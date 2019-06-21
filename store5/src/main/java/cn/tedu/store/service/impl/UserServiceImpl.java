package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public void reg(User user) throws UsernameDuplicateException, InsertException {
		// 根据尝试注册的用户名查询数据
		User result = userMapper.findByUsername(user.getUsername());
		//判断查询结果是否为null
		if(result==null) {
			//允许注册，执行注册
			//封装is_delete
			user.setIsDelete(0);
			//封装日志
			Date now = new Date();
			user.setCreatedUser(user.getUsername());
			user.setCreatedTime(now);
			user.setModifiedUser(user.getUsername());
			user.setModifiedTime(now);
			//TODO 密码加密
			//生成随机盐
			String salt = UUID.randomUUID().toString().toUpperCase();
			//基于原密码和盐值执行加密
			String md5Password = getMd5Password(user.getPassword(), salt);		
			//将盐和加密结果封装到user对象中
			user.setPassword(md5Password);
			user.setSalt(salt);
			Integer rows = userMapper.insert(user);
			if(rows!=1) {
				throw new InsertException("注册时发生未知错误，请联系系统管理员！！");
			}
		}else {
			//不允许注册
			throw new UsernameDuplicateException("尝试注册的用户名（"+user.getUsername()+")已经存在！！");
		}

	}

	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		//根据参数username查询匹配的用户信息
		User result = userMapper.findByUsername(username);
		//判断查询结果是否为null
		if(result==null) {
			//是：用户对应的数据不存在，抛出UserNotFoundException
			throw new UserNotFoundException("登录失败，用户名不存在");
		}
		//判断isDelete是否为1
		if(result.getIsDelete()==1) {
			//是：用户对应数据标记为“已删除”，抛出UserNotFoundException
			throw new UserNotFoundException("登录失败，用户标记为删除");
		}
		//获取查询到的用户的盐值
		String salt = result.getSalt();
		//将用户输入的密码和盐值进行加密
		String md5Password = getMd5Password(password, salt);
		//如果用户输入的密码和获取的密码不同，则抛出PasswordNotMatchException
		if(!md5Password.equals(result.getPassword())) {
			throw new PasswordNotMatchException("登录失败，密码错误！！");
		}
		//将查询结果中的salt，password,isDelete设置为null
		result.setSalt(null);
		result.setPassword(null);
		result.setIsDelete(null);
		//返回查询结果
		return result;
	}
	
	@Override
	public void changePassword(Integer uid, String oldPassword, String newPassword, String username)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		User result = userMapper.findByUid(uid);
		if(result==null) {
			throw new UserNotFoundException("修改密码失败，尝试访问的用户数据不存在！！");
		}
		
		if(result.getIsDelete()==1) {
			throw new UserNotFoundException("修改密码失败，对应用户已标记为删除！！");
		}
		
		String salt = result.getSalt();
		String md5OldPassword = getMd5Password(oldPassword,salt);
		System.err.println(md5OldPassword);
		System.err.println(result.getPassword());
		
		if(!md5OldPassword.equals(result.getPassword())) {
			throw new PasswordNotMatchException("修改密码失败，原密码错误！！");
		}
		
		//获取修改的时间
		Date now = new Date();
		String md5NewPassword = getMd5Password(newPassword, salt);
		Integer rows = userMapper.updatePassword(uid,md5NewPassword,username,now);
		
		if(rows!=1) {
			throw new UpdateException("密码修改失败！！更新数据时发生未知错误！！");
		}
	}
	
	@Override
	public void changeInfo(User user) throws UpdateException, UserNotFoundException {
		//从参数user中获取uid
		Integer uid = user.getUid();
		//调用持久层对象的方法，根据uid查询用户数据
		User result = userMapper.findByUid(uid);
		//判断查询结果是否为null；
		if(result==null) {
			//是：抛出UserNotFoundException
			throw new UserNotFoundException("修改资料错误！！用户不存在！");
		}
		//判断查询结果中的isDelete是否为1
		if(result.getIsDelete()==1) {
			//是：抛出UserNotFoundException
			throw new UserNotFoundException("修改资料错误！！该用户已标记为删除状态！");
		}
		//创建当前时间对象，封装到user中
		Date now = new Date();
		//TODO 确保modifiedUser属性是有值的
		user.setModifiedTime(now);
		Integer rows = userMapper.updateInfo(user);
		if(rows!=1) {
			throw new UpdateException("修改资料失败！发生未知错误！");
		}
		
	}

	@Override
	public User getByUid(Integer uid) throws UserNotFoundException {
		//调用持久层对象查询参数uid匹配的用户数据
		User result = userMapper.findByUid(uid);
		//判断查询结果是否为null
		if(result==null) {
			throw new UserNotFoundException("用户不存在！！");
		}
		//判断查询结果中的isDelete是否为1
		if(result.getIsDelete()==1) {
			throw new UserNotFoundException("用户不存在！！");
		}
		//将查询结果中的password/salt/isDelete设置为null
		result.setPassword(null);
		result.setSalt(null);
		result.setIsDelete(null);
		
		return result;
	}
	
	
	@Override
	public void changeAvatar(Integer uid, String avatar, String username)
			throws UserNotFoundException, UpdateException {
		//根据参数uid查询用户数据
		User result = userMapper.findByUid(uid);
		//判断用户数据是否为null，是，抛出异常
		if(result==null) {
			throw new UserNotFoundException("上传失败！用户不存在！");
		}
		//判断用户数据中的isDelete是否为1，是，抛出异常
		if(result.getIsDelete()==1) {
			throw new UserNotFoundException("上传失败！该用户标记为删除了");
		}
		//创建时间对象
		Date now = new Date();
		//调用持久层的方法执行更新操作
		Integer rows = userMapper.updateAvatar(uid, avatar,username,now);
		//判断返回值是否不为1
		if(rows!=1) {
			
			throw new UpdateException("上传头像失败！发生未知错误！");
		}
	}
	
	
	/**
	 * 将密码执行加密
	 * @param password 原密码
	 * @param salt 盐值
	 * @return 加密后的结果
	 */
	private String getMd5Password(String password,String salt) {
		//拼接原密码与盐值
		String str = salt+password+salt;
		//循环加密5次
		for (int i = 0; i < 5; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
		}
		//返回结果
		return str;
	}

	

}
