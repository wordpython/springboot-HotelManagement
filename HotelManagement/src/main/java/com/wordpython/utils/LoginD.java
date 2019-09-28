package com.wordpython.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
/**
 * 登陆教务系的类
 * 分析教务系统登陆页面的js，确定登陆需要传递的参数，找到该怎么加密
 * @author wordpython
 *
 */
public class LoginD {
	public  String sendLoginD(String username,String password) throws IOException {
		String encoded=(new ExecuteScript()).made(username)+"%%%"+(new ExecuteScript()).made(password);//将加密后的用户名和密码连在一起给encoded
		Map<String,String> map=new HashMap<String,String>();
		map.put("encoded", encoded); //服务器接收的是encoded，而不是用户名和密码，因为隐藏的encoded有name,用户名和密码没有name，服务器只接收有name的
		Connection con=Jsoup.connect("http://jwxt.gduf.edu.cn/jsxsd/xk/LoginToXk");//提交表单的地址
		con.header("User-Agent",
              "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
//		 设置cookie和post上面的map数据
		Response login = con.ignoreContentType(true).method(Method.POST).data(map).timeout(20000).execute();
		String url="http://jwxt.gduf.edu.cn/jsxsd/framework/xsMain.jsp";
		String url2=login.url()+"";
		String stuNameID=null;
		if(url2.equals(url)) {  //判断是登陆成功还是被重定向到原来的登陆页面
			Document doc = Jsoup.parse(login.body());// html为内容
			Element e_title = doc.getElementById("Top1_divLoginName");// doc.getElementsByTag("title").get(0);根据标签名找title元素
			String stuNameId = e_title.text();// 获取姓名学号
			stuNameID=new LoginD().getChinese(stuNameId);
		}
		return stuNameID;
	}
	//截取姓名
	private String getChinese(String stuNameId) {
		String regex = "([\u4e00-\u9fa5]+)";
		String str="";
		Matcher matcher = Pattern.compile(regex).matcher(stuNameId);
		while (matcher.find()) {
		str+= matcher.group(0);
		}
		return str;
	}
//	public static void main(String[] args) throws IOException {
//		LoginD kk=new LoginD();
//		System.out.println(kk.sendLoginD("171543246", "xxxxxx"));
//	}
}
