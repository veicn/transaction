package com.sinochem.crude.trade.order.util;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
/**
 * java实现发送http请求公共类
 * Copyright (C) 2016 LiuXuetang
 * All rights reserved
 * Author:bbt
 * Version:0.0.1
 */
public class NetUtil {

	/**
	 * 发送get请求
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String get(String url) throws IOException {
		InputStream is = sendGetRequest(url);
		return new String(readStream(is),"UTF-8");
	}

	/**
	 * 发送post请求
	 * <p>
	 *     表单格式提交
	 * </p>
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, Map<String,String> params) throws IOException {
		InputStream is = sendPostRequestWithParams(url, params);
		return new String(readStream(is),"UTF-8");
	}

	/**
	 * 发送post请求
	 * <p>
	 *     json格式提交
	 * </p>
	 * @param path
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static String post(String path, JSONObject params) throws IOException {
		byte[] data = params.toString().getBytes("UTF-8");
		URL url = new URL(path);
		HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
		httpUrl.setRequestMethod("POST");
		httpUrl.setDoOutput(true);//post请求 必须设置为true
		httpUrl.setUseCaches(false);
		httpUrl.setConnectTimeout(6000);
		httpUrl.setReadTimeout(6000);
		httpUrl.setChunkedStreamingMode(0);//分块协议
		httpUrl.setRequestProperty("Accept-Language", "zh-cn");
		httpUrl.setRequestProperty("Content-Type", "application/json");
		httpUrl.setRequestProperty("Content-Length", String.valueOf(data.length));
		httpUrl.setRequestProperty("Connection", "Keep-Alive");
		httpUrl.setRequestProperty("Cache-Control", "no-cache");
		httpUrl.connect();
		//数据流对象
		DataOutputStream dos=new DataOutputStream(httpUrl.getOutputStream());
		dos.write(data);//将请求参数 写入到网络中
		dos.close();
		InputStream is = httpUrl.getInputStream();
		return new String(readStream(is), "UTF-8");
	}

   /**
    * 普通方式发送get请求
    * @param path 请求路劲
    * @return InputStream
    * @throws Exception
    */
	private static InputStream sendGetRequest(String path) throws IOException {
		URL url=new URL(path);
		HttpURLConnection httpUrl=(HttpURLConnection) url.openConnection();
		httpUrl.setRequestMethod("GET");
		httpUrl.setConnectTimeout(6000);
		httpUrl.setReadTimeout(6000);
		httpUrl.setChunkedStreamingMode(0);//分块协议
		if(httpUrl.getResponseCode()==200){
			return httpUrl.getInputStream();
		}
		return null;
	}

	/**
	 * 普通方式实现post请求并携带参数
	 * @param path 请求路劲
	 * @param params  请求参数
	 * @return 输入流对象
	 * @throws Exception
     */
	private static InputStream sendPostRequestWithParams(String path,Map<String,String> params) throws IOException {
		URL url=new URL(path);
		HttpURLConnection httpUrl= (HttpURLConnection) url.openConnection();
		httpUrl.setRequestMethod("POST");
		httpUrl.setDoOutput(true);//post请求 必须设置为true
		httpUrl.setUseCaches(false);
		httpUrl.setConnectTimeout(6000);
		httpUrl.setReadTimeout(6000);
		httpUrl.setChunkedStreamingMode(0);//分块协议

		StringBuffer sb=new StringBuffer();
		for(Map.Entry<String, String> entry  : params.entrySet())
		{
		   sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		sb.deleteCharAt(sb.length()-1);
		httpUrl.setRequestProperty("Accept-Language", "zh-cn");
		httpUrl.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpUrl.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		httpUrl.setRequestProperty("Content-Length", String.valueOf(sb.toString().getBytes().length));
		httpUrl.setRequestProperty("Connection", "Keep-Alive");
		httpUrl.setRequestProperty("Cache-Control", "no-cache");

		//数据流对象
		DataOutputStream dos=new DataOutputStream(httpUrl.getOutputStream());
		dos.write(sb.toString().getBytes("UTF-8"));//将请求参数 写入到网络中
		dos.close();
		/*if(httpUrl.getResponseCode()!=200) {
			throw new Exception("请求Url失败");
		}*/
		return httpUrl.getInputStream();
	}

	/**
	 * 读取 输入流中数据
	 * @param is  输入流对象
	 * @return 二进制数据
	 * @throws Exception
     */
	private static byte[] readStream(InputStream is) throws IOException {
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		byte [] data=new byte[8192];//每次读8k
		int len=-1;
		while((len=is.read(data))!=-1){
		bos.write(data, 0, len);
		}
		byte [] d=bos.toByteArray();
		is.close();
		bos.close();
		return d;
	}
}
