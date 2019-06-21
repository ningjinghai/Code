package cn.tedu.store.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.tedu.store.interceptor.LoginInterceptor;

@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//创建拦截器对象
		HandlerInterceptor interceptor = new LoginInterceptor();
		
		//白名单,即：不要求登录即可访问的路径
		List<String> patters = new ArrayList<>();
		patters.add("/js/**");
		patters.add("/css/**");
		patters.add("/images/**");
		patters.add("/bootstrap3/**");
		patters.add("/web/register.html");
		patters.add("/web/login.html");
		patters.add("/users/reg");
		patters.add("/users/login");
		
		//通过注册工具添加拦截器对象
		registry.addInterceptor(interceptor)
							.addPathPatterns("/**")
							.excludePathPatterns(patters);
		
		
		
	}

	
}
