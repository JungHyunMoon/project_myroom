package com.myroom.realtor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myroom.realtor.bo.RealtorBO;
import com.myroom.realtor.model.Realtor;

@RestController
public class RealtorRestController {

	@Autowired
	private RealtorBO realtorBO;
	
	@PostMapping("/get_realtor_Info")
	public Map<String, Object> getrealtorInfo(
			@RequestParam("realtorId") int realtorId) {
		
		Map<String, Object> result = new HashMap<>();
		
		Realtor realtor = realtorBO.getRealtorById(realtorId);
		result.put("code", 1);
		result.put("email", realtor.getEmail());
		result.put("name", realtor.getName());
		result.put("loginId", realtor.getLoginId());
		return result;
	}
}
