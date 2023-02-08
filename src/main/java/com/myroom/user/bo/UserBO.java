package com.myroom.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.user.dao.UserDAO;
import com.myroom.user.model.User;

@Service
public class UserBO {

	@Autowired 
	private UserDAO userDAO;
	
	public void addUser(String loginId, String hashedPassword, String name, String email) {
		userDAO.insertUser(loginId, hashedPassword, name, email);
	}
	
	public User getUserByLoginIdPassword(String loginId, String hashedPassword) {
		return userDAO.selectUserByLoginIdPassword(loginId, hashedPassword);
	}
	
}
