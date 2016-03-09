package com.rick.tools.xml.common;

/**
 * 
 * 公共字段定义
 * @author Rick
 * @see 2016年3月9日下午6:04:06
 */
public interface ConDefine {
	
	
	//--基础解析文件路径
	static final String CLASS_PATH = System.getProperty("user.dir");
	static final String BASE_PATH = CLASS_PATH+"/sources/";
	static final String BK_PATH = BASE_PATH+"bookstore.xml";//--bookstore.xml 文件绝对路径
	static final String DJ_PATH = BASE_PATH+"dom4j.xml";//--dom4j.xml 文件绝对路径
	//--- 创建XML文件定义
	static final String FILE_NAME_XML = "hibernate.xml";
	
	
}
