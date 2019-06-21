package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.FileEmptyException;
import cn.tedu.store.service.ex.FileIOException;
import cn.tedu.store.service.ex.FileSizeException;
import cn.tedu.store.service.ex.FileStateException;
import cn.tedu.store.service.ex.FileTypeException;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{

	@Autowired
	private IUserService userService;
			
	@PostMapping("reg")
	private ResponseResult<Void> reg(User user){
		//执行注册
		userService.reg(user);
		//返回成功
		return new ResponseResult<>(SUCCESS);
	}
	
	@PostMapping("login")
	private ResponseResult<User> login(String username,String password,HttpSession session){
		//调用业务层对象的login（）方法执行登录
		User result = userService.login(username, password);
		//将登录结果中的uid封装到session中
		session.setAttribute("uid",result.getUid());
		//将登录结果中的username封装到session中
		session.setAttribute("username",result.getUsername());
		//返回结果
		return new ResponseResult<>(SUCCESS,result);
	}
	
	@PostMapping("update")
	private ResponseResult<Void> update(@RequestParam("old_password")String oldPassword,@RequestParam("new_password")String newPassword,HttpSession session){
		//从session中获取uid
		Integer uid = getUidFromSession(session);
		//从session中获取username
		String username = session.getAttribute("username").toString();
		//调用业务层对象执行修改密码
		userService.changePassword(uid, oldPassword, newPassword, username);
		//返回操作成功
		return new ResponseResult<>(SUCCESS);
	}
	
	@RequestMapping("change_info")
	public ResponseResult<Void> changeInfo(User user,HttpSession session){
		//获取uid
		Integer uid = getUidFromSession(session);
		//获取username
		String username = session.getAttribute("username").toString();
		//将uid和username封装到参数user对象中
		user.setUid(uid);
		user.setUsername(username);
		//调用业务层对象执行修改个人资料
		userService.changeInfo(user);
		//返回操作成功
		return new ResponseResult<>(SUCCESS);
	}
	
	/**
	 * 保存文件的文件夹名称
	 */
	public static final String UPLOAD_DIR = "images";
	
	/**
	 * 控制文件的大小
	 */
	public static final long UPLOAD_AVATAR_MAX_SIZE = 1*1024*1024;
	
	/**
	 * 文件的类型
	 */
	public static final List<String> UPLOAD_AVATAR_TYPES = new ArrayList<>();
	static {
		UPLOAD_AVATAR_TYPES.add("image/png");
		UPLOAD_AVATAR_TYPES.add("image/jpg");
		UPLOAD_AVATAR_TYPES.add("image/jpeg");
	}
	
	@PostMapping("change_avatar")
	public ResponseResult<String> changeAvatar(
			HttpServletRequest request,@RequestParam("avatar")MultipartFile avatar,HttpSession session){
		//检查是否选择了有效文件提交的请求，判断isEmpty
		if(avatar.isEmpty()) {
			//抛出异常：FileEmptyException
			throw new FileEmptyException("上传头像失败！未选择头像文件，或选择的文件为空");
		}
		
		//判断文件大小：SpringBoot默认不支持上传大文件
		long size = avatar.getSize();
		if(size>UPLOAD_AVATAR_MAX_SIZE) {
			//抛出异常FileSizeException
			throw new FileSizeException("上传头像失败！不允许使用超过"+UPLOAD_AVATAR_MAX_SIZE/1024+"kb的文件");
		}
		//判断文件类型
		String contentType = avatar.getContentType();
		System.err.println(contentType);
		if(!UPLOAD_AVATAR_TYPES.contains(contentType)) {
			//抛出异常：FileTypeException
			throw new FileTypeException("上传失败，不支持所提交的文件类型，允许的文件类型为："+UPLOAD_AVATAR_TYPES.get(0)+","+UPLOAD_AVATAR_TYPES.get(1));
		}
		//确定保存文件的文件夹的File对象：参考上传的demo
		String parentPath = request.getServletContext().getRealPath(UPLOAD_DIR);
		File parent = new File(parentPath);
		if(!parent.exists()) {
			parent.mkdirs();
		}
		//确定保存的文件的文件名：参考上传的demo
		String originalFilename = avatar.getOriginalFilename();
		String suffix = "";
		int beginIndex = originalFilename.lastIndexOf(".");
		if(beginIndex!=-1) {
			suffix = originalFilename.substring(beginIndex);
		}
		String child = UUID.randomUUID().toString()+suffix;
		//创建保存图像的File对像：dest = new File(parent,child)
		File dest = new File(parent,child);
		//执行上传并保存头像文件：avatar.transferTo(dest);
		try {
			avatar.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FileStateException("上传头像失败！所选择答的文件已经不可用");
			//抛出异常：FileStateException
		} catch (IOException e) {
			//抛出异常：FileIOException
			throw new FileIOException("上传头像失败！读写数据时出现错误！");
		}
		//执行修改数据：
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		
		String avatarPath = "/"+UPLOAD_DIR+"/"+child;
		
		userService.changeAvatar(uid, avatarPath, username);
		
		//返回成功及avatarPath
		ResponseResult<String> rr = new ResponseResult<>();
		rr.setState(SUCCESS);
		rr.setData(avatarPath);
		return rr;
	}
	
	@GetMapping("details")
	public ResponseResult<User> getByUid(HttpSession session){
		//获取uid
		Integer uid = getUidFromSession(session);
		//调用业务层对象执行获取数据
		User data = userService.getByUid(uid);
		//返回操作成功及数据
		return new ResponseResult<>(SUCCESS,data);
	}
}



































