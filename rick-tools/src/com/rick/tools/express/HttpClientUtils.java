package com.rick.tools.express;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @category [httpclient网页抓取工具类]
 * @description get/post请求抓取网页内容
 * @author rick
 * @date 2016-07-02 14:26:11
 * 
 * @version v1.0
 *
 */
public class HttpClientUtils {
	private static PoolingHttpClientConnectionManager cm;
	private static String UTF_8 = "UTF-8";
	
	/**
	 * @category 连接池管理器初始化
	 */
	private static void init(){
		if(cm == null){
			cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(50);//整个连接池最大连接数
			cm.setDefaultMaxPerRoute(5);//每路由最大连接数，默认值是2
		}
	}
	
	/**
	 * 通过连接池获取HttpClient
	 * @return
	 */
	private static CloseableHttpClient getHttpClient(){
		init();
		return HttpClients.custom().setConnectionManager(cm).build();
	}
	
	/**
	 * @category get抓取
	 * @param url
	 * @return
	 */
	public static String httpGetRequest(String url){
		HttpGet httpGet = new HttpGet(url);
		return getResult(httpGet);
	}
	
	/**
	 * @category get抓取
	 * @param url
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException{
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);
		
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);
        
        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);
	}
	
	/**
	 * @category get抓取
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	public static String httpGetRequest(String url, Map<String, Object> headers, 
			Map<String, Object> params) throws URISyntaxException{
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);
		
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);
        
        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param: headers.entrySet()) {
			httpGet.addHeader(param.getKey(), (String) param.getValue());
		}
        return getResult(httpGet);
	}
	
	/**
	 * @category post抓取
	 * @param url
	 * @return
	 */
	public static String httpPostRequest(String url){
		HttpPost httpPost = new HttpPost(url);
		return getResult(httpPost);
	}
	
	/**
	 * @category post抓取
	 * @param url
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException{
		HttpPost httpPost = new HttpPost(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
		return getResult(httpPost);
	}
	
	/**
	 * @category post抓取
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String httpPostRequest(String url, Map<String, Object> headers, 
			Map<String, Object> params) throws UnsupportedEncodingException{
		HttpPost httpPost = new HttpPost(url);
		
		for (Map.Entry<String, Object> param: headers.entrySet()) {
			httpPost.addHeader(param.getKey(), (String) param.getValue());
		}
		
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        
		return getResult(httpPost);
	}
	
	/**
	 * @category 参数设置
	 * @param params
	 * @return
	 */
	private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params){
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param: params.entrySet()) {
        	pairs.add(new BasicNameValuePair(param.getKey(), (String) param.getValue()));
		}
        
        return pairs;
	}
	
	
	/**
	 * 处理Http请求
	 * @param request
	 * @return
	 */
	private static String getResult(HttpRequestBase request){
		//CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		CloseableHttpClient httpClient = getHttpClient();
		try{
			CloseableHttpResponse response = httpClient.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			System.err.println("response status is "+statusCode);
			HttpEntity entity = response.getEntity();
			if(entity!=null){
				long len = entity.getContentLength();// -1 表示长度未知
				System.err.println("entity length is(if value equal -1 that it is unknown) "+ len);
				result = EntityUtils.toString(entity,UTF_8);//-- 设置编码格式 
			}
			response.close();
		}catch(ClientProtocolException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}