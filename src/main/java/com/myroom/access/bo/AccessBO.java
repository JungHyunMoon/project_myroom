package com.myroom.access.bo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.api.OpenApiRealtorOffice;
import com.myroom.realtor.dao.RealtorDAO;
import com.myroom.user.dao.UserDAO;

@Service
public class AccessBO {
	
	@Autowired
	private OpenApiRealtorOffice openApiRealtorOffice;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RealtorDAO realtorDAO;
	
	public int getRealtorTotalCount(String realtorName, String registerNumber) {
		JSONObject realtorJSON = openApiRealtorOffice.isRegisteredNumber(realtorName, registerNumber);
		int totalCount = Integer.valueOf((String)realtorJSON.get("totalCount"));
		
		return totalCount;
	}
	
	public boolean isAlreadyExistId(String loginId) {
		boolean result = false;
		if(userDAO.selectUserByLoginId(loginId) != null) {
			result = true;
		};
		if(realtorDAO.selectRealtorByLoginId(loginId) != null) {
			result = true;
		};
		
		return result;
	}
}
