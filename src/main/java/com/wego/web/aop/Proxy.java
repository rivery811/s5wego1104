package com.wego.web.aop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.wego.web.utl.Printer;

import lombok.Data;

@Data 
@Component
@Lazy
public class Proxy {
	private int  pageNum;
	private String search;
	@Autowired Printer printer;
	//@Autowired List<String> pxylist;
	
	public List<?> crawl(Map<?,?> paramMap){// 디비를 안감 
		String url = "http://"+paramMap.get("site")+"/";
		printer.accept("넘어온 url"+url);
		List<String> pxylist= new ArrayList<String>();
		pxylist.clear();
		try {
			Connection.Response response =Jsoup.connect(url)
					.method(Connection.Method.GET)
					.execute();
			Document document =response.parse();
			String text = document.html();
			printer.accept("크롤링한텍스트"+text);
			pxylist.add(text);
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return pxylist;
	}
}
