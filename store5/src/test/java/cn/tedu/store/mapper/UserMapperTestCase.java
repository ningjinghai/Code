package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {

	@Autowired
	public UserMapper mapper;
	
	@Test
	public void inert() {
		User user = new User();
		user.setUsername("root");
		user.setPassword("1234");
		Integer rows = mapper.insert(user);
		System.err.println("rows="+rows);
	}
	
	@Test
	public void findByUsername() {
		String username = "root";
		User user = mapper.findByUsername(username);
		System.err.println(user);
	}
	
	@Test
	public void findByUid() {
		User user = mapper.findByUid(2);
		System.err.println(user);
	}

	@Test
	public void updatePassword() {
		Date now = new Date();
		Integer rows = mapper.updatePassword(2,"2222","张三",now);
		System.err.println(rows);
	}
	
	@Test
	public void updateInfo() {
		User user = new User();
		user.setUid(6);
		user.setPhone("123342332");
		user.setEmail("124@55261");
		user.setGender(1);
		user.setModifiedUser("李四");
		user.setModifiedTime(new Date());
		Integer rows = mapper.updateInfo(user);
		System.err.println(rows);
		
	}
	
	@Test
	public void updateAvatar() {
		Date now = new Date();
		Integer rows = mapper.updateAvatar(2,"888888","大乔",now);
		System.err.println(rows);
	}
	
}































