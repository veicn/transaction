package com.sinochem.crude.trade.wechat.constant;

import com.sinochem.it.b2b.common.exception.BizException;

public class WechatException extends BizException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public enum TYPE{
		WE001("WE001", "身份信息为空"),
		WE002("",""),
		
		;
		private String code;
		private String desc;

		TYPE(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		public String getCode() {
			return this.code;
		}
		public String getDesc() {
			return this.desc;
		}
	}
	
}
