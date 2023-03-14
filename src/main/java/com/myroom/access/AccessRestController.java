package com.myroom.access;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myroom.access.bo.AccessBO;
import com.myroom.common.EncryptUtils;
import com.myroom.realtor.bo.RealtorBO;
import com.myroom.realtor.model.Realtor;
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
			Model model,
			HttpSession session) {
		
		String hashedPassword = EncryptUtils.md5(password);
		
		Map<String, Object> result = new HashMap<>();
		User user = userBO.getUserByLoginIdPassword(loginId, hashedPassword);
		if (user != null) {
			session.setAttribute("userId", user.getId());
			session.setAttribute("loginId", user.getLoginId());
			session.setAttribute("type", "basicUser");
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
			@RequestParam("password") String password,
			Model model,
			HttpSession session) {
		
		Realtor realtor = realtorBO.getRealtorByLoginId(loginId);
		boolean isMatches = passwordEncoder.matches(password, realtor.getPassword());
		
		Map<String, Object> realtorInfo = realtorBO.getRegisterdRealtorInfoByRealtorNameRegisterNumber(realtor.getName(), realtor.getRegisterId());
		String address = (String) realtorInfo.get("address");
		String[] temp = address.split("\\s+");
		String local2 = temp[temp.length -1];
		
		Map<String, Object> result = new HashMap<>();
		if (isMatches == true) {
			session.setAttribute("realtorId", realtor.getId());
			session.setAttribute("loginId", realtor.getLoginId());
			session.setAttribute("local2", local2);
			session.setAttribute("type", "realtor");
			result.put("code", 2);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "중개인의 아이디 또는 비밀번호를 확인하세요.");
		};
		
		
		return result;
	}
	
	@PostMapping("/sign_up_user")
	public Map<String, Object> signUpUser(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("email") String email,
			Model model) {
		
		String hashedPassword = EncryptUtils.md5(password);
		
		userBO.addUser(loginId, hashedPassword, name, phoneNumber, email);
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("result", "성공");
		
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
	
	@PostMapping("/isRegisterdRealtor")
	public Map<String, Object> isRegisterdRealtor(
			@RequestParam("realtorName") String realtorName,
			@RequestParam("registerNumber") String registerNumber
			) {
		
		Map<String, Object> result = new HashMap<>();
		
		int totalCount = accessBO.getRealtorTotalCount(realtorName, registerNumber);
		result.put("result", totalCount);
		
		return result;
	}
	
	@PostMapping("/isAlreadyExistId")
	public Map<String, Object> isAlreadyExistId(
			@RequestParam("loginId") String loginId
			) {
		boolean isRegisterdId = accessBO.isAlreadyExistId(loginId);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("result", isRegisterdId);
		
		return result;
	}
	
	@GetMapping("/items/{realEstateId}")
	public Map<String, Object> itemsDetail(
			@PathVariable int realEstateId,
			Model model) {
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", realEstateId);
		return result;
	}
	
}
