package com.wordpython.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

public class Search {
//	public static void main(String[] args) throws IOException {
//		getSearch("china");
//	}
	public static String getSearch(String keyword) throws IOException {
		String url="https://sitesearchapp.washingtonpost.com/sitesearch-api/v2/search.json?count=20&datefilter=displaydatetime:%5B*+TO+NOW%2FDAY%2B1DAY%5D&facets.fields=%7B!ex%3Dinclude%7Dcontenttype,%7B!ex%3Dinclude%7Dname&highlight.fields=headline,body&highlight.on=true&highlight.snippets=1&query="+keyword+"&sort=&callback=angular.callbacks._0";
		Map<String,String> map=new HashMap<String,String>();
		Connection con=Jsoup.connect(url);//提交表单的地址
		con.header("User-Agent",
              "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
//		 设置cookie和post上面的map数据
		Response login = con.ignoreContentType(true).method(Method.POST).data(map).timeout(1160000).execute();//报错：Read timeout，则延长timeout(60000)
		return login.body();
	}
}
