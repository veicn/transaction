package com.sinochem.crude.trade.wechat.constant;


import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.mysql.jdbc.StringUtils;
import com.sinochem.crude.trade.wechat.domain.AccessToken;
import com.sinochem.crude.trade.wechat.domain.AccessTokenClazz;
import com.sinochem.crude.trade.wechat.service.AccessTokenService;
import com.sinochem.crude.trade.wechat.service.impl.AccessTokenServiceImpl;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;

/**
 * 向redis中定时存放access_token
 * Wh
 * 2018年2月28日
 * @version V1.0
 */
@Service
public class AccessTokenUtil {

//	@Value("${wechat.appid}")
//	   String appid;
//	@Value("${wechat.appsecret}")
//	   String appsecret;
//
//	public static HttpConnectionManager httpClientManager = ContextLoader.getCurrentWebApplicationContext().getBean(HttpConnectionManager.class);
//
//	/**
//	 *获取access_token
//	 */
//	public  AccessToken getAccessToken() throws Exception{
//		AccessToken at = null;
//		StringBuffer sb = new StringBuffer();
//		sb.append(UrlMapping.GET_ACCESSTOKEN_URL);
//		sb.append("?grant_type=client_credential&appid=");
////		sb.append(WechatConstant.APP_ID);
//		sb.append(appid);
//		sb.append("&secret=");
////		sb.append(WechatConstant.APP_SECRET);
//		sb.append(appsecret);
//		CloseableHttpClient httpClient = httpClientManager.getHttpClient();
//		HttpGet httpGet = new HttpGet(sb.toString());
//		CloseableHttpResponse response = httpClient.execute(httpGet);
//		try {
//			HttpEntity entity = response.getEntity();
//			at = new ObjectMapper().readValue(EntityUtils.toString(entity), AccessToken.class);
//		} catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			response.close();
//		}
//		return at;
//	}
//

	
}
