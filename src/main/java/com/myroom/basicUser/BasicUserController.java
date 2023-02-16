package com.myroom.basicUser;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myroom.realEstate.bo.RealEstateBO;

@Controller
@RequestMapping("/myroom")
public class BasicUserController {
	
	@Autowired
	private RealEstateBO realEstateBO;
	
	@GetMapping("/user/map")
	public String signInView(
			Model model) {
		
		List<Map<String, Object>> realEstateList = realEstateBO.getRealEstateInfoByLocal2("삼성동");
		
		model.addAttribute("realEstateList", realEstateList);
		model.addAttribute("title", "매물보기");
		model.addAttribute("leftViewName", "realEstateMap");
		model.addAttribute("rightViewName", "realEstateList");
		return "/template/mapLayout";
	}
}
