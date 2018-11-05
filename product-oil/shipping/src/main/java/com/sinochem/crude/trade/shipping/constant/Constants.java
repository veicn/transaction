package com.sinochem.crude.trade.shipping.constant;

/**
 * 标识的常量类
 *
 * @author Yichen Zhao
 *         date: 20180227
 */
public class Constants {

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


}
