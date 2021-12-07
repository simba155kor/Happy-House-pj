package com.happyhouse.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.happyhouse.news.NewsDetailDto;
import com.happyhouse.news.NewsDetailParser;
import com.happyhouse.news.NewsDto;
import com.happyhouse.news.NewsList;

@RestController
@RequestMapping("/news")
public class NewsController {
	
	
	
//	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
//	private static final String SUCCESS = "success";
//	private static final String FAIL = "fail";
	
	List<NewsDto> list;
//	
	@GetMapping
	public ResponseEntity<List<NewsDto>> getNews() throws IOException {
//		NewsApi api = new NewsApi();
//		String text = "부동산";
//		String response = api.search(text);
//		list = NewsParser.getList(response);
//		
		
		list = NewsList.getList();
		
		
		return new ResponseEntity<List<NewsDto>>(list, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/{newsNo}")
	public ResponseEntity<NewsDetailDto> getDetail(@PathVariable("newsNo") int newsNo) throws IOException {
		
		
		NewsDetailDto dto = new NewsDetailDto();
		String link = list.get(newsNo).getLink();
		System.out.println(list.get(newsNo).getTitle());
		System.out.println(link);
		
		NewsDetailParser ndp = new NewsDetailParser();
		dto = ndp.getNews(link);
		
		
		return new ResponseEntity<NewsDetailDto>(dto, HttpStatus.OK);
		
		
		
		
	}
	
	
	
	
	
	
	
	
}
