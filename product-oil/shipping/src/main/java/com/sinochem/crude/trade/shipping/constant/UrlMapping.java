package com.sinochem.crude.trade.shipping.constant;

/**
 * URL地址常量类
 *
 * @author Yichen Zhao date: 20180225
 */
public class UrlMapping {

    /**
     * 公共
     */
    public static final String INDEX = "index";

    /**
     * test测试航程跟踪
     */
    public static final String PRODUCT_BACK_VOYAGETRACKING = "pages/back/voyageTracking";

    /**
     * 物流跟踪
     */
    public static final String PAGES_BACK_LOGISTICSTRACKING = "pages/back/LogisticsTracking";

    /**
     * 航程跟踪url
     */
    public static final String PRODUCT_BACK_VOYAGESTART = "pages/back/VoyageStart";

    /**
     * 航程在途中航程跟踪url 
     */
    public static final String PRODUCT_BACK_TRANSITLOADING = "pages/back/TransitLoading";

    /**
     * 航程卸港前在途中航程跟踪url
     */
    public static final String PRODUCT_BACK_TRANSITUNLOADING = "pages/back/TransitUnloading";


    /**
     * 船舶确认单
     */
    public static final String VESSEL_ACCEPTANCE = "pages/back/vesselAcceptance";

    /**
     * 查看船舶确认单详情
     */
    public static final String DETAILS = "details";
    
	/**
	 * 进入船舶确认单确认页面
	 */
	public static final String CONFIRM = "confirm";

    /**
     * 进入确认单修改页面
     */
    public static final String MODIFY = "modify";

    /**
     * 生成船舶确认单
     */
    public static final String CREATE = "create";

    /**
     * 查看船舶确认单详情
     */
    public static final String VESSEL_ACCEPTANCE_DETAILS = "pages/back/vesselAcceptanceDetails";

    /**
     * 生成船舶确认单
     */
    public static final String CREATE_VESSEL_ACCEPTANCE = "pages/back/createVesselAcceptance";

    /**
     * 装港信息url
     */
    public static final String PRODUCT_BACK_LOADPORT = "pages/back/LoadPort";

    /**
     * 装港进度
     */
    public static final String LOAD_PORT_PROGRESS = "pages/back/progress";

    /**
     * 平台船舶维护信息
     */
    public static final String PRODUCT_BACK_SYSSHIP = "pages/back/sysShip";

    /**
     * 租船协议管理
     */
    public static final String MANAGE_AGREEMENT = "pages/back/agreement";

    /**
     * 租船协议列表界面
     **//*
    public static final String LIST_INDEX = "charterPartyManagement";*/

    /**
     * 生成租船协议信息
     **/
    public static final String ADD_INDEX = "index";

    /**
     * 修改租船协议信息
     **/
    public static final String UPDATE_INDEX = "update_index";

    /**
     * 查看租船协议信息
     **/
    public static final String Detail_INDEX = "detail_index";

    /**
     * 确认租船协议信息
     **/
    public static final String CONFIRM_DETAIL_INDEX = "confirm_index";

    /**
     * 录入租船协议协议
     **/
    public static final String SAVE_AGREEMENT = "agreementAdd";

    /**
     * 确认租船协议协议
     **/
    public static final String CONFIRM_AGREEMENT = "agreementConfirm";
    
    /**
     * 删除租船协议协议
     **/
    public static final String Delete_AGREEMENT = "agreementDelete";
    
    /**
     * 租船协议协议（租船代理）
     **/
    public static final String LIST_INDEX_AGENT = "charterPartyManagementByAgent";
    /**
     * 租船协议协议（贸易商）
     **/
    public static final String LIST_INDEX_MERCHANT = "charterPartyManagementByMerchant";

    /**
     * 平台船舶信息维护_新增
     */
    public static final String SAVE_SYS_SHIP = "/saveSysShip";
    
    /**
     * 平台船舶信息维护_新增保存
     */
    public static final String UPDATE_SAVE_SYS_SHIP = "/updateSaveSysShip.json";
    
    /**
     * 跳转平台新增页面
     */
    public static final String TO_SAVE_SYS_SHIP = "/saveSysShip";

    /**
     * 平台船舶信息维护_修改
     */
    public static final String EDIT_SYS_SHIP = "/editSysShip";
    
    /**
     * 平台船舶信息维护_更新
     */
    public static final String UPDATE_SYS_SHIP = "/updateSysShip.json";
    
    /**
     * 平台船舶信息维护_删除
     */
    public static final String DEL_SYS_SHIP = "/delSysShip.json";

    /**
     * 平台船舶信息维护_下拉列表
     */
    public static final String FIND_SYS_SHIP_LIST = "/findSysShipList.json";

    /**
     * 平台船舶信息维护_翻页列表
     */
    public static final String GET_SYS_SHIP_PAGE_LIST = "/sysShipList.htm";

    /**
     * 根据uuid、查询船舶状态
     */
    public static final String FIND_SYS_SHIP_STATUS = "/findSysShipStatus.json";

    /**
     * 根据imo、查询船舶MMSI
     */
    public static final String FIND_SYS_SHIP_MMSI = "/findSysShipMmsi.json";

    /**
     * 平台船舶信息维护_详细
     */
    public static final String FIND_SYS_SHIP_DETAIL = "/findSysShipDetail";

    /**
     * 船舶附件信息维护_删除
     */
    public static final String DEL_ACCESSORY = "/accessory/delAccessory.json";
    /**
     * 船舶附件信息维护_列表
     */
    public static final String FIND_ACCESSORY_LIST = "/accessory/findAccessoryList.json";

    /**
     * 租船信息_列表
     */
    public static final String PRODUCT_BACK_DEMANDS = "pages/back/demands";
    /**
     * 租船信息_列表(中化信)
     */
    public static final String GET_DEMANDS_PAGE_LIST = "/demandsList.htm";
    /**
     * 租船信息_列表(船代)
     */
    public static final String GET_AGENTDEMANDS_PAGE_LIST = "/agentdemandsList.htm";
    /**
     * 租船信息_详情
     */
    public static final String DEMANDS_SELECT_UUID = "/demandsDetail.json";
    /**
     * 租船信息_生成租船协议信息
     */
    public static final String DEMANDS_SAVE_INIT = "/insert.htm";
    /**
     * 租船信息_新增
     */
    public static final String DEMANDS_SAVE = "/save.json";
    /**
     * 租船信息_取消
     */
    public static final String DEMANDS_DELETE = "/delete.json";
    /**
     * 租船信息_修改
     */
    public static final String DEMANDS_UPDATE = "/update.json";
    /**
     * 租船信息_作废
     */
    public static final String DEMANDS_UPDATE_STATUS = "/updateStatus.json";
    /**
     * 校验是否已租船
     */
    public static final String CHECKA_DEMANDS = "/checkDemands.json";
    /**
     * 校验是否已租船
     */
    public static final String CHECKA_CONFIRMATION = "/checkConfirmation.json";

    /**
     * 查看船舶确认单详情
     */
    public static final String VESSEL_ACCEPTANCE_DETAILS_APP = "wechat/vesselAcceptanceDetailsApp.json";
    

    /**
     * 租船确认单_船代
     * 
     */
    public static final String CONFIRMATIONAGENCYINDEX = "/ConfirmationAgencyIndex";
    
    /**
     * 租船确认单_买家
     * 
     */
    public static final String CONFIRMATIONBUYERINDEX = "/ConfirmationBuyerIndex";
    
    /**
     * 租船确认单_卖家
     * 
     */
    public static final String CONFIRMATIONSELLERINDEX = "/ConfirmationSellerIndex";
    
    /**
     * 租船确认单_结束航程
     * 
     */
    public static final String UPDATECONFIRAM = "/updateConfiram.json";
    
    /**
     * 租船确认单_新增装港信息
     * 
     */
    public static final String LOADINGPORTADD = "/loadingPortAdd.json";
    
    /**
     * 租船确认单_新增在途中到港前信息
     * 
     */
    public static final String ADDLODING = "/addLoding.json";
    /**
     * 租船确认单_卖家确认订单
     * 
     */
    public static final String CONCONFIRMCLI = "/conconfirmcli.json";
    /**
     * 租船确认单_下拉框查询
     * 
     */
    public static final String QUERYSELECT = "/querySelect.json";

    /**
     * 租船确认单_港口_下拉框查询
     *
     */
    public static final String QUERYSELECTPORT = "/querySelectPort.json";

    /**
     * 租船代理下拉内容
     *
     */
    public static final String QUERYINDEPENDENT= "/querySelectIndependent.json";
    
    /**
     * 下载文件公共方法（OSS）
     */
    public static final String DOC_DOWNLOAD_OSS = "common/doc/download.htm";//

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~微信API~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * 查看租船协议单详情
     */
    public static final String AGREEMENT_DETAILS_APP = "/query/agreementDetailsApp.json";

    /**
     * 租船需求列表查询 微信端API
     */
    public static final String WECHAT_QUERY_DEMANDS = "wechat/getDemandsList.json";

    /**
     * 租船协议列表(中化新)查询 微信端API
     */
    public static final String WECHAT_QUERY_AGREEMENT_MYS = "wechat/getAgreementList.json";
    
    /**
     * 租船协议列表(租船代理)查询 微信端API
     */
    public static final String WECHAT_QUERY_AGREEMENT_ZCDL = "wechat/getAgreementListZcdl.json";

    /**
     * 租船协议列表查询 微信端API
     */
    public static final String WECHAT_QUERY_CONFIRMATION_SHEET = "wechat/getConfirmationSheetList.json";

    /**
     * 根据Uuid查询详情 微信端API
     */
    public static final String WECHAT_QUERYBYUUID_AGREEMENT = "wechat/getAgreementByUuid.json";

    /**
     * 根据Uuid查询船舶确认单信息 微信端API
     */
    public static final String WECHAT_GETCONFIRMATIONSHEET = "wechat/getConfirmationSheet.json";

    /**
     * 配载计划查询 && 查看航程信息 微信端API
     */
    public static final String WECHAT_GETSTOWAGEPLAN = "wechat/getStowagePlan.json";

    /**
     * 查询船舶装港信息   船舶装港进度表 微信端API
     */
    public static final String WECHAT_GETLOADPORTDEATIL = "wechat/getLoadPortDeatil.json";

    /**
     * 船舶在途信息查询 微信端API
     */
    public static final String WECHAT_GETUNLOADLIST = "wechat/getUnloadList.json";

    /**
     * 查询船舶卸港信息 微信端API
     */
    public static final String WECHAT_GETFINDUNLOADPORTDEATIL = "wechat/getFindUnloadPortDeatil.json";

    /**
     * 装港信息查询 微信端API
     */
    public static final String WECHAT_LOADING_POART = "wechat/loadingPort.json";

    /**
     * 物流跟踪
     */
    public static final String WECHAT_GET_ALL_DATAS = "wechat/getAllDatas.json";

    /**
     * 物流跟踪 imowaypoint
     */
    public static final String WECHAT_GET_IMOWAYPOINT = "wechat/imowaypoint.json";

    
}
