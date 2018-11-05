package com.sinochem.crude.trade.wechat.constant;

/**
 * URL地址常量类
 * @author Yichen Zhao
 * date: 20180225
 */
public class UrlMapping {

	/**
	 * 用来验证消息的确来自微信服务器
	 */
	public static final String  VERIFY_REQUEST="verifyRequest.json";

	/**
	 * 回调地址
	 */
	public static final String CALL_BACK="callback.json";
	
	/**
	 * AccessToken请求地址
	 */
	public static final String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

	/**
	 * 用户同意授权，获取code
	 */
	public static final String WECHAT_AUTHORIZE="https://open.weixin.qq.com/connect/oauth2/authorize";

	/**
	 * 获取token和openid
	 */
	public static final String WECHAT_ACCESS_TOKEN="https://api.weixin.qq.com/sns/oauth2/access_token";

//	/**
//	 * 获取用户信息
//	 */
//	public static final String WECHAT_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";

	/**
	 * 获取关注公众号的openid
	 */
	public static final String WECHAT_USER_OPENID = "https://api.weixin.qq.com/cgi-bin/user/get";
	/**
	 *获得jsapi_ticket
	 */
	public static final String WECHAT_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

	/**
	 * 获取用户信息
	 */
	public static final String WECHAT_USER_INFO= "https://api.weixin.qq.com/cgi-bin/user/info";

	/**
	 * 设置用户备注名
	 */
	public static final String WECHAT_UPDATEREMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark";

	/**
	 * 微信配置的回调地址
	 */
	public static final String CALL_BACK_URL="http://118.89.34.39/callback.json";
	
	/**
	 * 存储用户关系信息
	 */
	public static final String SAVE_RELATION = "saveRelation.json";
	
	/**
	 * 解绑
	 */
	public static final String UN_BINDING = "deleteRelation.json";

	/**
	 * 客服接口-发消息
	 */
	public static final String SEND_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
	
	/**
	 * 消息推送
	 */
	public static final String NOTIFICATION = "notification.json";


	/**
	 * 获取公司信息
	 */
	public static final String ENTERPRISE="enterprise.json";

	/**
	 * 获取个人信息
	 */
	public static final String PERSON="person.json";


	public static final String LOGIN="login.json";
}
