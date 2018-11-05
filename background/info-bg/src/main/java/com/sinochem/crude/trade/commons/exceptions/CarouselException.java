package com.sinochem.crude.trade.commons.exceptions;

import com.sinochem.crude.trade.common.exception.BizException;

public class CarouselException extends BizException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6854801395723117773L;

	public CarouselException(String code,String msg){
		super(msg);
		super.setCode(code);
		super.setType("PL");
		super.setData(msg);
	}
	public CarouselException(TYPE type){
		super(type.getDesc());
		super.setCode(type.getCode());
		super.setType("PL");
		super.setData(type.getDesc());
	}
	/**
	 * 代码定义规则
	 * 例：WBCG001
	 * WB(0,2)：应用代码已定请咨询模块负责人；
	 * CG(2,4)：业务模块代码，
	 * 001(4,7)：序号
	 * 本项应用应用代码为：PL
	 */
	public enum TYPE {
		PLCR001 ("PLCR001","信息有误"),
		PLCR002 ("PLCR002","轮播图id不能为空"),
		PLCR003 ("PLCR003","未登陆, 请登录"),
		PLCR004 ("PLCR004","类型代码不能为空"),
		PLCR005 ("PLCR005","广告位描述不能为空"),
		PLCR006 ("PLCR006","系统页面号不能为空"),
		PLCR007 ("PLCR007","广告页面位置不能为空"),
		PLCR008 ("PLCR008","图片不能为空"),
		PLCR009 ("PLCR009","广告位栏位id不能为空"),
		PLCR010 ("PLCR010","广告图id不能为空"),
		PLCR011 ("PLCR011","主键不能为空")
		;
		
		
		private String code;
		private String desc;
		TYPE(String code,String desc) {
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
