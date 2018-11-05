package com.sinochem.crude.trade.shiprefueling.constants;

/**
 * URL地址常量类
 *
 * @author Yichen Zhao date: 20180225
 */
public class UrlMapping {

    /**
     * 销售信息controller
     */
    public static final String WAB_INFO = "/info";
    /**
     * 查询销售列表
     */
    public static final String INFO_SELECTALLORDER = "/selectAll.json";
    
    
    /****************************************** niuwk start  *********************************************************/
    /** 添加附件信息（开发调试用） */
	public static final String ATTACHMENTS_INSERT = "/attachments/insert.json";
	/** 修改附件信息（开发调试用） */
	public static final String ATTACHMENTS_MODIFY = "/attachments/modify.json";
	/** 查询附件信息 （开发调试用）*/
	public static final String ATTACHMENTS_GET = "/attachments/get.json";
    /** 删除附件信息 （开发调试用）*/
	public static final String ATTACHMENTS_DELETE = "/attachments/delete.json";
	/** 查询附件信息列表（开发调试用） */
	public static final String ATTACHMENTS_LIST = "/attachments/list.json";

    
	/** 添加采购信息 */
	public static final String HASEINFO_INSERT = "/haseinfo/insert.json";
	/** 修改采购信息 */
	public static final String HASEINFO_MODIFY = "/haseinfo/modify.json";
	/** 查询采购信息 */
	public static final String HASEINFO_GET = "/haseinfo/get.json";
	/** 删除采购信息 */
	public static final String HASEINFO_DELETE = "/haseinfo/delete.json";
	/** 查询采购信息列表 */
	public static final String HASEINFO_LIST = "/haseinfo/list.json";
	
	
	/** 添加询价报价信息 */
	public static final String INQUIRY_QUOTATION_INSERT = "/inquiryquotation/insert.json";
	/** 修改询价报价信息 */
	public static final String INQUIRY_QUOTATION_MODIFY = "/inquiryquotation/modify.json";
	/** 查询询价报价信息 */
	public static final String INQUIRY_QUOTATION_GET = "/inquiryquotation/get.json";
	/** 删除询价报价信息 */
	public static final String INQUIRY_QUOTATION_DELETE = "/inquiryquotation/delete.json";
	/** 查询询价报价信息列表 */
	public static final String INQUIRY_QUOTATION_LIST = "/inquiryquotation/list.json";
	/** 根据采购UUID查询询价报价信息列表 */
	public static final String INQUIRY_QUOTATION_LIST_BY_NEEDUUID = "/inquiryquotation/listbyneeduuid.json";
	/** 更新状态*/
	public static final String INQUIRY_QUOTATION_STATUS = "/inquiryquotation/status.json";
	
	/** 查询船燃订单信息列表 */
	public static final String ORDER_IGNITION_LIST = "/buyorderignition/list.json";
	/** 按交易对手展示订单分页列表 */
	public static final String ORDER_IGNITION_OPPONENT_LIST = "/orderignition/opponent/list.json";
	
	
	/** 查询船燃订单信息列表 */
	public static final String ORDER_SUPPLY_LIST = "/ordersupply/list.json";
	/** 按交易对手展示订单分页列表 */
	public static final String ORDER_SUPPLY_OPPONENT_LIST = "/orderignition/supply/list.json";
	
	/** 根据订单UUID查询船燃订单信息列表 */
	public static final String CATEGORY_LIST_BY_ORDERUUID = "/category/listbyorderuuid.json";
	
	 /****************************************** niuwk end  *********************************************************/


}
