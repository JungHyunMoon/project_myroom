package com.myroom.realtor.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RealtorDAO {

	public void insertRealtor(
			@Param("name") String name, 
			@Param("registerId") String registerId, 
			@Param("loginId") String loginId, 
			@Param("hashedPassword") String hashedPassword, 
			@Param("email") String email);
}
