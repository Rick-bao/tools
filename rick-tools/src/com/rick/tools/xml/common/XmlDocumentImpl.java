package com.rick.tools.xml.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.rick.tools.xml.XmlDocument;

/**
 * 
 * XML文档操作公共实现类
 1.创建新文件
 2.组装文件格式
 3、输出xml内容到文件中
 * @author Rick
 * @date 2016年3月8日下午1:14:35
 */
public class XmlDocumentImpl implements XmlDocument {

	@Override
	public void parseXML(String filePath) {}
	
	@Override
	public boolean createXML(String filePath) {
		//-- 创建新文件
		if(createFile(filePath)!=null){
			if(writeXML(createFile(filePath)))
			return true;
		}
		return false;
	}

	/**
	 * 写出XML内容到文件
	 * @return
	 */
	private boolean writeXML(File file){
		FileWriter out = null;
		BufferedWriter writer = null;
		try {
			out = new FileWriter(file);
			writer = new BufferedWriter(out);
			writer.write(assemblingXML().toString());
			writer.flush();
			try {
				if(writer !=null)
					writer.close();
				if(out !=null)
					out.close();
				return true;
			} catch (IOException e1) {}
		} catch (IOException e) {
			try {
				if(writer !=null)
					writer.close();
				if(out !=null)
					out.close();
			} catch (IOException e1) {}
		}
		return false;
	}
	
	/**
	 * 组装XML内容
	 * @return
	 */
	private StringBuilder assemblingXML(){
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version='1.0' encoding='UTF-8'?>\n");
		builder.append("<hibernate-configuration>");
		builder.append("\n\t<session-factory>");
		builder.append("\n\t\t<property name=\"show_sql\">true</property><!-- 显示SQL语句 -->");
		builder.append("\n\t\t<property name=\"max_fetch_depth\">1</property><!-- 未知 -->");
		builder.append("\n\t\t<property name=\"jdbc.fetch_size\">5</property><!-- 未知 -->");
		builder.append("\n\t\t<property name=\"connection.url\">jdbc:mysql://localhost:3306/myuser?useUnicode=true&amp;characterEnconding=GB2312</property><!-- 连接数据库字符串 -->");
		builder.append("\n\t\t<property name=\"connection.username\">****</property><!-- 数据库用户名 -->");
		builder.append("\n\t\t<property name=\"connection.password\">****</property><!-- 数据库密码 -->");
		builder.append("\n\t\t<property name=\"connection.driver_class\">com.mysql.jdbc.Driver</property><!-- 驱动路径 -->");
		builder.append("\n\t\t<property name=\"hibernate.dialect\">org.hibernate.dialect.MySQLDialect</property><!-- 数据库方言 -->");
		builder.append("\n\t\t<mapping resource=\"com/test/myhibernate/dto/user.hbm.xml\" /><!-- hbm.xml文件配置路径 -->");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("");
		builder.append("\n\t</session-factory>");		
		builder.append("\n</hibernate-configuration>");
		return builder;
	}
	
	/**
	 * 创建XML标签 - 备用
	 * @param beginTag
	 * @param propties
	 * @param endTag
	 */
//	private String tagXML(String beginTag,String propties,String endTag){
//		
//		return null;
//	}
	
	/**
	 * 创建XML新文件
	 * @return
	 */
	private File createFile(String filePath){
		File file = new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(file.canRead()&&file.canWrite()){
			return file;
		}
		return null;
	}
	
	//------------------- 
	public static void main(String[] args) {
		/*String classPath = System.getProperty("user.dir");
		
		System.err.println("---- "+classPath);*/
		
		XmlDocument documentImpl = new XmlDocumentImpl();
		documentImpl.createXML(ConDefine.BASE_PATH+ConDefine.FILE_NAME_XML);
		System.err.println("---- 创建成功");
	}
}
