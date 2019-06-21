package cn.tedu.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
	
	@RequestMapping("login.do")
	@ResponseBody
	public ResponseResult handleLogin(String username,String password) {
		System.out.println("username="+username);
		System.out.println("password="+password);
		
		if("root".equals(username)) {
			if("1234".equals(password)) {
				return new ResponseResult(1,"登录成功！");
			}else {
				return new ResponseResult(2,"登录失败，密码错误！");
			}
		}else {
			return new ResponseResult(3,"登录失败，用户名不存在！");
		}
		
	}
	

}
