package com.sinochem.crude.trade.wechat.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.sinochem.crude.trade.wechat.constant.AccessTokenUtil;
import com.sinochem.crude.trade.wechat.constant.UrlMapping;
import com.sinochem.crude.trade.wechat.constant.WechatConstant;
import com.sinochem.crude.trade.wechat.domain.UserInfo;
import com.sinochem.crude.trade.wechat.helper.HttpHelper;
import com.sinochem.crude.trade.wechat.helper.StringHelper;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sinochem.crude.trade.wechat.dao.AccessTokenMapper;
import com.sinochem.crude.trade.wechat.domain.AccessToken;
import com.sinochem.crude.trade.wechat.domain.AccessTokenClazz;
import com.sinochem.crude.trade.wechat.service.AccessTokenService;
@Service
public class AccessTokenServiceImpl implements AccessTokenService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AccessTokenMapper accessTokenMapper;
	@Autowired
	@Value("${wechat.appid}")
	String appid;
	@Value("${wechat.appsecret}")
	String appsecret;

	@Override
	public void saveAccessToken(AccessToken accessToken,int type) {
		AccessTokenClazz accessTokenClazz = new AccessTokenClazz();
		if(accessToken != null){
			accessTokenClazz.setAccessToken(accessToken.getAccess_token());
			accessTokenClazz.setCreateDate(new Date());
			accessTokenClazz.setTokentype(type);
		}
		accessTokenMapper.insertAccessToken(accessTokenClazz);
	}

	/**
	 * 获取微信jstoken并存在mysql；
	 * 115分钟内jstoken有效；
	 *regain  是否强制重新获取token；
	 */
	@Override
	public  String getJSToken(boolean regain) {
		//先判断当前jstoken是否过期；
		String jstoken="";
		AccessTokenClazz argac=new AccessTokenClazz();
		argac.setTokentype(1);
		AccessTokenClazz accessTokenClazz= accessTokenMapper.findvolidAccessToken(argac);
		if(accessTokenClazz==null||regain) {
			try {
				AccessToken accessToken = getWXJSToken(false);

				if (accessToken != null && accessToken.getErrcode() != null && (accessToken.getErrcode().equals("42001") || accessToken.getErrcode().equals("40001"))) {
					accessToken = getWXJSToken( true);
				}
				if(accessToken!=null) {
					jstoken=accessToken.getTicket();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return  jstoken;
		}
		return accessTokenClazz.getAccessToken();
	}
	Gson gson=new Gson();
	/**
	 *获取微信jstoken
	 */
	public  AccessToken getWXJSToken(boolean regain) throws Exception{
		String token=getToken(regain);
		String tickestr ="";
		HttpHelper helper=new HttpHelper();
		Map<String, String> map = new HashMap<>();
		map.put("access_token",token);
		map.put("type","jsapi");
		String json=helper.GetWXServiceMap(UrlMapping.WECHAT_TICKET,map);
		AccessToken tick= gson.fromJson(json, AccessToken.class);
		if(tick!=null && tick.getErrcode().equals("0")) {
			tick.setAccess_token(tick.getTicket());
			saveAccessToken(tick, 1);
//			 tickestr = tick.getTicket();
		}
		return tick;
	}

/**
 * 获取微信token并存在mysql；
 * 115分钟内token有效；
*regain  是否强制重新获取token；
 */
    @Override
	public  String getToken(boolean regain) {
		//先判断当前token是否过期；
		AccessTokenClazz argac=new AccessTokenClazz();
		argac.setTokentype(0);
		AccessTokenClazz accessTokenClazz= accessTokenMapper.findvolidAccessToken(argac);
		if(accessTokenClazz==null||regain) {
			AccessToken accessToken = null;
			try {
				accessToken = getAccessToken();//AccessTokenUtil.getAccessToken();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (accessToken != null) {

				saveAccessToken(accessToken,0);
			}
			return  accessToken.getAccess_token();
		}
		return accessTokenClazz.getAccessToken();
	}




	/**
	 *获取access_token
	 */
	public  AccessToken getAccessToken() throws Exception{
		//AccessToken at = null;
		Map<String, String> map = new HashMap<>();
		map.put("appid",appid);
		map.put("secret", appsecret);
		map.put("grant_type", "client_credential");
		HttpHelper httpHelper = new HttpHelper();
		String wu = httpHelper.GetWXServiceMap(UrlMapping.GET_ACCESSTOKEN_URL, map);
		Gson gson = new Gson();
		AccessToken at = gson.fromJson(wu, AccessToken.class);
		return at;
	}

	@Override
	public AccessTokenClazz findAnAccessToken() {
		return accessTokenMapper.findAnAccessToken();
	}

	@Override
	public void deleteAccessToken() {
		accessTokenMapper.deleteAccessToken();
	}
	
}
