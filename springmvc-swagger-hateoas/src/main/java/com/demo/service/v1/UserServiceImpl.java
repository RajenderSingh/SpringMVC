package com.demo.service.v1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.v1.UserDao;
import com.demo.model.v1.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;

	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}
	
	public User findById(long id) {
		return userDao.findById(id);
	}
	
	public User findByName(String name) {
		return userDao.findByName(name);
	}
	
	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void deleteUserById(long id) {
		userDao.deleteUserById(id);
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}

	public void deleteAllUsers() {
		userDao.deleteAllUsers();
	}

}
