package org.geojson.httpclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	private static Log log = LogFactory.getLog(HttpUtils.class);
	
	public static String sendGetUTF8(String url, String authorization) {
		BufferedReader br = null;
		StringBuffer result = new StringBuffer("");
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36");
			connection.setRequestProperty("Authorization", authorization);
			
			// 是否允许输入输出
			connection.setDoInput(true);
			
			// 提交模式
			connection.setRequestMethod("GET");
			
			// 开始连接请求
			connection.connect();
			br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			//br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			br.close();
			
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e);
			}
		}
		
		return result.toString();
	}
	
	
	/**
	 * 发送请求到指定服务器
	 * 
	 * @param url 请求地址
	 */
	public static String sendPost(String url, String jsonData, String authorization) {
		System.out.println(jsonData);
		
		BufferedWriter bw = null;
		BufferedReader br = null;
		StringBuffer result = new StringBuffer("");
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			connection.setRequestProperty("Connection", "keep-alive");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3218.0 Safari/537.36");
			connection.setRequestProperty("Authorization", authorization);
			
			// 是否允许输入输出
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			// 提交模式
			connection.setRequestMethod("POST");
			
			// 开始连接请求
			connection.connect();
			
			bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			bw.write(jsonData);
			bw.flush();
			bw.close();
			
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			br.close();

		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error(e);
			}
		}
		
		return result.toString();
	}

	public static String sendHttpPost(String url, String body) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
		httpPost.setEntity(new StringEntity(body,"UTF-8"));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		System.out.println(response.getStatusLine().getStatusCode() + "\n");
		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8"); 
		//System.out.println(responseContent);

		response.close();
		httpClient.close();
		return responseContent;
		}

	
}
