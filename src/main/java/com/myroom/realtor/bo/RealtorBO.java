package com.myroom.realtor.bo;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.api.OpenApiRealtorOffice;
import com.myroom.realtor.dao.RealtorDAO;
import com.myroom.realtor.model.Realtor;

@Service
public class RealtorBO {

	@Autowired
	private RealtorDAO realtorDAO;
	@Autowired
	private OpenApiRealtorOffice openApiRealtorOffice;
	
	public void addRealtor(String name, String registerId, String loginId, String encodedPassword, String email) {
		realtorDAO.insertRealtor(name, registerId, loginId, encodedPassword, email);
	}
	
	public Map<String, Object> getRegisterdRealtorInfoByRealtorNameRegisterNumber(String realtorName, String registerNumber) {
		JSONArray temp = (JSONArray) openApiRealtorOffice.callRegisteredRealtor(realtorName, registerNumber).get("field");
		JSONObject realtorJSON = (JSONObject) temp.get(0);
		Map<String, Object> result = new HashMap<>();
		result.put("name", realtorJSON.get("brkrNm"));
		result.put("office", realtorJSON.get("bsnmCmpnm"));
		result.put("address", realtorJSON.get("ldCodeNm"));
		result.put("type", realtorJSON.get("ofcpsSeCodeNm"));
		return result;
	}
	
	public int getRegisterdRealtorCountByRealtorNameRegisterNumber(String realtorName, String registerNumber) {
		JSONObject realtorJSON = openApiRealtorOffice.callRegisteredRealtor(realtorName, registerNumber);
		int totalCount = Integer.valueOf((String)realtorJSON.get("totalCount"));
		
		return totalCount;
	}
	
	public Realtor getRealtorByLoginId(String loginId) {
		return realtorDAO.selectRealtorByLoginId(loginId);
	}
	
	public Realtor getRealtorById(int realtorId) {
		return realtorDAO.selectRealtorById(realtorId);
	}
}
