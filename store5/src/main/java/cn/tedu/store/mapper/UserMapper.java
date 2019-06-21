package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

/**
 * 处理用户数据的持久层接口
 * @author soft01
 *
 */
public interface UserMapper {
	
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	Integer insert(User user);
	
	/**
	 * 根据用户uid修改密码
	 * @param uid 用户的id
	 * @param password 新密码
	 * @param modifiedUser 最后修改执行人
	 * @param modifiedTime 最后修改时间
	 * @return 受影响的行数
	 */
	Integer updatePassword(@Param("uid")Integer uid,@Param("password")String password,@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 根据用户id上传用户头像
	 * @param uid 用户的id
	 * @param avatar 头像的路径
	 * @param modifiedUser 最后修改执行人
	 * @param modifiedTime 最后修改时间
	 * @return 受影响的行数
	 */
	Integer updateAvatar(@Param("uid")Integer uid,
								@Param("avatar")String avatar,
								@Param("modifiedUser")String modifiedUser,
								@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 根据用户uid修改资料
	 * @param user 包含了用户资料的对象
	 * @return 受影响的行数
	 */
	Integer updateInfo(User user);
	
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 
	 */
	User findByUsername(String username);
	
	/**
	 * 根据uid查询用户是否存在
	 * @param uid
	 * @return 查询到的用户数据
	 */
	User findByUid(Integer uid);
	
	
}






















