package org.service.demo.serviceImpl;

import org.dao.demo.bean.User;
import org.dao.demo.dao.UserDao;
import org.service.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userdao;

	public boolean insert(String id, String name, String password, int age) {
		int result = userdao.insert(id,name,password);
		if (result == 0) {
			return false;
		}
		return true;
	}

	public List<String> selectId(String name) {
		List<String> idList = userdao.selectId(name);
		return idList;
	}

	@Override
	public List<String> selectUser(String name, String pwd) {
		return null;
	}

	public List<String> fuzzyQueryId(String name) {
		List<String> idList = userdao.fuzzyQueryId(name);
		return idList;
	}

	public List<String> fuzzyQueryIdPre(String name) {
		List<String> idList = userdao.fuzzyQueryIdPre(name);
		return idList;
	}

	public boolean updatePwd(String name, String newpwd, String oldpwd) {
		int result = userdao.updatePwd(name, newpwd, oldpwd);
		return result != 0;
	}

	public List<String> selectName() {
		List<String> nameList = userdao.selectName();
		return nameList;
	}

	public boolean deleteUser(String userName) {
		int result = userdao.delete(userName);
		return result != 0;
	}

	public boolean login(String name, String pwd) {
		List<String> idList = userdao.login(name, pwd);
		if (idList.size() == 0 || idList == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<User> selectUsers(String name, String pwd) {
		return userdao.findUser(name, pwd);
	}
}
