package com.myroom.realtor;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myroom.realEstate.bo.RealEstateBO;
import com.myroom.realEstate.model.RealEstate;
import com.myroom.realtor.bo.RealtorBO;
import com.myroom.reservation.bo.ReservationBO;

@Controller
@RequestMapping("/myroom")
public class RealtorController {

	@Autowired
	private RealEstateBO realEstateBO;
	@Autowired
	private RealtorBO realtorBO;
	@Autowired
	private ReservationBO reservationBO;
	
	@GetMapping("/realtor/new_room")
	public String signInRealtorView(
			Model model,
			HttpSession session
			) {
		
		String local2 = (String) session.getAttribute("local2");
		List<RealEstate> realEstateList = realEstateBO.getRealEstateWithNoRealtorInMylocal2(local2);
		model.addAttribute("realEstateList", realEstateList);
		model.addAttribute("title", "중개사 메인화면");
		model.addAttribute("viewName", "newRooms");
		return "/template/oneContent";
	}
	
	@GetMapping("/realtor/clients")
	public String getClients(
			Model model,
			HttpSession session) {
		
		int realtorId = (int) session.getAttribute("realtorId");
		List<RealEstate> realEstateList = realEstateBO.getRealEstateListByRealtorId(realtorId);
		int reservationCount = reservationBO.getReservationCountByRealtorId(realtorId);
		
		model.addAttribute("realEstateList", realEstateList);
		model.addAttribute("reservationCount", reservationCount);
		model.addAttribute("title", "중개관리");
		model.addAttribute("leftViewName", "myClientRooms");
		model.addAttribute("rightViewName", "clientRoomDetail");
		return "/template/twoContents";
	}
}
