package com.myroom.basicUser;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myroom.realEstate.bo.RealEstateBO;

@Controller
@RequestMapping("/myroom")
public class BasicUserController {
	
	@Autowired
	private RealEstateBO realEstateBO;
	
//	@GetMapping("/user/map")
//	public String signInUserView(Model model) {
//		model.addAttribute("title", "매물보기");
//		model.addAttribute("leftViewName", "realEstateMap");
//		model.addAttribute("rightViewName", "realEstateList");
//		return "/template/mapLayout";
//	}
	
	@GetMapping("/user/map")
	public String signInView(
			Model model,
			@RequestParam(value="local", required=false) String local
			) {
		
		List<Map<String, Object>> realEstateList = realEstateBO.getRealEstateInfoByLocal2(local);
		
		model.addAttribute("keyword", local);
		model.addAttribute("realEstateList", realEstateList);
		model.addAttribute("title", "매물보기");
		model.addAttribute("leftViewName", "realEstateMap");
		model.addAttribute("rightViewName", "realEstateList");
		return "/template/mapLayout";
	}
}
