package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;

/**
 * 处理用户数据的业务层接口
 */
public interface IUserService {

	/**
	 * 用户注册
	 * @param user 将要注册的用户信息
	 * @throws UsernameDuplicateException
	 * @throws InsertException
	 */
	void reg(User user) throws UsernameDuplicateException,InsertException;
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 成功登录的用户信息
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 */
	User login(String username,String password) throws UserNotFoundException,PasswordNotMatchException;

	/**
	 * 修改用户密码
	 * @param uid 用户id
	 * @param oldPassword 原密码
	 * @param newPassword 新密码
	 * @param username 用户名
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 * @throws UpdateException
	 */
	void changePassword(Integer uid,String oldPassword,String newPassword,String username) throws UserNotFoundException,PasswordNotMatchException,UpdateException;

	/**
	 * 修改用户个人资料
	 * @param user
	 * @throws UpdateException
	 * @throws UserNotFoundException
	 */
	void changeInfo(User user) throws UpdateException,UserNotFoundException;

	/**
	 * 上传用户头像
	 * @param uid 用户uid
	 * @param avatar
	 * @param username
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeAvatar(Integer uid,String avatar,String username) throws UserNotFoundException,UpdateException;
	
	/**
	 * 
	 * @param uid
	 * @return
	 */
	User getByUid(Integer uid) throws UserNotFoundException;
}





















