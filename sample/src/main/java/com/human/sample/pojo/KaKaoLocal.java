package com.human.sample.pojo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class KaKaoLocal {
	
	public static void main(String[] args) throws Exception {
		String keyfile = "D:/WorkSpace/02.DataAnalysis/08.Prototype/static/keys/카카오apikey.txt";
		InputStream is = new FileInputStream(keyfile);
		byte[] buffer = new byte[80];
		while (true) {
			int num = is.read(buffer);
			if (num == -1)
				break;
		}
		is.close();
		//System.out.println(Arrays.toString(buffer));
		String kakaoKey = new String(buffer).substring(0,32);
		
		// 카카오 로컬 API - 키워드로 장소 검색하기
		String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json";
		// 서울시 강남구 삼성동 10km 반경에서 카카오프렌즈 매장 검색
		double lat = 37.514323;
		double lng = 127.062831;
				
		//주소창에 한글로 검색한 값 나오게하는법 
		//파이썬에서는  인코딩 queto 자바에서는 urlencoder을 사용
		String encodedQuery =  URLEncoder.encode("카카오프렌즈", "utf-8");
		String rawUri = apiUrl + "?y=" + lat + "&x=" + lng + "&radius=10000&query=" + encodedQuery;
		// System.out.println(rawUri);
		URI uri = new URI(rawUri);
		
		// Header 만들기
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "KakaoAK " + kakaoKey);
		
		
		// RestTemplate
		RestTemplate rest = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.GET, entity, String.class);
		//response에 결과값이 옴
		
		
		// 결과값을 받아보려면..
		//System.out.println(response.getStatusCode());
		//response.getStatusCode();
		//System.out.println(response.getBody()); // 출력을 할 수 있음
		
		JSONParser json = new JSONParser();
		JSONObject obj = (JSONObject) json.parse(response.getBody().toString());
		JSONArray documents = (JSONArray) obj.get("documents");
		for  (int i = 0; i < documents.size(); i++) {
			JSONObject docu = (JSONObject) documents.get(i);
			String address_name = (String) docu.get("address_name");
			String place_name = (String) docu.get("place_name");
			String phone = (String) docu.get("phone");
			System.out.println(place_name + "," + address_name + "," + phone );
		}
	}
	
}
// Run > Java aplication으로 실행
