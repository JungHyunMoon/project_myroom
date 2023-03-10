package com.myroom.reservation.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.reservation.dao.ReservationDAO;
import com.myroom.reservation.model.Reservation;
import com.myroom.reservation.model.ReservationCard;
import com.myroom.user.dao.UserDAO;
import com.myroom.user.model.User;

@Service
public class ReservationBO {

	@Autowired
	private ReservationDAO reservationDAO;
	@Autowired
	private UserDAO userDAO;
	
	public void addReservation(int realEstateId, int realtorId, int userId) {
		reservationDAO.insertReservation(realEstateId, realtorId, userId);
	}
	
//	public List<Reservation> getReservatedUserByRealtorIdRealEstateId(int realtorId, int realEstateId) {
//		return reservationDAO.selectReservationByRealtorIdRealEstateId(realtorId, realEstateId);
//	}
	
	public int getReservationCountByRealtorId(int realtorId) {
		List<Reservation> reservationList = reservationDAO.selectReservationListByRealtorId(realtorId);
		int result = 0;
		for (int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getStatus().equals("대기중")) {
				result = result + 1;
			}
		}
		return result;
	}
	
	public List<ReservationCard> getReservatedUserByRealtorIdRealEstateId(int realtorId, int realEstateId) {
		List<ReservationCard> result = new ArrayList<>();
		ReservationCard reservationCard = null;
		
		List<Reservation> reservationList = reservationDAO.selectReservationListByRealtorIdRealEstateId(realtorId, realEstateId);
		for (Reservation reservation : reservationList) {
			reservationCard = new ReservationCard();
			int userId = reservation.getUserId();
			User user = userDAO.selectUserById(userId);
			String status = reservationDAO.selectReservationStatusByRealtorIdUserId(realtorId, userId);
			
			reservationCard.setUser(user);
			reservationCard.setStatus(status);
			result.add(reservationCard);
		}
		return result;
	}
	
	public void updateReservationStatus(int realEstateId, int userId, String status) {
		reservationDAO.updateReservationStatus(realEstateId, userId, status);
	}
	
	public void deleteReservation(int realEstateId) {
		reservationDAO.deleteReservation(realEstateId);
	}
	
	public List<Reservation> getCompletedReservationListByUserId(int userId) {
		return reservationDAO.selectCompletedReservationListByUserId(userId);
	}
	
}
