package org.dao.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.dao.demo.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

	public int insert(@Param("id")String id, @Param("name")String name, @Param("password")String password);

	// 这里必须加个 @Param 注解，用于指定sql语句中 ${field} 中的字段标识符，
	// 否则会报异常，提示为 未提供对应字段的 getter 方法，
	public List<String> selectId(@Param("name")String name);

	public List<String> fuzzyQueryId(String name);

	public List<String> fuzzyQueryIdPre(String name);

	public int updatePwd(String name, String newpwd, String oldpwd);

	public List<String> selectName();

	public int delete(String name);

	public List<String> login(String name, String pwd);

	public List<User> findUser(String name, String pwd);

	User findUserByName(String name);
	
	boolean save(User user);
}
