package com.myroom.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myroom.user.model.User;

@Repository
public interface UserDAO {

	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId, 
			@Param("hashedPassword") String hashedPassword);
	
	public void insertUser(
			@Param("loginId") String loginId, 
			@Param("hashedPassword") String hashedPassword, 
			@Param("name")String name,
			@Param("phoneNumber")String phoneNumber,
			@Param("email")String email);
	
	public User selectUserByLoginId(String loginId);
	
	public User selectUserById(int userId);
	
	public void deleteUser(int userId);
}
