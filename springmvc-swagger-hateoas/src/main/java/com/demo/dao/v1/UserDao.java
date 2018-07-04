package com.demo.dao.v1;

import java.util.List;

import com.demo.model.v1.User;

public interface UserDao {
      
    User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);
}
