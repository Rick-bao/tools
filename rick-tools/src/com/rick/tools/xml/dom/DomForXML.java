package com.rick.tools.xml.dom;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.rick.tools.xml.model.BookStoreModel;

/**
 * 
 * DOM解析XML
 * @author Rick
 * @date 2016年3月9日下午10:41:48
 */
public class DomForXML {
	/**
	 * 获取图书信息方法
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public List<BookStoreModel> getBooks(InputStream inputStream) throws Exception{  
        List<BookStoreModel> list = new ArrayList<BookStoreModel>();  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder = factory.newDocumentBuilder();  
        Document document = builder.parse(inputStream);  
        Element element = document.getDocumentElement();  
          
        NodeList bookNodes = element.getElementsByTagName("book");  
        for(int i=0;i<bookNodes.getLength();i++){  
            Element bookElement = (Element) bookNodes.item(i);  
            BookStoreModel book = new BookStoreModel();  
//            book.setId(Integer.parseInt(bookElement.getAttribute("id")));  
            NodeList childNodes = bookElement.getChildNodes();  
//          System.out.println("*****"+childNodes.getLength());  
            for(int j=0;j<childNodes.getLength();j++){  
                if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){  
                    if("title".equals(childNodes.item(j).getNodeName())){  
                        book.setTitle(childNodes.item(j).getFirstChild().getNodeValue());  
                    }
                    if("author".equals(childNodes.item(j).getNodeName())){  
                        book.setAuthor(childNodes.item(j).getFirstChild().getNodeValue());  
                    }
                    if("year".equals(childNodes.item(j).getNodeName())){  
                        book.setYear(childNodes.item(j).getFirstChild().getNodeValue());  
                    }
                    if("price".equals(childNodes.item(j).getNodeName())){  
                    	book.setPrice(childNodes.item(j).getFirstChild().getNodeValue());  
                    }  
                }  
            }//end for j  
            list.add(book);  
        }//end for i  
        return list;  
    }  
}
