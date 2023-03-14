package com.myroom.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myroom.comment.bo.CommentBO;
import com.myroom.realEstate.bo.RealEstateBO;
import com.myroom.reservation.bo.ReservationBO;
import com.myroom.user.bo.UserBO;
import com.myroom.user.model.User;

@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	@Autowired
	private RealEstateBO realEstateBO;
	@Autowired
	private CommentBO commentBO;
	@Autowired
	private ReservationBO reservationBO;
	
	@PostMapping("/get_user_Info")
	public Map<String, Object> getUserInfo(
			@RequestParam("userId") int userId) {
		
		Map<String, Object> result = new HashMap<>();
		
		User user = userBO.getUserById(userId);
		result.put("code", 1);
		result.put("userId", user.getId());
		result.put("email", user.getEmail());
		result.put("name", user.getName());
		result.put("loginId", user.getLoginId());
		return result;
	}
	
	@PostMapping("/delete_user")
	public Map<String, Object> deleteuser(
			@RequestParam("userId") int userId) {
		
		userBO.deleteUser(userId);
		realEstateBO.deleteRoomByUserId(userId);
		commentBO.deleteCommentByUserId(userId);
		reservationBO.deleteReservationByUserId(userId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		return result;
	}
}
