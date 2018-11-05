package com.sinochem.crude.trade;

public interface URLMapping {

	public static final String CHANNELM_INSERT = "channelM/insert.json";

	public static final String CHANNELM_UPDATE = "channelM/update.json";

	public static final String CHANNELM_DELETE = "channelM/delete.json";

	public static final String CHANNELM_QUERY = "channelM/query.json";
	
	public static final String TRANSPORT_QUERY = "/front/transportInfoList.json";
	
	public static final String DOLLARQUOTE_QUERY = "/front/indecPriceDollar.json";
	
	public static final String PMISAGIO_QUERY = "/query/agio.json";
	
	public static final String PMISOFFICIAL_QUERY = "/query/officialPrice.json";
	
	public static final String PMIFUTUREPRICE_QUERY = "/query/futurePrice.json";
	
	public static final String PMIVENDPRICE_QUERY = "/query/vendPrice.json";
	
	public static final String QUALITY_QUERY = "/query/quality.json";
	
	public static final String THREADINFO_UPDATE = "/info/tread.json";
	
	public static final String PRAISEINFO_UPDATE = "/info/praise.json";
	
	public static final String SHAREINFO_UPDATE = "/share/shareInfo.json";
	
	public static final String COLLECTIONINFO_UPDATE = "/collection/collectionInfo.json";
	
	public static final String OILLIST_QUERY = "/front/queryOilList.json";
	
	public static final String AGIOPOINT_QUERY = "/front/agioPoint.json";
	
	public static final String OFFICALLIST_QUERY = "/front/queryOfficalOilList.json";
	
	public static final String OFFICALPOINT_QUERY = "/front/officalPoint.json";
	
	public static final String OFFICALLISTQUERY_QUERY = "/front/officialList.json";
	
	public static final String COLCLUMNINFO_ADD = "/column/addInfo.json";
	
	public static final String COLCLUMNINFO_SAVE = "/column/savePreviewInfo.json";
	
	public static final String COLCLUMNINFO_QUERY = "/column/infoDetail.json";
	
	public static final String COLCLUMNINFO_HOT = "/column/hotInfoList.json";
	
	public static final String COLCLUMN_APPLY = "/column/applyOrEditColumn.json";
	
	public static final String COLCLUMN_PREVIEW = "/column/savePreviewColumn.json";
	
	public static final String COLCLUMN_QUERY = "/column/previewColumn.json";
	
	public static final String COLCLUMN_MY = "/column/myColumn.json";
	
	public static final String COLCLUMN_LIST = "/column/columnList.json";
	
	public static final String COLCLUMN_INFO_LIST = "/column/infoList.json";
	
	public static final String COLLECTION_INFO_LIST = "/collection/myCollect.json";
	
	public static final String SUBSCRIBE_COLUMN_LIST = "/column/mySubscibe.json";
	
	public static final String SUBSCRIBE_ARTICLE_LIST = "/column/mySubscibeArticle.json";
	
	public static final String COMMENT_ARTICLE_LIST = "/comment/myComment.json";
	
	public static final String MEMBER_MYMEMBER_LIST = "/member/myMymember.json";
	
	public static final String MEMBER_MYMEMBER_AUDIT = "/member/memberAudit.json";
	
	public static final String AGIOLISTQUERY_QUERY = "/front/agioT.json";
	public static final String INFOLISTOS_QUERY = "/front/infoListOS.json";
	/** 获取资讯频道列表App---频道栏*/
	String CMS_APPCHANNELMLIST = "/channelM/appChannelMList.json";
	/** 获取资讯主频道列表---频道栏*/
	String CMS_CHANNELMLIST = "/channelM/channelMList.json";
	/** 获取资讯子频道列表---频道栏*/
	String CMS_CHANNELSUBLIST = "/channelM/channelSubList.json";
	/** 保存关注(APP)*/
    String APPS_SAVEATTENTIONFORAPP = "/mobile/attentions/save.json";
    /** 获取个人所有菜单*/
    String APPS_QUERYMENUALL = "/mobile/attention/queryMenuAll.json";
	
	/**
	 * 外部系统接口(信息发布平台)
	 */
	public static final String EXTERNAL_INTER = "external/interactive.json";
	
	/**
	 * 使用接口数据，测试用
	 */
	public static final String EXTERNAL_USE = "external/use.json";
	

	/** ============值集============ **/
	/** 查询值集类别list */
	String CODE_SET_LIST = "/values/codeSetList.json";
	/** 批量查询值集 */
	String CODE_SET_LIST_BY_ARR = "/values/queryCodeSetListByArr.json";
	/** 查询值集项目list */
	String CODE_ITEM_LIST = "/values/codeItemList.json";
	/** 查询值集语言类型list */
	String CODE_LIST = "/values/codeList.json";
	/** 新增值集 */
	String ADD_CODE_SET = "/values/addCodeSet.json";
	/** 删除值集类别 */
	String DEL_CODE_SET = "/values/delCodeSet.json";
	/** 删除值集项目 */
	String DEL_CODE_ITEM = "/values/delCodeItem.json";
	/** 值集是否可编辑查询 */
	String CODE_ITEM_EDITABLE = "/values/codeItemEditable.json";
	/** 值集是否可编辑查询 */
	String EDIT_CODE_SET = "/values/editCodeSet.json";
	/** 值集是否可编辑查询 */
	String EDIT_CODE_ITEM = "/values/editCodeItem.json";
	String CODE_SET_MANAGE_LIST = "/values/codeSetManageList.json";
	/** 根据值集id查询list */
	String CODE_SET_LIST_BY_KEY = "/values/codeSetListByKey.json";
	/**根据代码集代码查询值集list*/
	String CMS_QUERYCODESETLIST = "/values/queryCodeSetList.json";

	/** ============轮播图============ **/
	String UPDATE_AD_IMAGE = "/carousel/updateAdImage.json";
	String UPDATE_AD_IMAGE_L = "/carousel/updateAdImagel.json";
	String CAROUSEL_QUERY_ONE = "/carousel/queryCarouselById.json";
	String QUERY_TYPE_CODE = "/carousel/queryTypecode.json";
	String QUERY_IMAGE_DES = "/carousel/queryImagedes.json";
	String QUERY_PAGE_CODE = "/carousel/queryPageCode.json";
	String QUERY_CAROUSELS = "/carousel/queryCarousels.json";
	String ADD_AD_IMAGE_L = "/carousel/addAdImagel.json";
	String ADD_AD_IMAGE = "/carousel/addImagel.json";
	String DELETE_CAROUSEL = "/carousel/deleteCarousl.json";


	public static final String PRICEINDEXTEMP_LIST = "/om/price/priceTemp.htm"; //指数模板列表

	public static final String PRICEINDEXTEMP_SAVE_PRICEINDEX = "/price/saveOrUpdatePriceIndexTemp.json"; //新增指数模板

	public static final String PRICEINDEXTEMP_SETPRICETEMPSTATUS = "/price/setPriceIndexTempStatus.json"; //设置指数模板状态

	public static final String PRICEINDEX_LIST = "/om/price/priceMainTain.htm"; //指数列表

	public static final String PRICEINDEX_SAVEORUPDAREPRICEINDEX = "/price/saveOrUpdatePriceIndex.json"; //新增或更新指数
	
	public static final String PRICEINDEX_DELETEPRICEINDEXLIST = "/price/deletePriceIndex.json"; //重置删除指数

	public static final String BASEPRICETEMP_LIST = "/om/baseprice/basePriceTemp.htm"; //基价模板列表
	
	public static final String BASEPRICE_LIST = "/om/baseprice/basePrice.htm"; //基价列表

	public static final String BASEPRICETEMP_SAVEORUPDATE = "/baseprice/saveOrUpdateBasePriceTemp.json";//新增或修改基价

	public static final String BASEPRICETEMP_SETSTATUS = "/baseprice/setBasePriceTempStatus.json"; //设置基价模板状态

	public static final String BASEPRICE_DELETELIST = "/baseprice/deleteBasePrice.json"; //重置删除基价
	
	public static final String BASEPRICE_SAVEORUPDATE = "/baseprice/saveOrUpdateBasePrice.json"; //新增或更新指数
	
	public static final String SYMBOLINDEX_LIST = "/om/symbol/symbolIndex.htm"; //合约模板
	
	public static final String SYMBOLINDEX_SAVEORUPDARESYMBOLINDEX = "/symbol/saveOrUpdateSymbol.json"; //新增或更新合约模板
	
	public static final String SYMBOLPRICEINDEX_LIST = "/om/symbol/symbolPriceMainTain.htm"; //合约列表
	
	public static final String SYMBOLPRICEINDEX_SAVEORUPDATE = "/symbolPrice/saveOrUpdateSymbolPrice.json"; //新增或更新合约

	public static final String SYMBOLPRICEINDEX_DELETE = "/symbolPrice/deleteSymbolPrice.json"; //重置删除合约
	
	public static final String INFO_COMMENT_LIST="/info/comment/list.json";//拉取评论列表
	
	public static final String INFO_COMMENT_ADD="/info/comment/add.json";//新增评论
	
	public static final String INFO_COMMENT_DEL="/info/comment/del.json";//删除评论
	
	public static final String INFO_COMMENT_REPLY="/info/comment/reply.json";//新增回复
	
	public static final String COMMENT_REPLY_DEL="/comment/reply/del.json";//删除回复
	
	public static final String EFRACTIONMSG_LIST="/EFractionMsg/list.json";//馏分信息列表
	
	public static final String COLUMNMANAGEMENT_LIST="/om/columnManagement/audit.json";//专栏列表
	
	public static final String REVIEW_COLUMN="/om/columnManagement/reviewColumn.json";//审核专栏
	
	public static final String CHECK_COLUMN="/om/columnManagement/checkColumn.json";//查看专栏
	
	public static final String UPDATE_COLUMN="/om/columnManagement/updateColumn.json";//更新专栏
	
}
