package com.myroom.access;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/myroom")
public class AccessController {

	@GetMapping("")
	public String mainView() {
		return "/main";
	}
	
	@GetMapping("/sign_up")
	public String signUpView(
			Model model) {
		model.addAttribute("title", "회원가입");
		model.addAttribute("viewName", "signUp");
		return "/template/accessLayout";
	}
	
	@GetMapping("/sign_in")
	public String signInView(
			Model model) {
		model.addAttribute("title", "로그인");
		model.addAttribute("viewName", "signIn");
		return "/template/accessLayout";
	}
	
	@GetMapping("/sign_out")
	public String signOut(HttpSession session) {
		
		session.removeAttribute("userId");
		return "redirect:/myroom/sign_in";
	}
	
	@GetMapping("/find_my_id")
	public String findIdView(
			Model model) {
		model.addAttribute("title", "계정 찾기");
		model.addAttribute("viewName", "findMyAccount");
		return "/template/accessLayout";
	}
	
}
