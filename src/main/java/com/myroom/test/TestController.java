package com.myroom.test;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myroom.api.OpenApiRealtorOffice;
import com.myroom.realtor.bo.RealtorBO;
import com.myroom.test.bo.TestBO;

@Controller
public class TestController {
	
	@Autowired
	private TestBO testBO;
	@Autowired
	private RealtorBO agentBO;
	@Autowired
	private OpenApiRealtorOffice openApiRealtorOffice;
	
	@ResponseBody
	@RequestMapping("/test1")
	public String helloWorld() {
		return "Hello world! by myRoom 한글설마?";
	}
	
	@ResponseBody
	@RequestMapping("/test2")
	public JSONObject helloJSON() {
		String registerNumber = "123123";
		String name = "문정현";
		JSONObject result = openApiRealtorOffice.isRegisteredNumber(name, registerNumber);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/test3")
	public int dbTest() {
		
		return testBO.test();
	}
	
	@RequestMapping("test4")
	public String jspTest() {
		return "/test";
	}
}