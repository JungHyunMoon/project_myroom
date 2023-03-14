package com.myroom.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myroom.comment.bo.CommentBO;
import com.myroom.reservation.bo.ReservationBO;

@RestController
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	@Autowired
	private ReservationBO reservationBO;

	@PostMapping("/add_comment")
	public Map<String, Object> addRealEstateComment(
			@RequestParam("realEstateId") int realEstateId,
			@RequestParam("reservationId") int reservationId,
			@RequestParam("content") String content,
			HttpSession session
			) {
		
		int userId = (int) session.getAttribute("userId");
		
		commentBO.addComment(userId, realEstateId, content);
		String temp = userId + " & " + realEstateId;
		reservationBO.deleteReservationByUserIdRealEstaetId(userId, realEstateId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		
		return result;
	}
}
