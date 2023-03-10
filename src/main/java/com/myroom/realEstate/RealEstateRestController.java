package com.myroom.realEstate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myroom.realEstate.bo.RealEstateBO;
import com.myroom.realEstate.model.RealEstate;
import com.myroom.reservation.bo.ReservationBO;
import com.myroom.reservation.model.ReservationCard;
import com.myroom.user.bo.UserBO;
import com.myroom.user.model.User;

import jakarta.servlet.http.HttpSession;

@RestController
public class RealEstateRestController {

	@Autowired
	private RealEstateBO realEstateBO;
	@Autowired
	private ReservationBO reservationBO;
	@Autowired
	private UserBO userBO;
	
	@PostMapping("/add_room")
	public Map<String, Object> addRoom(
			@RequestParam("local1") String local1,
			@RequestParam("local2") String local2,
			@RequestParam("local3") String local3,
			@RequestParam("jibunAddress") String jibunAddress,
			@RequestParam("deposit") int deposit,
			@RequestParam("monthRentPrice") int monthRentPrice,
			@RequestParam("residence_type") String residence_type,
			@RequestParam("area") double area,
			@RequestParam("title") String title,
			@RequestParam("description") String description,
			Model model, HttpSession session) {
		
		int userId = (int) session.getAttribute("userId");
		realEstateBO.addRealEstate(userId, local1, local2, local3, jibunAddress, deposit, monthRentPrice, residence_type, area, title, description);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("keyword", local3);
		return result;
	}
	
	@PostMapping("/delete_room")
	public Map<String, Object> deleteRoom(
			@RequestParam("realEstateId") int realEstateId) {
		
		realEstateBO.deleteRoomByRealEstateId(realEstateId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		return result;
	}
	
	@PostMapping("/set_room_realtor")
	public Map<String, Object> setRoomRealtor(
			@RequestParam("realEstateId") int realEstateId,
			@RequestParam("realtorId") int realtorId
			) {
		
		realEstateBO.setRoomRealtorId(realEstateId, realtorId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		return result;
	}
	
	@PostMapping("/drop_realEstate")
	public Map<String, Object> dropRealEstate(
			@RequestParam("realEstateId") int realEstateId
			) {
		
		realEstateBO.setRoomRealtorIdNullByRealEstateId(realEstateId);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		
		return result;
	}
	
	@PutMapping("/set_realtor_comment")
	public Map<String, Object> setRealtorComment(
			@RequestParam("realEstateId") int realEstateId,
			@RequestParam("comment") String comment
			) {
		
		realEstateBO.setRealtorComment(realEstateId, comment);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		return result;
	}
	
	@PostMapping("/get_room")
	public Map<String, Object> getRoom(
			@RequestParam("realEstateId") int realEstateId,
			Model model,
			HttpSession session
			) {
		
		int realtorId = (int) session.getAttribute("realtorId");
		
		RealEstate realEstate = realEstateBO.getRealEstateById(realEstateId);
		List<ReservationCard> reservatedUserList = reservationBO.getReservatedUserByRealtorIdRealEstateId(realtorId, realEstateId);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("realEstate", realEstate);
		result.put("reservatedUserList", reservatedUserList);
		
		return result;
	}
}
