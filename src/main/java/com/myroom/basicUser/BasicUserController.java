package com.myroom.basicUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myroom")
public class BasicUserController {
	
	@GetMapping("/user/map")
	public String signInView(
			Model model) {
		model.addAttribute("title", "매물보기");
		model.addAttribute("leftViewName", "realEstateMap");
		model.addAttribute("rightViewName", "realEstateList");
		return "/template/mapLayout";
	}
}
