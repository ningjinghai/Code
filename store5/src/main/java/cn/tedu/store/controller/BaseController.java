package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.tedu.store.service.ex.FileEmptyException;
import cn.tedu.store.service.ex.FileIOException;
import cn.tedu.store.service.ex.FileSizeException;
import cn.tedu.store.service.ex.FileStateException;
import cn.tedu.store.service.ex.FileTypeException;
import cn.tedu.store.service.ex.FileUploadException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;
import cn.tedu.store.util.ResponseResult;

/**
 * 控制器的基类
 * @author soft01
 *
 */
public abstract class BaseController {
	
	/**
	 * 响应结果是用于表示操作成功
	 */
	protected static final int SUCCESS = 200;
	
	/**
	 * 从Session中获取uid
	 * @param session session HttpSession中的对象
	 * @return 用户的uid
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
	@ExceptionHandler({ServiceException.class,FileUploadException.class})
	public ResponseResult<Void> handleException(Throwable e){
		ResponseResult<Void> rr = new ResponseResult<>();
		rr.setMessage(e.getMessage());
		
		if(e instanceof UsernameDuplicateException) {
			//400-用户名冲突异常
			rr.setState(400);
		}else if(e instanceof UserNotFoundException) {
			//401-尝试访问的用户数据不存在
			rr.setState(401);
		}else if(e instanceof PasswordNotMatchException) {
			//402-验证密码失败 ，密码错误
			rr.setState(402);
		}else if(e instanceof InsertException) {
			//500-插入数据异常
			rr.setState(500);
		}else if(e instanceof UpdateException) {
			//501-更新数据异常
			rr.setState(501);
		}else if(e instanceof FileEmptyException) {
			//600-文件不存在异常
			rr.setState(600);
		}else if(e instanceof FileSizeException) {
			//601-文件过大异常
			rr.setState(601);
		}else if(e instanceof FileTypeException) {
			//602-文件类型不匹配异常
			rr.setState(602);
		}else if(e instanceof FileIOException) {
			//603-文件IO异常
			rr.setState(603);
		}else if(e instanceof FileStateException) {
			//604-文件参数异常
			rr.setState(604);
		}
		return rr;
	}

}

























