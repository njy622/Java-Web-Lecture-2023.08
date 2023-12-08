package com.human.sample.restApi;

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

public class KakaoLocal {

	public static void main(String[] args) throws Exception { 	// IO 작업할때는 무조건 throws가 들어간다!
		String keyfile = "D:/WorkSpace/02.DataAnalysis/08.Prototype/static/keys/카카오apiKey.txt";		// properties에 넣어놓고 불러와도 된다
		InputStream is = new FileInputStream(keyfile);
		byte[] buffer = new byte[80];
		while (true) {
			int num = is.read(buffer);
			if (num == -1)
				break;
		}
		is.close(); 
		//System.out.println(Arrays.toString(buffer));  //실행 (확인) run->java application
		String kakaoKey = new String(buffer).substring(0, 32); 	
		
		// 카카오 로컬 API - 키워드로 장소 검색하기
		String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json";
		// 서울시 강남구 삼성동 10km 반경에서 카카오프렌즈 매장 검색
		double lat = 37.514323;
		double lng = 127.062831;
		String encodedQuery = URLEncoder.encode("카카오프렌즈", "utf-8"); // URLEncoder : 자바 한글 인코딩 (quote)
		String rawUri = apiUrl + "?y=" + lat + "&x=" + lng + "&radius=10000&query=" + encodedQuery ;   // &radius=10000 : 10km 반경 
		// System.out.println(rawUri);
		URI uri = new URI(rawUri);
		
		// Header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "KakaoAK " + kakaoKey);		// 카카오 페이지에 있는대로 스펠링,blank 모두 제대로 넣어야함!
		
		// RestTemplate
		RestTemplate rest = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);		// new HttpEntity<>() : 뒷부분에서 <String> 생략가능
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.GET, entity, String.class);  // header가 있어서 exchange를 사용
		
		//System.out.println(response.getStatusCode());
		//System.out.println(response.getBody());  	// getBody() : json 그자체
		
		JSONParser json = new JSONParser();
		// {"documents":[{"address_name":"서울 강남구 삼성동 159", => { (Object), [ (Array)
		JSONObject obj = (JSONObject) json.parse(response.getBody().toString());  // (JSONObject) 으로 타입캐스팅해줘야함
		JSONArray documents = (JSONArray) obj.get("documents"); 
		for (int i = 0; i < documents.size(); i++) {
			JSONObject docu = (JSONObject) documents.get(i);
			String address_name = (String) docu.get("address_name");
			String place_name = (String) docu.get("place_name");
			String phone = (String) docu.get("phone");
			System.out.println(place_name + ", " + address_name + ", " + phone);
		}
	}

}
