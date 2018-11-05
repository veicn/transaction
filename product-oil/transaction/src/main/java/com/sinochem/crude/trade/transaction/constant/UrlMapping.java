package com.sinochem.crude.trade.transaction.constant;

/**
 * URL地址常量类
 * @author Yichen Zhao
 * date: 20180225
 */
public class UrlMapping {

    /** 公共 */
    public static final String INDEX = "index";
    public static final String DEMAND_INDEX = "demand_index";


    /** 示例 */
    public static final String EXAMPLE = "pages/example";
    public static final String EXAMPLE1 = "example1";
    public static final String EXAMPLE2 = "example2";
    public static final String EXAMPLE3 = "example3";

    /** 数据生成 */
    public static final String DATA_GENERATOR = "data_generator";
    /** 下载公共方法*/
    public static final String DOC_DOWNLOAD_OSS = "common/doc/download.htm";

    /** 组件 */
    public static final String COMPONENTS = "pages/components";

    /** 前台 */
    /* 商品报价 */
    public static final String PRODUCT_BIDDING = "pages/front/product_bidding";
    public static final String QUOTE = "quote";
    public static final String QUOTE_JSON = "quote.json";
    public static final String BID_JSON = "bid.json";
    public static final String DEMANDBID_JSON = "demand_bid.json";

    public static final String DEMAND_QUOTE = "demand_quote";

    /* 商品比较 */
    public static final String PRODUCT_COMPARE = "pages/front/product_compare";

    /* 商品详情 */
    public static final String PRODUCT_DETAIL = "pages/front/product_detail";
    public static final String BERTH_LIST = "berth_list";

    /* 成品油商品列表 */
    public static final String PRODUCT_LIST = "pages/front/product_list";
    public static final String SALE_QUERY = "sale_query";
    public static final String SALE_QUERY_ORDER = "sale_query_order";

    /* 发布商品销售资源 */
    public static final String PUBLISH_PRODUCT = "pages/front/publish_product";
    public static final String PUBLISH_DEMAND = "pages/front/publish_demand";
    public static final String POST_DEMAND_SHEET = "post_demand_sheet";
    public static final String POST_SALE_SHEET = "post_sale_sheet";
    public static final String POST_SALE_SHEET_JSON = "post_sale_sheet.json";
    public static final String PORT_LIST = "port_list";
    public static final String GET_SPECIFICATION_LIST = "get_specification_list";

    /** 后台 */
    /* 我的报价列表 */
    public static final String MY_BIDDING_LIST = "pages/back/my_bidding_list";
    public static final String MY_DEMAND_BIDDING_LIST = "pages/back/my_demand_bidding_list";

    /* 成品油订单详情*/
    public static final String MY_CONTRACT_DETAIL = "pages/back/my_contract_detail";
    public static final String GET_CONTRACT_SHEET_UUID = "getContractSheetUuid";
    public static final String MY_CONTRACT_DETAIL_UPLOADONE = "uploadRefinery";
    public static final String MY_CONTRACT_DETAIL_UPLOADTWO = "uploadShoreTanks";
    public static final String MY_CONTRACT_DETAIL_UPLOADTHREE = "uploadciq";
    public static final String MY_CONTRACT_DETAIL_UPLOADFOUR = "uploadloadingSurveyReport";


    /* 成品油采購资源列表 */
    public static final String MY_DEMAND_LIST = "pages/back/my_demand_list";

    /* 成品油订单列表 */
    public static final String MY_CONTRACT_LIST = "pages/back/my_contract_list";
    public static final String MY_CONTRACT_LIST_Index = "redirect:/pages/back/my_contract_list/index.htm";

    /* 报价对比 */
    public static final String MY_PRODUCT_BIDDING_COMPARE = "pages/back/my_product_bidding_compare";

    /* 报价详情 */
    public static final String MY_PRODUCT_BIDDING_DETAIL = "pages/back/my_product_bidding_detail";

    /* 我的报价详情 */
    public static final String MY_BIDDING_DETAIL = "pages/back/my_bidding_detail";
    public static final String MY_DEMAND_BIDDING_DETAIL = "pages/back/my_demand_bidding_detail";

    /* 报价清单 */
    public static final String MY_PRODUCT_BIDDING_LIST = "pages/back/my_product_bidding_list";
    public static final String CONFIRM_BIDDING = "confirm_bidding";
    public static final String CONFIRM_BIDDING_JSON = "confirm_bidding.json";
    public static final String CANCEL_SALE_SHEET = "cancel_sale_sheet";

    /* 资源详情 */
    public static final String MY_PRODUCT_DETAIL = "pages/back/my_product_detail";
    public static final String MY_DEMAND_DETAIL = "pages/back/my_demand_detail";

    /* 成品油资源列表 */
    public static final String MY_PRODUCT_LIST = "pages/back/my_product_list";

    /**
     * 票据中心controller
     */
    public static final String BILL_CORE_UPLOAD = "pages/back/billCoreUpload";

    /**
     * 登录相关
     */
    public static final String LOGIN = "log";

    /**
     * 推出登录
     */
    public static final String LOG_OUT = "/logout";

    /**
     * 首页地址
     */
    public static final String HOME_PAGE_INDEX = "redirect:/pages/portal/home/index.htm";

    /* 上架   下架 */
    public static final String SHELF_OR_OFFSHELF = "shelfOrOffShelf";
    /*删除*/
    public static final String DELETE = "delete";
    /*重定向我的资源列表*/
    public static final String MY_PRODUCT_LIST_Index_HTM= "redirect:/pages/back/my_product_list/index.htm";

    /*重定向我的报价列表*/
    public static final String MY_BIDDING_LIST_Index_HTM= "redirect:/pages/back/my_bidding_list/index.htm";

    /* 修改销售单 */
    public static final String EDIT_SALE_SHEET = "pages/front/edit_sale_sheet";
    public static final String EDIT_DEMAND_SHEET = "pages/front/edit_demand_sheet";
    public static final String COMMIT_SALE_SHEET_EDIT = "commit_sale_sheet_edit";
    public static final String COMMIT_DEMAND_SHEET_EDIT = "commit_demand_sheet_edit";

    /**
     * 上传文件新增
     */
    public static final String UPLOAD_SAVEALL = "/uploadSaveAll";
    /**
     * 上传文件新增
     */
    public static final String UPLOAD_SAVE = "/uploadSave";
    /**
     * 上传文件修改
     */
    public static final String UPLOAD_UPDATE = "/uploadUpdate";
    /**
     * 上传文件删除
     */
    public static final String UPLOAD_DELETE = "/uploadDelete.json";

    public static final String COMMIT_SALE_SHEET_EDIT_JSON = "commit_sale_sheet_edit.json";


    /** Dubbo服务 */
    public static final String GET_ALL_ENTERPRISE_LIST = "/getAllEnterpriseList";

    //DemandSheetController主路径
    public static final String DEMAND_SHEET = "/pages/back/my_demand_list";
    //采购报价详情页面
    public static final String DEMAND_BIDDING_INVENTORY = "/demand_bidding_inventory.htm";
    //接受报价ajax
    public static final String DEMAND_ACCEPT = "/accept.json";
    //报价和采购详情对比页面
    public static final String DEMAND_BIDDING_COMPARE= "/demand_bidding_compare";
    //关闭采购需求ajax
    public static final String DEMAND_CANCEL= "/cancel.json";

    /** 后台om 订单管理*/
    public static final String BGOM_CONTRACT = "pages/backgroundom/my_contract";


    public static final String UPDATE_TRANS_EXTEND = "updateTransExtend.json";
    public static final String MY_CONTRACT_DETAIL_APPOINT = "contractAppoint.json";
    public static final String APPOINT_LIST = "appoint_list";

}
