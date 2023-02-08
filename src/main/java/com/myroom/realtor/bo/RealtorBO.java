package com.myroom.realtor.bo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.api.OpenApiRealtorOffice;
import com.myroom.realtor.dao.RealtorDAO;

@Service
public class RealtorBO {

	@Autowired
	private RealtorDAO realtorDAO;
	
	@Autowired
	private OpenApiRealtorOffice openApiRealtorOffice;
	
	public void addRealtor(String name, String registerId, String loginId, String hashedPassword, String email) {
		realtorDAO.insertRealtor(name, registerId, loginId, hashedPassword, email);
	}
	
	public int getRegisterdRealtorCountByRealtorNameRegisterNumber(String realtorName, String registerNumber) {
		JSONObject realtorJSON = openApiRealtorOffice.isRegisteredNumber(realtorName, registerNumber);
		int totalCount = Integer.valueOf((String)realtorJSON.get("totalCount"));
		
		return totalCount;
	}
}
