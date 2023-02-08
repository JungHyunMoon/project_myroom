package com.myroom.access;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myroom.access.bo.AccessBO;
import com.myroom.api.OpenApiRealtorOffice;
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
	private OpenApiRealtorOffice openApiRealtorOffice;
	
	/*
	 * 
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			Model model) {
		
		String hashedPassword = EncryptUtils.md5(password);
		
		User user = userBO.getUserByLoginIdPassword(loginId, hashedPassword);
		Map<String, Object> result = new HashMap<>();
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
			@RequestParam("name") String name,
			@RequestParam("registerNumber") String registerId,
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			Model model) {
		
		String hashedPassword = EncryptUtils.md5(password);
		
		realtorBO.addRealtor(name, registerId, loginId, hashedPassword, email);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		
		return result;
	}
}
