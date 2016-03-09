package com.rick.tools.xml.dom4j;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.rick.tools.xml.XmlDocument;

/**
 * 
 * DOM4J解析XML文件
 * @author Rick
 * 2016年3月7日上午11:09:41
 */
public class Dom4jForXML implements XmlDocument {
	
	/**
	 * 解析XML方法
	 * @param filePath 文件路径
	 */
	@SuppressWarnings({ "unchecked" })
	public void parseXML(String filePath) {
		SAXReader saxReader = new SAXReader();

        Document document=null;
		try {
			document = saxReader.read(new File(filePath));
		} catch (DocumentException e1) {			
			e1.printStackTrace();
		}

        // 获取根元素
        Element root = document.getRootElement();
        System.out.println("---------------\nRoot: " + root.getName());

        // 获取所有子元素大小
        List<Element> childList = root.elements();
        System.out.println("total child count: " + childList.size());

        for (Element element : childList) {
        	if (root.getName().trim().equals("bookstore")) {
				//------- 图书信息
				System.err.println("\ntitle ++ "+ element.elementTextTrim("title"));
				System.err.println("author ++ "+ element.elementTextTrim("author"));
				System.err.println("year ++ " + element.elementTextTrim("year"));
				System.err.println("price ++ "+ element.elementTextTrim("price"));
			}
        	if(root.getName().trim().equals("document")){
        		//---- dom4j文件
        		
        		if (element.elementTextTrim("title") != null && element.elementTextTrim("author")!=null) {
					System.err.println("\ntitle -- "
							+ element.elementTextTrim("title"));
					System.err.println("author -- "
							+ element.elementTextTrim("author"));
				}
				List<Element> child = element.elements("section");
				System.out.println("\n******* "+child.size());
        		for (Element el : child) {
        			System.err.println("--------- * "+el.getName());
        			System.err.println("\n -- name : "+el.attributeValue("name"));
        			System.err.println("p -- "+el.elementTextTrim("p"));
        			List<Element> a = el.elements("p");
        			for (Element ae : a) {
        				System.err.println("a -- "+ae.elementTextTrim("a"));						
					}
        			List<Element> pre = el.elements("section");
        			for (Element pe : pre) {
        				System.err.println("pre -- "+pe.elementTextTrim("pre"));						
					}
				}
        	}
        	//------hibernate.xml文件
        	if(root.getName().trim().equals("hibernate-configuration")){
        		System.out.println("--------------------------------------\n");
        		List<Element> ars = element.elements();
        		for (Element hib : ars) {
        			//System.err.println("-------- * "+hib.getName());
        			if(hib.getName().trim().equals("property"))
        				System.err.println(hib.attributeValue("name")+" : "+hib.getStringValue());
        			if(hib.getName().trim().equals("mapping"))
        				System.err.print(hib.attributeValue("resource")+" : "+element.elementTextTrim("mapping"));
				}
        	}
		}
        
	}

	@Override
	public boolean createXML(String filePath) {
		return false;
	}
}
