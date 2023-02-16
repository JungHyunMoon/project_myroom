package com.myroom.realEstate.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myroom.realEstate.model.RealEstate;

@Repository
public interface RealEstateDAO {

	public List<RealEstate> getRealEstateList();
	
	public List<RealEstate> getRealEstateInfoByLocal2(String local);
}
