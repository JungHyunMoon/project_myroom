package com.myroom.reservation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myroom.realEstate.bo.RealEstateBO;
import com.myroom.reservation.bo.ReservationBO;

@RestController
public class ReservationRestController {

	@Autowired
	private ReservationBO reservationBO;
	@Autowired
	private RealEstateBO realEstateBO;
	
	@PostMapping("/add_reservation")
	public Map<String, Object> addReservation(
			@RequestParam("realEstateId") int realEstateId,
			@RequestParam("userId") int userId,
			@RequestParam("realtorId") int realtorId,
			Model model
			) {
		
		reservationBO.addReservation(realEstateId, realtorId, userId);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		return result;
	}
	
	@PostMapping("/update_reservation")
	public Map<String, Object> updateReservation(
			@RequestParam("realEstateId") int realEstateId,
			@RequestParam("userId") int userId,
			@RequestParam("status") String status
			) {
		
		reservationBO.updateReservationStatus(realEstateId, userId, status);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		
		return result;
	}
	
	@PostMapping("/delete_reservation")
	public Map<String, Object> deleteReservation(
			@RequestParam("realEstateId") int realEstateId
			) {
		
		reservationBO.deleteReservation(realEstateId);
		realEstateBO.deleteRoomByRealEstateId(realEstateId);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		
		return result;
	}
	
}
