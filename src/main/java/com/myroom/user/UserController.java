package com.myroom.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myroom.realEstate.bo.RealEstateBO;
import com.myroom.realEstate.model.RealEstate;
import com.myroom.reservation.bo.ReservationBO;
import com.myroom.user.model.UserReservation;

@Controller
@RequestMapping("/myroom/user")
public class UserController {

	@Autowired
	private RealEstateBO realEstateBO;
	@Autowired
	private ReservationBO reservationBO;
	
	@GetMapping("/map")
	public String signInUserView(
			Model model,
			@RequestParam(value="local", required=false) String local
			) {
		
		List<Map<String, Object>> realEstateList = realEstateBO.getRealEstateInfoByLocal3(local);
		
		model.addAttribute("keyword", local);
		model.addAttribute("realEstateList", realEstateList);
		model.addAttribute("title", "매물보기");
		model.addAttribute("leftViewName", "realEstateMap");
		model.addAttribute("rightViewName", "realEstateList");
		return "/template/mapLayout";
	}
	
	@GetMapping("/new_room")
	public String newRoomView(Model model) {
		
		model.addAttribute("title", "방 내놓기");
		model.addAttribute("leftViewName", "terms");
		model.addAttribute("rightViewName", "newRoom");
		
		return "/template/twoContents";
	}
	
	@GetMapping("/rooms")
	public String roomsView(Model model, HttpSession session) {
		
		;
		List<RealEstate> realEstateList = realEstateBO.getRealEstateListByUserId((int)session.getAttribute("userId"));
		model.addAttribute("title", "방 관리");
		model.addAttribute("viewName", "rooms");
		model.addAttribute("realEstateList", realEstateList);
		
		return "/template/oneContent";
	}
	
	@GetMapping("/reservation")
	public String reservationView(
			Model model,
			HttpSession session) {
		
		int userId = (int) session.getAttribute("userId");
		List<UserReservation> reservationList = reservationBO.getUserReservationListByUserId(userId);
		
		model.addAttribute("reservationList", reservationList);
		model.addAttribute("title", "예약내역");
		model.addAttribute("viewName", "reservation");
		
		return "/template/oneContent";
	}
}
