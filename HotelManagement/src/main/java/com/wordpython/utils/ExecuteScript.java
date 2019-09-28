package com.wordpython.utils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
/**
 * 连接js的类，将字符串传给js加密
 * @author wordpython
 *
 */
public class ExecuteScript {
    public String made(String str){
    	String encoded=null;
    	//创建一个脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
        //获取一个指定的名称的脚本引擎
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            //获取当前类的所在目录的路径
//            String path = ExecuteScript.class.getResource("").getPath();
        	String path="D:\\Java_web\\worksplace\\RookiesThree\\WebContent\\js\\";
            // FileReader的参数为所要执行的js文件的路径
            engine.eval(new FileReader(path + "conwork.js"));  //
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                //从脚本引擎中返回一个给定接口的实现
                Methods executeMethod = invocable.getInterface(Methods.class);
                //执行指定的js方法
                encoded=executeMethod.encodeInp(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return encoded;
    }
}