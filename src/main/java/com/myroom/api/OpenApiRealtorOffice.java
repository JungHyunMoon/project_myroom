package com.myroom.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class OpenApiRealtorOffice {

	public JSONObject isRegisteredNumber(String name, String registerNumber) {
		// StringBuffer 객체는 .append를 통해 계속해서 문자열을 추가해 나갈 수 있다.
		StringBuffer resultBr = new StringBuffer();
		try {
			// 강제로 파라미터 URL encoding
			registerNumber = URLEncoder.encode(registerNumber, "UTF-8");
			name = URLEncoder.encode(name, "UTF-8");
			String urlstr = "http://apis.data.go.kr/1611000/nsdi/EstateBrkpgService/attr/getEBBrokerInfo?"
					+ "ServiceKey=OKYVi80dgDiW8IDno3fxQ4t28RkLne%2BbVPbqtA4WtTj%2B772kQaCEd3nCRo4EEoG1aUI8F2VUc0KI3e8cq1L%2FGw%3D%3D"
					+ "&format=JSON" 
					+ "&jurirno=" + registerNumber
					+ "&brkrNm=" + name;

			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			
			// UTF-8로 해놨음에도 불구하고 한글 깨짐 Unexpected character
//			urlconnection.setRequestProperty("content-type", "application/json;  charset=utf-8"); // 역시 안됨

			// Buffer
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
		// br -> string
		String jsonStr = resultBr.toString();
		Object obj = null;
		// string -> obj
		try {
			obj = parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = (JSONObject) obj;
		Object key = "EBBrokers";
		JSONObject result = (JSONObject) jsonObj.get(key);

		return result;
	}
}