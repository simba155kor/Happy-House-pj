package com.happyhouse.news;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
 


public class NewsParser {
	

//	public static List<NewsDto> getList(String response) {
//		
////		
////		JSONObject jObject = new JSONObject(response);
////		JSONArray jArray = jObject.getJSONArray("items");
////		List<NewsDto> list = new ArrayList<>();
////		for(int i = 0; i < jArray.length(); i++) {
////			JSONObject item = jArray.getJSONObject(i);
////			
////			String title = item.getString("title");
////			String url = item.getString("link");
////			String description  = item.getString("description");
////			
//////			NewsDto news = new NewsDto(title, url, description);
////			list.add(news);
////			
////			
////		}
////		return list;
//	}
	
}
