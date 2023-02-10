package com.myroom.access;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myroom.access.bo.AccessBO;
import com.myroom.common.EncryptUtils;
import com.myroom.realtor.bo.RealtorBO;
import com.myroom.user.bo.UserBO;
import com.myroom.user.model.User;

@RestController
public class AccessRestController {

	@Autowired
	private UserBO userBO;
	@Autowired
	private RealtorBO realtorBO;
	@Autowired
	private AccessBO accessBO;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*
	 * 
	 */
	@PostMapping("/sign_in_user")
	public Map<String, Object> signInUser(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			Model model) {
		
		String hashedPassword = EncryptUtils.md5(password);
		
		Map<String, Object> result = new HashMap<>();
		User user = userBO.getUserByLoginIdPassword(loginId, hashedPassword);
		if (user != null) {
			result.put("code", 1);
			result.put("result", "성공");
			result.put("userName", user.getName());
		} else {
			result.put("code", 500);
			result.put("errorMessage", "아이디 또는 비밀번호를 확인하세요.");
		}
		
		
		return result;
	}
	
	@PostMapping("/sign_in_realtor")
	public Map<String, Object> signInRealtor(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") CharSequence password,
			Model model) {
		
		String realtorPassword = realtorBO.getRealtorPasswordByLoginId(loginId);
		boolean isMatches = passwordEncoder.matches(password, realtorPassword);
		
//		boolean realtor = realtorBO.isMatchesPassword(loginId, password);
		
		Map<String, Object> result = new HashMap<>();
		if (isMatches == true) {
			result.put("code", 2);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "중개인의 아이디 또는 비밀번호를 확인하세요."
			+ "\n" + password
			+ "\n" + isMatches
			+ "\n" + realtorPassword
			);
//			result.put("errorMessage", "중개인의 아이디 또는 비밀번호를 확인하세요.");
		};
		
		
		return result;
	}
	
	@PostMapping("/sign_up_user")
	public Map<String, Object> signUpUser(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			Model model) {
		
		String hashedPassword = EncryptUtils.md5(password);
		
		userBO.addUser(loginId, hashedPassword, name, email);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		
		return result;
	}
	
	@PostMapping("/isRegisterd")
	public Map<String, Object> isRegisterd(
			@RequestParam("realtorName") String realtorName,
			@RequestParam("registerNumber") String registerNumber
			) {
		
		Map<String, Object> result = new HashMap<>();
		
		int totalCount = accessBO.getRealtorTotalCount(realtorName, registerNumber);
		result.put("result", totalCount);
		
		return result;
	}
	
	@PostMapping("/sign_up_realtor")
	public Map<String, Object> signUpRealtor(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("registerNumber") String registerId,
			@RequestParam("email") String email,
			Model model) {
		
		String encodedPassword = passwordEncoder.encode(password);
		
		realtorBO.addRealtor(name, registerId, loginId, encodedPassword, email);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		
		return result;
	}
}
