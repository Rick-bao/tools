package com.rick.tools.xml.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.rick.tools.xml.XmlDocument;
import com.rick.tools.xml.common.ConDefine;
import com.rick.tools.xml.dom4j.Dom4jForXML;
import com.rick.tools.xml.sax.SaxForXML;

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
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	@Test
	public void saxTest() throws SAXException, IOException, ParserConfigurationException{
		// step 1: 获得SAX解析器工厂实例
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // step 2: 获得SAX解析器实例
        SAXParser parser = factory.newSAXParser();

        // step 3: 开始进行解析
        // 传入待解析的文档的处理器
        parser.parse(new File(ConDefine.BK_PATH), new SaxForXML());
	}
}
