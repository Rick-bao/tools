package com.rick.tools.xml.test;

import org.junit.Test;

import com.rick.tools.xml.XmlDocument;
import com.rick.tools.xml.common.ConDefine;
import com.rick.tools.xml.dom4j.Dom4jForXML;

public class XMLTest {
	
	/**
	 * Dom4j测试方法入口
	 */
	@Test
	public void dom4jTest() {
		XmlDocument document = new Dom4jForXML();
		//document.parseXML(ConDefine.BK_PATH);
		
//		document.parseXML(ConDefine.DJ_PATH);
		
		document.parseXML(ConDefine.BASE_PATH+ConDefine.FILE_NAME_XML);
	}

	/**
	 * SAX测试方法入口
	 */
	@Test
	public void saxTest(){
		
	}
}
