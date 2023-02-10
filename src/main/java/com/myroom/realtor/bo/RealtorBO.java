package com.myroom.realtor.bo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myroom.api.OpenApiRealtorOffice;
import com.myroom.realtor.dao.RealtorDAO;

@Service
public class RealtorBO {

	@Autowired
	private RealtorDAO realtorDAO;
	@Autowired
	private OpenApiRealtorOffice openApiRealtorOffice;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void addRealtor(String name, String registerId, String loginId, String encodedPassword, String email) {
		realtorDAO.insertRealtor(name, registerId, loginId, encodedPassword, email);
	}
	
	public int getRegisterdRealtorCountByRealtorNameRegisterNumber(String realtorName, String registerNumber) {
		JSONObject realtorJSON = openApiRealtorOffice.isRegisteredNumber(realtorName, registerNumber);
		int totalCount = Integer.valueOf((String)realtorJSON.get("totalCount"));
		
		return totalCount;
	}
	
	public String getRealtorPasswordByLoginId(String loginId) {
		return realtorDAO.selectRealtorByLoginId(loginId).getPassword();
	}
}
