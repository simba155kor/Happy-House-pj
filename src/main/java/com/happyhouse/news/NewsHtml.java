package com.happyhouse.news;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NewsHtml {

	private String articleTitle;
	
	private String articleBodyContent;

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleBodyContent() {
		return articleBodyContent;
	}

	public void setArticleBodyContent(String articleBodyContent) {
		this.articleBodyContent = articleBodyContent;
	}
	
	
	public static String getNewsHtml(String link) throws IOException {
		Document doc = Jsoup.connect(link).get();
		System.out.println("====================");
//		System.out.println(doc);
		/*
		 *  class="tts_head"   article_info
		 *  class="_article_body_contents article_body_contents"
		 */
		Elements title = doc.select(".tts_head");
		System.out.println(title);
		
		return null;
	}
	
	
}
