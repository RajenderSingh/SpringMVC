package com.demo.dao.v1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

import com.demo.model.v1.User;

@Repository
public class UserDaoImpl implements UserDao {
	private static final AtomicLong counter = new AtomicLong();
	private static List<User> users;	
	static{
		users = populateDummyUsers();
	}

	public List<User> findAllUsers() {
		return users;
	}
	
	public User findById(long uid) {
		for(User user : users){
			if(user.getUId() == uid){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User user : users){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		user.setUId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long uid) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getUId() == uid) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"AA",30, 70000));
		users.add(new User(counter.incrementAndGet(),"BB",40, 50000));
		users.add(new User(counter.incrementAndGet(),"CC",45, 30000));
		users.add(new User(counter.incrementAndGet(),"DD",50, 40000));
		return users;
	}

	public void deleteAllUsers() {
		users.clear();
	}

}
