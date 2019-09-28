package com.wordpython.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
 * 获取article
 */
public class content {
	public static String getContent(String url) throws IOException {
		Map<String,String> map=new HashMap<String,String>();
		Connection con=Jsoup.connect(url);
		con.header("User-Agent",
	              "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
		Response login = con.ignoreContentType(true).method(Method.POST).data(map).timeout(1160000).execute();//报错：Read timeout，则延长timeout(60000)
		Document doc = Jsoup.parse(login.body());
		Elements article = doc.select("article");
		return article.toString();
	}
//	public static void main(String[] args) throws IOException {
//		String url="https://www.washingtonpost.com/politics/2019/03/08/manafort-isnt-out-woods-yet-fact-now-his-lies-mueller-could-cost-him-dearly/?";
//		System.out.println(getContent(url));
//	}
	
}
