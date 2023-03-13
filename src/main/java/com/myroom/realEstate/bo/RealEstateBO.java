package com.myroom.realEstate.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.realEstate.dao.RealEstateDAO;
import com.myroom.realEstate.model.RealEstate;

@Service
public class RealEstateBO {

	@Autowired
	private RealEstateDAO realEstateDAO;
	
	public void addRealEstate(int userId, String local1, String local2, String local3, String jibunAddress, int deposit, int monthRentPrice, String residence_type, double area, String title, String description) {
		
		// address 생성을 위한 String split 그리고 다시 join
		String[] temp = jibunAddress.split("\\s+");
		String[] arrayAddress = Arrays.copyOf(temp, temp.length -1);
		String address = String.join(" ", arrayAddress);
		
		String sales_type = "";
		if (monthRentPrice > 0) {
			sales_type = "월세";
		} else {
			sales_type = "전세";
		}
		
		realEstateDAO.insertRealEstate(userId, sales_type, deposit, monthRentPrice, area, address, jibunAddress, local1, local2, local3, title, residence_type, description);
		
//		realEstateDAO.insertComment(userId, monthRentPrice, description);
	}
	
	public List<RealEstate> getRealEstateListByUserId(int userId) {
		return realEstateDAO.selectRealEstateListByUserId(userId);
	}
	
	public List<RealEstate> getRealEstateListByRealtorId(int realtorId) {
		return realEstateDAO.selectRealEstateListByRealtorId(realtorId);
	}
	
	public List<Map<String, Object>> getRealEstateInfoByLocal3(String local) {
		List<RealEstate> realEstatesInfo =  realEstateDAO.getRealEstateInfoByLocal3(local);
		
		List<Map<String, Object>> realEstateList = new ArrayList<>();
		for (int i = 0; i < realEstatesInfo.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("zIndex", realEstatesInfo.get(i).getId());
			map.put("jibunAddress", realEstatesInfo.get(i).getJibunAddress());
			map.put("title", realEstatesInfo.get(i).getTitle());
			map.put("residencetype", realEstatesInfo.get(i).getResidence_type());
			map.put("salestype", realEstatesInfo.get(i).getSales_type());
			map.put("area", realEstatesInfo.get(i).getArea());
			realEstateList.add(map);
		}
		
		return realEstateList;
	}
	
	public List<Map<String, Object>> getRealEstateInfoByLocal2InAdressLastIndex(String address) {
		// address 생성을 위한 String split 그리고 다시 join
		String[] temp = address.split("\\s+");
		String local = temp[temp.length -1];
		
		List<RealEstate> realEstatesInfo =  realEstateDAO.getRealEstateInfoByLocal2(local);
		
		List<Map<String, Object>> realEstateList = new ArrayList<>();
		for (int i = 0; i < realEstatesInfo.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("zIndex", realEstatesInfo.get(i).getId());
			map.put("jibunAddress", realEstatesInfo.get(i).getJibunAddress());
			map.put("title", realEstatesInfo.get(i).getTitle());
			map.put("residencetype", realEstatesInfo.get(i).getResidence_type());
			map.put("salestype", realEstatesInfo.get(i).getSales_type());
			map.put("area", realEstatesInfo.get(i).getArea());
			realEstateList.add(map);
		}
		
		return realEstateList;
	}
	public RealEstate getRealEstateById(int realEstateId) {
		RealEstate realEstate = realEstateDAO.selectRealEstateById(realEstateId);
		String comment = realEstate.getRealtor_comment();
		if (comment != null && comment != "") {
			realEstate.setRealtor_comment(comment.replace("<br>", "\n"));
		}
		
		return realEstate;
	}
	
	public void deleteRoomByRealEstateId(int realEstateId) {
		realEstateDAO.deleteRoomByRealEstateId(realEstateId);
	}
	
	public List<RealEstate> getRealEstateWithNoRealtorInMylocal2(String local2) {
		return realEstateDAO.selectRealEstateWithNoRealtorInMylocal2(local2);
	}
	
	public void setRoomRealtorId(int realEstateId, int realtorId) {
		realEstateDAO.updateRoomRealtorId(realEstateId, realtorId);
	}
	
	public void setRealtorComment(int realEstateId, String comment) {
		if (comment != null && comment != "") {
			comment = comment.replace("\n", "<br>");
		}
		realEstateDAO.updateRealtorComment(realEstateId, comment);
	}
	
	public void deleteRealEstate(int realEstateId) {
		realEstateDAO.deleteRoomByRealEstateId(realEstateId);
	}
	
	public void setRoomRealtorIdNullByRealEstateId(int realEstateId) {
		realEstateDAO.updateRoomRealtorIdNullByRealEstateId(realEstateId);
	}
	
}
