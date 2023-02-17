package com.myroom.realEstate.bo;

import java.util.ArrayList;
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
	
	public List<RealEstate> getRealEstateList() {
		return realEstateDAO.getRealEstateList();
	}
	public List<Map<String, Object>> getRealEstateInfoByLocal2(String local) {
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
}
