package com.sinochem.crude.trade.orderexecute.commons.constants;

public enum MsgRemindingTemplateEnum {
	/** NOR提醒 */
	NOR_REMINDING("MSG_01","NOR提醒", "norReminding"),
	
	/** COD提醒 */
	COD_REMINDING("MSG_02","COD提醒", "codReminding"),
	
	/** 提单日提醒 */
	BILLDATE_UPDATE_REMINDING("MSG_03","提单日提醒", "billdateUpdateReminding"),
	
	/** 确认收款提醒 */
	CHECK_RECEIPT_REMINDING("MSG_04","确认收款提醒","checkReceiptReminding"),
	
	/** 上传付款凭证提醒（正式对账单） */
	UPLOAD_PAYMENT_VOUCHER_REMINDING_F("MSG_0501","上传付款凭证提醒","uploadPaymentVoucherReminding"),
	/** 上传付款凭证提醒（预估对账单） */
	UPLOAD_PAYMENT_VOUCHER_REMINDING_L("MSG_0502","上传付款凭证提醒","uploadPaymentVoucherReminding"),
	
	/** 创建对账单提醒 */
	CREATE_STATEMENT_REMINDING("MSG_06","创建对账单提醒","createPaymentReminding"),
	
	/** 结算单提交提醒 */
	STATEMENT_SUBMITED_NOTIFY("MSG_07","结算单提交提醒","statementSubmitedNotify"),
	/** 结算单确认提醒 */
	STATEMENT_CONFIRM_NOTIFY("MSG_08","结算单确认提醒","statementConfirmNotify"),
	/** 结算单被驳回 */
	STATEMENT_REJECT_NOTIFY("MSG_09","结算单被驳回","statementRejectNotify"),
	/** 付款凭证已上传 */
	STATEMENT_UPLOAD_PAYMENT_VOUCHER_NOTIFY("MSG_10","付款凭证已上传","statementUploadPaymentVoucherNotify");
	
	private String msgCode;
	private String title;
	private String templateName;
	
	/**
	 * @param msgCode 消息代码
	 * @param templateName 模板名称
	 * @param title 消息标题
	 */
	MsgRemindingTemplateEnum(String msgCode, String title, String templateName){
		this.msgCode = msgCode;
		this.title = title;
		this.templateName = templateName;
	}
	
	public String getMsgCode() {
		return msgCode;
	}
	public String getTemplateName() {
		return templateName;
	}
	public String getTitle() {
		return title;
	}
}
