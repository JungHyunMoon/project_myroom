package com.myroom.realEstate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myroom.realEstate.bo.RealEstateBO;
import com.myroom.realEstate.model.RealEstate;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/myroom")
public class RealEstateController {

	@Autowired
	private RealEstateBO realEstateBO;
	
	@GetMapping("/items/{realEstateId}")
	public String itemsDetail(
			@PathVariable int realEstateId,
			Model model,
			HttpSession session) {
		
		RealEstate realEstate = realEstateBO.getRealEstateById(realEstateId);
		
		model.addAttribute("title", "매물 상세보기");
		model.addAttribute("leftViewName", "detailInfo");
		model.addAttribute("rightViewName", "review");
		model.addAttribute("title", "매물 상세보기");
		model.addAttribute("type", session.getAttribute("type"));
		
		model.addAttribute("realEstate", realEstate);
		
		return "/template/twoContents";
	}
}
