package com.myroom.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPI {

	@GetMapping("/testAPI")
	public Map<String, Object> callApiHttp() {
		StringBuffer resultBr = new StringBuffer();
		String temp = "9253-8848";
		try {
         String urlstr = "http://apis.data.go.kr/1611000/nsdi/EstateBrkpgService/attr/getEBBrokerInfo?" +
               "ServiceKey=OKYVi80dgDiW8IDno3fxQ4t28RkLne%2BbVPbqtA4WtTj%2B772kQaCEd3nCRo4EEoG1aUI8F2VUc0KI3e8cq1L%2FGw%3D%3D" +
               "&format=JSON" +
               "&jurirno=9253-8848";
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

			String returnLine;
			while ((returnLine = br.readLine()) != null) {
				resultBr.append(returnLine + "\n");
			}
			urlconnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONParser parser = new JSONParser();
		
		String jsonStr = resultBr.toString();
		Object obj = null;
		try {
			obj = parser.parse( jsonStr );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = (JSONObject) obj;
		Object key = "EBBrokers";
		JSONObject result = (JSONObject)jsonObj.get(key);

		return result; // Type safety 괜찮은걸까?
	}
}