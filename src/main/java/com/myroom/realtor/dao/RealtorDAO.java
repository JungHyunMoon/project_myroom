package com.myroom.realtor.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myroom.realtor.model.Realtor;

@Repository
public interface RealtorDAO {

	public void insertRealtor(
			@Param("name") String name, 
			@Param("registerId") String registerId, 
			@Param("loginId") String loginId, 
			@Param("encodedPassword") String encodedPassword, 
			@Param("email") String email);
	
	public Realtor selectRealtorByLoginIdPassword(
			@Param("loginId") String loginId, 
			@Param("encodedPassword") String encodedPassword);
	
	public Realtor selectRealtorByLoginId(String loginId);
}
