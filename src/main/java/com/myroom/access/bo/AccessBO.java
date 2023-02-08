package com.myroom.access.bo;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.api.OpenApiRealtorOffice;

@Service
public class AccessBO {
	
	@Autowired
	private OpenApiRealtorOffice openApiRealtorOffice;
	
	public int getRealtorTotalCount(String realtorName, String registerNumber) {
		JSONObject realtorJSON = openApiRealtorOffice.isRegisteredNumber(realtorName, registerNumber);
		int totalCount = Integer.valueOf((String)realtorJSON.get("totalCount"));
		
		return totalCount;
	}
}
