package com.myroom.reservation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.myroom.reservation.model.Reservation;

@Repository
public interface ReservationDAO {

	public void insertReservation(
			@Param("realEstateId") int realEstateId,
			@Param("realtorId") int realtorId, 
			@Param("userId") int userId);
	
	public List<Reservation> selectReservationByRealtorIdRealEstateId(
			@Param("realtorId") int realtorId,
			@Param("realEstateId") int realEstateId);
	
	public List<Reservation> selectReservationListByRealtorId(int realtorId);
	
	public List<Reservation> selectReservationListByRealtorIdRealEstateId(
			@Param("realtorId") int realtorId, 
			@Param("realEstateId") int realEstateId);
	
	public String selectReservationStatusByRealtorIdUserId(
			@Param("realtorId") int realtorId,
			@Param("userId") int userId);
	
	public void updateReservationStatus(
			@Param("realEstateId") int realEstateId,
			@Param("userId") int userId,
			@Param("status") String status);
	
	public void deleteReservation(int realEstateId);
	
	public void deleteReservationByUserIdRealEstaetId(
			@Param("userId") int userId,
			@Param("realEstateId") int realEstateId);
	
	public List<Reservation> selectCompletedReservationListByUserId(int userId);
}
