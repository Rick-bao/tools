package com.rick.tools.xml;

/**
 * 
 * XML操作公共接口
 * @author Rick
 * 2016年3月7日上午11:03:55
 */
public interface XmlDocument {

	/**
	 * 解析XML方法
	 * @param filePath 文件路径
	 */
	public void parseXML(String filePath);
	/**
	 * 创建XML方法
	 * @param filePath
	 * @return
	 */
	public boolean createXML(String filePath);
}
