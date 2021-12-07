package com.happyhouse.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsDetailParser {

	
	
	
	public NewsDetailDto getNews(String link) throws IOException {
		
		Document doc = Jsoup.connect(link).get();
//	
		NewsDetailDto dto = new NewsDetailDto();
		
		
//		
		Elements element = doc.select("div#main_content.content");
		for(Element el : element.select(".article_header .article_info h3#articleTitle")) {

			dto.setTitle(el.text());
		}
		
		for(Element el : element.select("span.t11")) {
			System.out.println(el.text());
			dto.setRegTime(el.text());
		}
		
		for(Element el : element.select("div#articleBodyContents._article_body_contents.article_body_contents")) {
//			System.out.println(el.text());
			dto.setContent(el.text());
			
		}
		
		
		
		
		
		
		
		
		
		return dto;
	}
}



/*

String url = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=260";
		Document doc = Jsoup.connect(url).get();
		// ul.type06_headline
		List<NewsDto> list = new ArrayList<>();
		Elements element = doc.select("ul.type06_headline");
		for(Element el: element.select("li dl dt a")) {
//			System.out.println(el);
//			Elements el2 = el.select("dt");
//			for(Element el3 : el2) {
//				
//			}
			String link = el.attr("href");
//			System.out.println(el.select("href"));
//			System.out.println(link);
			String title = el.text();
			System.out.println(title);
			if(!title.equals("")) {
				NewsDto dto = new NewsDto();
				dto.setTitle(el.text());
				dto.setLink(link);
				list.add(dto);
			}
			
			
			
			
		}
		return list;
		

*/