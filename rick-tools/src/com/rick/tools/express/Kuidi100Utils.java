package com.rick.tools.express;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @category 快递100通用接口封装类
 * @source [http://www.kuaidi100.com/query?type=huitongkuaidi&postid=350580493610]返回json数据
 * @date 2016-07-01
 * @author rick
 * @version v1.0
 */
public class Kuidi100Utils {
	
	/**
	 * @category 获取订单内容
	 * @url [http://www.kuaidi100.com/query?type=huitongkuaidi&postid=350580493610] -JSON字符串
	 * @return JSON格式字符串/null
	 * @throws IOException
	 */
	public static String getHttpPostContent(String url,Map<String, Object> params) throws IOException {
		
		String content = null;
		try {
			content = HttpClientUtils.httpPostRequest(url, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	/**
	 * @test 测试入口main
	 * @param args
	 */
	public static void main(String[] args) {
		String url = null;

		Map<String, Object> params = new HashMap<>();
//		params.put("postid", "350580493610");
//		params.put("type", "huitongkuaidi");
		params.put("type", "yuantong");
		params.put("postid", "881443775034378914");
		
		try {

			url = "http://www.kuaidi100.com/query";
			//-- 发送 POST 请求
			String content = Kuidi100Utils.getHttpPostContent(url,params);

			System.err.println("---------------------------------");
			System.err.println("POST content is \n"+content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
