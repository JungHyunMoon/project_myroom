package com.myroom.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myroom.test.bo.TestBO;

@Controller
public class TestController {
	
	@Autowired
	private TestBO testBO;
	
	@ResponseBody
	@RequestMapping("/test1")
	public String helloWorld() {
		return "Hello world! by myRoom";
	}
	
	@ResponseBody
	@RequestMapping("/test2")
	public Map<String, Object> helloJSON() {
		Map<String, Object> result = new HashMap<>();
		result.put("result1", 1);
		result.put("result2", "test");
		result.put("result3", "success");
		
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