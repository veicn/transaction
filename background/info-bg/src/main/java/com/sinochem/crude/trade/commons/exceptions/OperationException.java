package com.sinochem.crude.trade.commons.exceptions;

import com.sinochem.crude.trade.common.exception.BizException;

public class OperationException extends BizException {

	private static final long serialVersionUID = 1L;

	public OperationException(String code, String msg) {
		super(msg);
		super.setCode(code);
		super.setType("OP");
		super.setData(msg);
	}

	public OperationException(TYPE type) {
		super(type.getDesc());
		super.setCode(type.getCode());
		super.setType("OP");
		super.setData(type.getDesc());
	}

	public OperationException(String code, String msg, Throwable e) {
		super(msg, e);
		super.setCode(code);
		super.setType("OP");
		super.setData(msg);
	}

	public OperationException(TYPE type, Throwable e) {
		super(type.getDesc(), e);
		super.setCode(type.getCode());
		super.setType("OP");
		super.setData(type.getDesc());
	}

	/**
	 * 代码定义规则 例：WBME001 WB(0,2)：应用代码已定请咨询模块负责人； ME(2,4)：业务模块代码， 001(4,7)：序号
	 * 本项应用应用代码为：MB
	 */
	public enum TYPE {
		OPEX001 ("OPEX001","系统异常"),
		
		OPBA008("OPBA008", "值集类别无法读取，请稍后重试"), 
		OPBA009("OPBA009", "值集数据无法读取，请稍后重试"), 
		OPBA010("OPBA010", "值集插入失败，请稍后重试"), 
		OPBA011("OPBA011", "值集更新失败，请稍后重试"), 
		OPBA012("OPBA012", "值集删除失败，请稍后重试"), 
		OPBA013("OPBA013", "值集可编辑性查询失败，请稍后重试"), 
		OPBA014("OPBA014", "值集编辑请求处理失败，请稍后重试"),
		OPBA015("OPBA015", "主键不能为空"),
		SOSM080 ("SOSM080","值集类别代码不能为空"),
		
		/**设备异常*/
		BADV003 ("BADV003","设备唯一标识码为空"),
		BADV004 ("BADV004","应用名称为空"),
		BADV005 ("BADV005","用户编号为空"),
		BADV006 ("BADV006","该用户未登陆"),
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
