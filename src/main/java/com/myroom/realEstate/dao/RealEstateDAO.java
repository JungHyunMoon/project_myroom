package com.myroom.realEstate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myroom.realEstate.model.RealEstate;

@Repository
public interface RealEstateDAO {

	public void insertRealEstate(
			@Param("userId") int userId,
			@Param("sales_type") String sales_type,
			@Param("deposit") int deposit,
			@Param("monthRentPrice") int monthRentPrice,
			@Param("area") double area,
			@Param("address") String address,
			@Param("jibunAddress") String jibunAddress,
			@Param("local1") String local1,
			@Param("local2") String local2,
			@Param("local3") String local3,
			@Param("title") String title,
			@Param("residence_type") String residence_type,
			@Param("description") String description);
	
	public List<RealEstate> selectRealEstateListByUserId(int userId);
	
	public List<RealEstate> selectRealEstateListByRealtorId(int realtorId);
	
	public List<RealEstate> getRealEstateInfoByLocal3(String local);
	
	public List<RealEstate> getRealEstateInfoByLocal2(String local);
	
	public RealEstate selectRealEstateById(int RealEstateId);
	
	public void insertComment(
			@Param("userId") int userId,
			@Param("realEstateId") int realEstateId,
			@Param("content") String content);
	
	public void deleteRoomByRealEstateId(int realEstateId);
	
	public List<RealEstate> selectRealEstateWithNoRealtorInMylocal2(String local2);
	
	public void updateRoomRealtorId(
			@Param("realEstateId") int realEstateId, 
			@Param("realtorId") int realtorId);
	
	public void updateRealtorComment(
			@Param("realEstateId") int realEstateId, 
			@Param("comment") String comment);
	
	public void updateRoomRealtorIdNullByRealEstateId(int realEstateId);
	
}
