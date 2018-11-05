package com.sinochem.crude.trade.inspector.constant;

/**
 * 标识的常量类
 *
 * @author Yichen Zhao
 *         date: 20180227
 */
public class Constant {
	
	/**有效状态 */
    public static final String ALIEVE_FLAG_YES = "1";
    /**无效状态 */
    public static final String ALIEVE_FLAG_NO = "0";
    /**语言类型*/
    public static final String LANG_VER = "zh";

    /**新增成功凑code*/
    public static final int SAVE_SUCCESS = 0;


    /**
     * 新增失败
     */
    public static final String SAVE_FALSE = "Insert Failed";

    /**
     * 新增成功
     */
    public static final String SAVE_TRU = "Insert Successfully！";

    /**
     * 修改失败
     */
    public static final String MODIFY_FALSE = "Modify Failed";

    /**
     * 修改成功
     */
    public static final String MODIFY_TRU = "Modify Successfully!";


    /**
     * 删除失败
     */
    public static final String DELE_FALSE = "Delete Failed";

    /**
     * 删除成功
     */
    public static final String DELE_TRU = "Delete Successfully!";

    /**
     * 新增标识
     */
    public static final String SAVE_FLAG = "1";

    /**
     * 逻辑删除标识
     */
    public static final String DELE_FLAG = "0";

    /**
     * 租船需求单编码seq
     */
    public static final String DEMANDS_CODE = "ZCXQ";

    /**
     * 租船协议单编码seq
     */
    public static final String AGREEMENT_CODE = "ZCXY";

    /**
     * 租船确认单编码seq
     */
    public static final String CONFIRMATION_CODE = "ZCQR";

    /**
     * 系统异常message
     */
    public static final String EXCEPTION_MESSAGE = "System Error";
    /**
     * 系统异常status
     */
    public static final Integer EXCEPTION_STATUS = 9999;
    /**
     * 系统异常code
     */
    public static final String EXCEPTION_CODE = "9999";


    /**
     * 询价 enquiry MESSAGE
     */
    public static final String ENQUIRY_MESSAGE = "ENQUIRY ONESELF";
    /**
     * 询价
     */
    public static final Integer ENQUIRY_STATUS = 901;

    /**
     * 装货港增加第一步
     */
    public static final long STEP_ONE = 1;
    /**
     * 装货港增加第二步
     */
    public static final long STEP_TWO = 2;
    /**
     * 装货港增加第三步
     */
    public static final long STEP_THREE = 3;
    /**
     * 装货港增加第四步
     */
    public static final long STEP_FOUR = 4;

    /**
     * 造船年限"0-20"
     */
    public static final String BULLT_ONE = "1";
    /**
     * 造船年限"0-15"
     */
    public static final String BULLT_TWO = "2";
    /**
     * 造船年限"0-10"
     */
    public static final String BULLT_THREE = "3";
    /**
     * 造船年限"non"
     */
    public static final String BULLT_FOUR = "4";
    /**
     * 造船年限"other"
     */
    public static final String BULLT_FIVE = "5";

    /**
     * 炼厂 (Web)
     */
    public static String TYPE_REFINERY_WEB = "1";

    /**
     * 贸易商 (Web)
     */
    public static String TYPE_TRADER_WEB = "2";

    /**
     * 船代(Web)
     */
    public static String TYPE_CONFIRMATION_WEB = "6";

    /**
     * 转租船东(Web)
     */
    public static String TYPE_SHIP_AGENT_WEB = "7";

    /**
     * 贸易商
     */
    public static Integer TYPE_TRADER = 1;

    /**
     * 买家
     */
    public static Integer TYPE_BUYER = 2;

    /**
     * 船代
     */
    public static Integer TYPE_SHIP_AGENT = 3;

    /**
     * 卖家
     */
    public static Integer TYPE_SELLER = 4;

    /**
     * 租船代理
     */
    public static Integer TYPE_DISPONENT_OWNER = 5;

    /**
     * 第4步返回前端做判断使用
     */
    public static Integer STEP_FLAG = 4;

    /**
     * 是否在线：默认为0 在线
     */
    public static String COMFIRM_ONLINE_DEFULT = "0";

    /**
     * 产品来源 1:代理
     */
    public static String PRODUCT_SOURCE_CODE_AGREEMENT = "1";

    /**
     * 消息发送类型1，针对if  else
     */
    public static String TYPE_CODE_ONE = "1";
    /**
     * 消息发送类型2 针对if  else
     */
    public static String TYPE_CODE_TWO = "2";
    /**
     * 消息发送类型3 针对if  else
     */
    public static String TYPE_CODE_THREE = "3";
    public static String TYPE_CODE_FOUR = "4";

    public static String[] TYPE_CODESS=new String[]{
            "ONE",
            "TWO",
            "THREE",
            "FOUR"
    };

    public static long OTHER_SHIPPINGAGENT_ID = 99999999;

    public static String OTHER_SHIPPINGAGENT_NM = "other";

    //	Owner
    public static final String DISPONENT_OWER_DEMANDS_URL = "pages/back/demands/agentdemandsList.htm";
    public static final String DISPONENT_OWER_CHARTER_PARTY_URL = "pages/back/agreement/charterPartyManagementByAgent.htm";

    //船代确认单
    public static final String AGENT_CONFIRMATION_URL = "pages/back/vesselAcceptance/ConfirmationAgencyIndex.htm";

    //买家确认单
    public static final String BUYER_CONFIRMATION_URL = "pages/back/vesselAcceptance/ConfirmationBuyerIndex.htm";

    //	卖家需求单列表
    public static final String SELLER_DEMANDS_URL = "pages/back/demands/demandsList.htm";
    //	卖家协议单列表
    public static final String SELLER_CHARTER_PARTY_URL = "pages/back/agreement/charterPartyManagementByMerchant.htm";

    //	卖家确认单
    public static final String SELLER_VESSEL_ACCEPTANCE_URL = "pages/back/vesselAcceptance/index.htm";
    //成功提示信息
    public static final String SUCCESS_MESSAGE = "Successfully";


  /**
     * 询价标识 有效
     */
     public static final String IRYQUOTATION_10 = "10";
    /**
     * 询价标识 无效
     */
    public static final String IRYQUOTATION_20 = "20";
    /**
     * 询价标识 已联系
     */
    public static final String IRYQUOTATION_30 = "30";
    /**
     * 询价标识 未联系
     */
    public static final String IRYQUOTATION_40 = "40";

    /**
     * 油品类
     */
    public static final String OILTYPE_1 = "1";
    /**
     * 油品类
     */
    public static final String OILTYPE_2 = "2";
    /**
     * 油品类
     */
    public static final String OILTYPE_3 = "3";
    /**
     * 卖家资质
     */
    public static final String SELLER = "sales_trader";

    /**
     * 买家资质
     */
    public static final String BUYER = "buy_trader";

    /**
     * 买家资质code
     */
    public static final String BUYER_CODE = "2";

    /**
     * 卖家资质code
     */
    public static final String SELLER_CODE = "1";

    /**
     * 船燃销售
     */
    public static final String SHIP_GNITION = "1";
    /**
     * 船油供销
     */
    public static final String SHIP_OIL = "2";

    public static final String FILE_OIL_TYPE = "1";
    public static final String FILE_AGREEMENT_TYPE = "2";
    public static final String FILE_INVOICE_TYPE= "3";
    public static final String FILE_PRODUCT_TYPE = "4";


    // 报价/询价状态 未联系
    public static final String QUOTATION_STATUS_NOT_CONNECTED = "1";

    // 报价/询价状态 已联系
    public static final String QUOTATION_STATUS_CONNECTED = "2";

    // 报价/询价状态 已成交
    public static final String QUOTATION_STATUS_DONE = "3";

    // 报价/询价状态 有效
    public static final String QUOTATION_STATUS_VALID = "4";

    // 报价/询价状态 无效
    public static final String QUOTATION_STATUS_INVALID = "5";

    // 报价/询价类型 报价
    public static final String QUOTATION_TYPE_OFFER = "2";

    // 报价/询价类型 询价
    public static final String QUOTATION_TYPE_INQUIRY = "1";


    /**
     * DataDict常量
     */
    // 油品type_code
    public static final String DAT_DICT_TYPE_CODE_OIL_CLASSIFICATION = "100";

    // 规格type_code
    public static final String DAT_DICT_TYPE_CODE_OIL_VARIETIES = "101";

    // 船加油 code
    public static final String DAT_DICT_P_DICT_CODE_SHIP = "10";
    // 原料油 code
    public static final String DAT_DICT_P_DICT_CODE_CRUDE = "90";

}
