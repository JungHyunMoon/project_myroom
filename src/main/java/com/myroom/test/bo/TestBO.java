package com.myroom.test.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.test.TestDAO.TestDAO;

@Service
public class TestBO {

	@Autowired
	private TestDAO testDAO;
	
	public int test() {
		return testDAO.test();
	}
}
