package com.sinochem.crude.trade.transport.commons.constants;


/**
 * Created by x on 2018/3/1.
 */
public class MessageTypeConstants {
	
	
	/**code，标题，模板名称*/
	public enum TYPE {
			/**航次开始-申请租船的操作员 - voyageStart*/
			ITRS001("ITRS001","更新配载计划","ship-transitBeg","ship-transitBeg_en","ship-transitBeg_all"),
			/**装港-申请租船的操作员 -loadPort*/
			ITRS002("ITRS002","录入船务信息（装港）","ship-loadPort","ship-loadPort_en","ship-loadPort_all"),
			/**在途-申请租船的操作员 -transitBeg*/
			ITRS003("ITRS003","录入船务信息（在途）","ship-voyage","ship-voyage_en","ship-voyage_all"),
			/**卸港-申请租船的操作员 -unloadPort*/
			ITRS004("ITRS004","录入船务信息（卸港）","ship-unloadPort","ship-unloadPort_en","ship-unloadPort_all"),
			/**航次结束-申请租船的操作员 -transitEnd*/
			ITRS005("ITRS005","航次结束","ship-transitEnd","ship-transitEnd_en","ship-transitEnd_all"),
			/**生成租船协议-买卖家确认租船操作员 -agreement*/
			ITRS006("ITRS006","生成租船协议","ship-agreement","ship-agreement_en","ship-agreement_all"),
			/**船盘失效-发船盘的操作员 -shipPlate*/
			ITRS007("ITRS007","船盘过时提醒","ship-shipPlateOut","ship-shipPlateOut_en","ship-shipPlateOut_all"),
			/**申请租船-转租船东企业 -applyShip*/
			ITRS008("ITRS008","申请租船","ship-applyShip","ship-applyShip_en","ship-applyShip_all"),	
			/**询盘-船盘发布操作员 -inquiry*/
			ITRS009("ITRS009","询盘","ship-inquiry","ship-inquiry_en","ship-inquiry_all"),									
			/**还盘-询盘操作员 -counter*/
			ITRS010("ITRS010","还盘","ship-counter","ship-counter_en","ship-counter_all"),		
			/**确认租船-发布船盘的操作员 -confirmShip*/
			ITRS011("ITRS011","确认租船","ship-confirmShip","ship-confirmShip_en","ship-confirmShip_all"),									
			/**船盘发布人终止-询盘操作员 -shipPlateMemberNameCancel*/
			ITRS012("ITRS012","终止租船","ship-shipPlateMemberNameCancel","ship-shipPlateMemberNameCancel_en","ship-shipPlateMemberNameCancel_all"),										
			/**询盘人终止-发布船盘操作员 -inquiryCancel*/
			ITRS013("ITRS013","终止租船","ship-inquiryCancel","ship-inquiryCancel_en","ship-inquiryCancel_all"),									
			/**报盘-申请租船操作员 -clause*/
			ITRS014("ITRS014","报盘","ship-clause","ship-clause_en","ship-clause_all"),								
			/**确认报盘-转租船东报盘操作员 -confirmClause*/
			ITRS015("ITRS015","确认报盘","ship-confirmClause","ship-confirmClause_en","ship-confirmClause_all"),									
			/**终止报盘-转租船东报盘操作员 -clauseCancel*/
			ITRS016("ITRS016","终止报盘","ship-clauseCancel","ship-clauseCancel_en","ship-clauseCancel_all"),									
			/**船舶审核通过-新增船舶方 -approveShip*/
			ITRS017("ITRS017","添加船舶，待平台审核","ship-approveShip","ship-approveShip_en","ship-approveShip_all"),										
			/**船舶审核驳回-新增船舶方 -rejectShip*/
			ITRS018("ITRS018","添加船舶，待平台审核","ship-rejectShip","ship-rejectShip_en","ship-rejectShip_all"),									
			/**指定船代装港-船代企业 -chuandaiLoadPort*/
			ITRS019("ITRS019","指定船代","ship-chuandaiLoadPort","ship-chuandaiLoadPort_en","ship-chuandaiLoadPort_all"),									
			/**指定船代卸港-船代企业 -chuandaiUnloadPort*/
			ITRS020("ITRS020","指定船代","ship-chuandaiUnloadPort","ship-chuandaiUnloadPort_en","ship-chuandaiUnloadPort_all"),								
			/**已结算-申请租船操作员 -calculate*/
			ITRS021("ITRS021","录入结算信息","ship-calculate","ship-calculate_en","ship-calculate_all"),							
													
													
													
			ITRS100("", "", "","","");
			
			
			/**代码*/
			private String code;
			/**标题*/
			private String title;
			/**模板名称*/
			private String tplName;
			/**模板名称英文*/
			private String tplName_en;
			/**模板名称中英文*/
			private String tplName_all;
			
			TYPE(String code, String title, String tplName,String tplName_en,String tplName_all) {
				this.code = code;
				this.title = title;
				this.tplName = tplName;
				this.tplName_en = tplName_en;
				this.tplName_all = tplName_all;
				
			}
			/**代码*/
			public String getCode() {
				return code;
			}
			/**标题*/
			public String getTitle() {
				return title;
			}
			/**模板名称*/
			public String getTplName() {
				return tplName;
			}
			/**模板名称_en*/
			public String getTplName_en() {
				return tplName_en;
			}
			/**模板名称_all*/
			public String getTplName_all() {
				return tplName_all;
			}
			
			
			
		}
    
    
    
}
