package com.myroom.access;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myroom")
public class AccessRestController {

	@PostMapping("sign_in")
	public String signInView(
			Model model) {
		model.addAttribute("title", "로그인");
		model.addAttribute("viewName", "signIn");
		return "/template/accessLayout";
	}
}
