package com.myroom.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myroom.agent.bo.AgentBO;
import com.myroom.test.bo.TestBO;

@Controller
public class TestController {
	
	@Autowired
	private TestBO testBO;
	@Autowired
	private AgentBO agentBO;
	
	@ResponseBody
	@RequestMapping("/test1")
	public String helloWorld() {
		return "Hello world! by myRoom 한글설마?";
	}
	
	@ResponseBody
	@RequestMapping("/test2")
	public Map<String, Object> helloJSON() {
		String registerNumber = "나92200000-51";
		String name = "강정식";
		Map<String, Object> result = agentBO.callApiHttp(registerNumber, name);
		
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