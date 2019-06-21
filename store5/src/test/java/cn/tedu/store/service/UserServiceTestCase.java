package cn.tedu.store.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
	
	@Autowired
	public IUserService service;
	
	@Test
	public void reg() {		
		try {
			User user = new User();
			user.setUsername("吕布");
			user.setPassword("88888");
			service.reg(user);
			System.err.println("ok");
		} catch (ServiceException e) {
			System.err.println(e.getClass());
			System.err.println(e.getMessage());
		}
		
	}
	
	@Test
	public void login() {
		try {
			String username = "root";
			String password = "12345";
			User user = service.login(username, password);
			System.err.println(user);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void updatePassword() {
		try {
			String newPassword = "9999";
			String username = "mybatis";
			Integer uid = 3;
			String oldPassword = "6666";
			service.changePassword(uid, oldPassword, newPassword, username);
			System.err.println("OK");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void updateInfo() {
		try {
			User user = new User();
			user.setUid(7);
			user.setPhone("9888888332");
			user.setEmail("124@88888");
			user.setGender(1);
			user.setModifiedUser("李四");
			service.changeInfo(user );
			System.err.println("OK");
		} catch(ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void getByUid() {
		try {
			Integer uid = 1;
			User user = service.getByUid(uid );
			System.err.println(user);
		} catch(ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void updateAvatar() {
		try {
			String avatar = "99999";
			Integer uid = 2;
			String username = "超级管理员";
			service.changeAvatar(uid, avatar, username);
			System.err.println("OK");
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}

}
















