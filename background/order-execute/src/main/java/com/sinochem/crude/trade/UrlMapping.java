package com.sinochem.crude.trade;

@SuppressWarnings({ "all" })
public interface UrlMapping {
	/****************************** 系统模块 **************************************/
	String INTERFACELIST_LIST_INIT = "/om/interface/interfaceList.htm";
	String INTERFACELIST_LIST = "/interface_list/list.json";
	String INTERFACELIST_QUERY = "/interface_list/query.json";
	String INTERFACELIST_ADD = "/interface_list/add.json";
	String INTERFACELIST_EDIT = "/interface_list/edit.json";
	String INTERFACELIST_DEL = "/interface_list/del.json";
	String INTERFACEINPUT_LOGGING_JSON = "/interface_list/queryLogging.json";
	String INTERFACEOUTPUT_INTPUT = "/om/interface/interfaceInput.htm";
	
	String INTERFACEMEMBER_LIST_INIT = "/om/interface/interfaceMember.htm";
	String INTERFACEMEMBER_LIST = "/interface_member/list.json";
	String INTERFACEMEMBER_QUERY = "/interface_member/query.json";
	String INTERFACEMEMBER_ADD = "/interface_member/add.json";
	String INTERFACEMEMBER_EDIT = "/interface_member/edit.json";
	String INTERFACEMEMBER_DEL = "/interface_member/del.json";
	
	String INTERFACESYSTEM_INIT = "/om/interface/interfaceSystem.htm";
	String INTERFACESYSTEM_LIST = "/interface_system/list.json";
	String INTERFACESYSTEM_ADD = "/interface_system/add.json";
	String INTERFACESYSTEM_EDIT = "/interface_system/edit.json";
	String INTERFACESYSTEM_DEL = "/interface_system/del.json";
	
	String INTERFACE_CONTRAST_INIT = "/om/interfaceContrast/interfaceContrast.htm";
	String INTERFACE_CONTRAST = "/interface_contrast/list.json";
	String INTERFACE_DEL = "/interface_contrast/del.json";
	String INTERFACE_UPD = "/interface_contrast/edit.json";
	String INTERFACE_ADD = "/interface_contrast/add.json";
	
	String QUERY_SYS_NAME = "/interface_system/querySysName.json";
	String QUERY_MEMBER_TYPE = "/interface_system/queryMemberType.json";
	String QUERY_MEMBER_INFO = "/interface_system/queryMemberInfoByType.json";
	
	String INTERFACEOUTPUT_LIST = "/interface_output/list.json";
	String INTERFACEOUTPUT_EDIT = "/interface/interfaceOutputEdit.json";
	String INTERFACEOUTPUT_OUTPUT = "/om/interface/interfaceOutput.htm";
	
	String TRADESUBJECT_INIT = "/om/trade/tradeSubject.htm";
	String TRADESUBJECT_INIT_LIST = "/trade/tradeSubject.json";
	String TRADESUBJECT_ADD = "/trade_subject/add.json";
	String TRADESUBJECT_EDIT = "/trade_subject/edit.json";
	String TRADESUBJECT_DEL = "/trade_subject/del.json";
	/****************************** 合同管理 **************************************/
	String CONTRACT_LIST_HTM = "buyerCenter/contract/list.htm";
	String CONTRACT_DELETE = "common/contract/delete.htm";
	String CONTRACT_DETAIL_HTM = "common/contract/detail.htm";
	String CONTRACT_ADD = "common/contract/add.htm";
	String CONTRACT_EDIT = "common/contract/edit.htm";
	
	/****************************** 订单管理 **************************************/
	String ORDER_LIST_BUYER = "buyerCenter/order/list.htm";
	String ORDER_LIST_SELLER = "sellerCenter/order/list.htm";
	String ORDER_DETAIL_BUYER = "buyerCenter/order/detail.htm";
	String ORDER_DETAIL_SELLER = "sellerCenter/order/detail.htm";
	String ORDER_PAYCONFIRM_DETAIL = "/sellerCenter/order/orderPayConfirm.htm";
	String ORDER_PAYCONFIRM_SAVE = "/sellerCenter/order/savePayConfirm.json";
	String ORDER_CONTAIN_OPERATE_BTNS = "contain/order/operateButtons.htm";
	String ORDER_CONTAIN_GOODSDETAIL = "contain/order/goodsDetail.htm";
	String ORDER_INCOME_STATEMENT = "common/order/incomeStatement.htm";
	String ORDER_SHIP_IMO = "common/order/findImoByShipName.json";
	String ORDER_EDIT_CONTRACT_NO = "common/order/editContractNo.json";
	String ORDER_SAVE_CONTRACT_NO = "common/order/saveContractNo.json";
	String SAVE_TRIGGER_APPLY = "bomp/triggerApply/save.json";//提交点价申请
	String CANCEL_TRIGGER_APPLY = "triggerApply/cancelApply.json";//撤销点价申请
	String TRIGGER_APPLY_PROCESS = "triggerApply/complete.json";//点价申请处理
	String TRIGGER_APPLY_NODEAL = "triggerApply/noDeal.json";//点价-未成功
	String UPDATE_TRIGGERCONTRACT_QT = "triggerContractr/updateQuantity.json";//修改合约数量
	String RESET_TRIGGERCONTRACT = "triggerContractr/resetContract.json";//合约重置
	String SPLIT_TRIGGER_ORDER = "triggerResult/splitOrder.json";//拆单操作
	String UPDATE_SPLIT_ORDER = "triggerResult/updateSplitOrder.json";//更新拆单数量、价格等信息
	String TRIGGER_TRANSFER_ADD_INIT = "bomp/triggerTransfer/add.htm";//点价转月新增弹框
	String SAVE_TRIGGER_TRANSFER = "triggerTransfer/save.json";//保存转月信息
	
	/****************************** 费用管理 **************************************/
	String FEE_INFO = "/sellerCenter/orderFee/orderFeeInfo.htm";
	String FEE_SAVE = "/orderFee/saveOrderFee.json";
	
	/****************************** 单证模块 **************************************/
	String DOCUMENT_CHECK = "/bomp/docUpload/batchUpload.htm";
	String DOCUMENT_EDIT_NEW = "/buyerCenter/orderDocument/documentEditNew.htm";
	String DOCUMENT_LIST = "/buyerCenter/orderDocument/documentList.htm";
	String DOCUMENT_SAVE = "/buyerCenter/orderDocument/saveDocument.json";
	String DOCUMENT_FILE_LIST = "/buyerCenter/orderDocumentFile/documentFileList.htm";
	String DOCUMENT_FILE_DELETE = "/buyerCenter/orderDocumentFile/deleteFile.json";
	
	String DOCUMENT_INIT = "/om/order/documentList.htm";
	String DOCUMENT_INIT_LIST="/order/documentList.json";
	String DOCUMENT_LIST_BUYID="/order/documentListById.json";
	String DOCUMENT_LIST_ADD="/order/addDocumentList.json";
	String DOCUMENT_LIST_SET_ADD = "/order/addDocumentListSet.json";
	String DOCUMENT_LIST_EDIT = "/order/editDocumentList.json";
	String DOCUMENT_LIST_SET_EDIT = "/order/editDocumentListItem.json";
	String DOCUMENT_LIST_DEL = "/order/delDocumentList.json";
	String DOCUMENT_LIST_SET_DEL = "/order/delDocumentListItem.json";
	
	/****************************** 异议模块 **************************************/
	String FEE_DISSENT_REPLY = "/sellerCenter/orderFeeDissent/orderFeeDissentReply.htm";
	String FEE_DISSENT_REPLY_SAVE = "/sellerCenter/orderFeeDissent/saveReplyContent.json";
	String FEE_DISSENT_DISSENT = "/buyerCenter/orderFeeDissent/orderFeeDissent.htm";
	String FEE_DISSENT_DISSENT_SAVE = "/buyerCenter/orderFeeDissent/saveDissentContent.json";
	
	/****************************** 对账单相关 **************************************/
	String S_STATEMENT_INFO_PRE = "/sellerCenter/orderStatement/infoPre.htm";//预估对账单 查看、编辑、新增
	String S_STATEMENT_INFO_FIN = "/sellerCenter/orderStatement/infoFin.htm";//正式对账单 查看、编辑、新增
	String STATEMENT_SAVE_PRE = "/orderStatement/saveOrderStatementPre.json";//对账单保存 修改
	String S_STATEMENT_LIST_PRE = "/sellerCenter/orderStatement/listPre.htm";//卖家预估对账单列表
	String S_STATEMENT_LIST_FIN = "/sellerCenter/orderStatement/listFin.htm";//卖家正式对账单列表
	String STATEMENT_COMFIRM = "/orderStatement/statementComfirm.json";//对账单确认
	String B_STATEMENT_INFO_FIN = "/buyerCenter/orderStatement/infoFin.htm";//买家正式对账单详情
	String B_STATEMENT_INFO_PRE = "/buyerCenter/orderStatement/infoPre.htm";//买家预估对账单详情
	String B_STATEMENT_LIST_PRE = "/buyerCenter/orderStatement/listPre.htm";//买家预估对账单列表
	String B_STATEMENT_LIST_FIN = "/buyerCenter/orderStatement/listFin.htm";//买家正式对账单列表
	String TRIGGER_RESULT_LIST = "/contain/order/triggerResultDetail.htm";//拆单列表
	String STATEMENT_STATUS ="/orderStatement/statementEditStatus.json";	//上传后修改状态
	String S_STATEMENT_EDIT_STATUS = "/orderStatement/infoPreEditStatus.json";//卖家结算单已确认前可撤销
	
	/****************************** 结算单相关 **************************************/
	String S_SETTLEMENT_INFO_PRE = "/sellerCenter/orderSettlement/infoPre.htm";
	String S_SETTLEMENT_INFO_FIN = "/sellerCenter/orderSettlement/infoFin.htm";
	String SETTLEMENT_SAVE_PRE = "/orderSettlement/saveOrderSettlementPre.json";
	String S_SETTLEMENT_LIST_PRE = "/sellerCenter/orderSettlement/listPre.htm";//卖家预估结算单列表
	String S_SETTLEMENT_LIST_FIN = "/sellerCenter/orderSettlement/listFin.htm";//卖家正式对账单列表
	String B_SETTLEMENT_INFO_PRE = "/buyerCenter/orderSettlement/infoPre.htm";
	String B_SETTLEMENT_INFO_FIN = "/buyerCenter/orderSettlement/infoFin.htm";
	String B_SETTLEMENT_LIST_PRE = "/buyerCenter/orderSettlement/listPre.htm";//买家预估结算单列表
	String B_SETTLEMENT_LIST_FIN = "/buyerCenter/orderSettlement/listFin.htm";//买家正式结算单列表
	String SETTLEMENT_LIST = "/OrderSettlement/OrderSettlement.htm";
	
	/****************************** 外部系统调用接口 **************************************/
	String OPENAPI_CONTRACTINFO_RECEIVE = "/openapi/contract_info/receive.json";
	String OPENAPI_SHIPINFO_RECEIVE = "/openapi/ship_info/receive.json";
	String OPENAPI_PORTLOADINGINFO_RECEIVE = "/openapi/port_loading_info/receive.json";
	String OPENAPI_PORTDISCHARGEINFO_RECEIVE = "/openapi/port_discharge_info/receive.json";
	String OPENAPI_STATEMENTSHEET_RECEIVE = "/openapi/statement_sheet/receive.json";
	String OPENAPI_DOCINFO_RECEIVE = "/openapi/doc_info/receive.json";
	String OPENAPI_RECEIVABLES_SYNCHRONIZE = "/openapi/receivables/synchronize.json";
	
	/****************************** 移动端调用接口 **************************************/
	String DOCUMENT_FILE_JSON = "/buyerCenter/orderDocumentFile/documentFileList.json";//单证列表
	String DOCUMENT_DETAIL = "/orderDocument/getDocumentDetail.json";//单证详情
	String ORDER_LIST_JSON = "api/order/list.json";//订单列表
	String ORDER_DETAIL_JSON = "api/order/detail.json";//订单详情
	String CONTRACT_LIST_JSON = "buyerCenter/contract/list.json";//合同列表
	String CONTRACT_DETAIL_JSON = "common/contract/detail.json";//合同详情
	
	String S_STATEMENT_INFO_PRE_JSON = "/sellerCenter/orderStatement/infoPre.json";//卖家临时结算单详情
	String S_STATEMENT_INFO_FIN_JSON = "/sellerCenter/orderStatement/infoFin.json";//卖家最终结算单详情
	String S_STATEMENT_LIST_PRE_JSON = "/sellerCenter/orderStatement/listPre.json";//卖家临时结算单列表
	String S_STATEMENT_LIST_FIN_JSON = "/sellerCenter/orderStatement/listFin.json";//卖家最终结算单列表
	String B_STATEMENT_INFO_PRE_JSON = "/buyerCenter/orderStatement/infoPre.json";//买家临时结算单详情
	String B_STATEMENT_INFO_FIN_JSON = "/buyerCenter/orderStatement/infoFin.json";//买家最终结算单详情
	String B_STATEMENT_LIST_PRE_JSON = "/buyerCenter/orderStatement/listPre.json";//买家临时结算单列表
	String B_STATEMENT_LIST_FIN_JSON = "/buyerCenter/orderStatement/listFin.json";//买家最终结算单列表
	
	/************************************** 文件上传 **************************************/
	String DOC_UPLOAD_OSS = "common/doc/upload.json";//上传文件公共方法（OSS）
	String DOC_UPLOAD_DOCCENTER = "common/doc/uploadDoc.json";//单证上传，文档中心
	String DOC_DOWNLOAD_OSS = "common/doc/download.htm";//下载文件公共方法（OSS）
	String DOC_FILE_STREAM = "common/doc/file.htm";//获取文件流（OSS）
	String GET_OSS_PARAMS = "api/oss/getParams.json";//获取OSS上传参数
	/************************************** 浏览文件 **************************************/
	String BROWSER_FILE_OSS = "common/doc/browserFile.htm";//浏览文件公共方法（OSS）
	
	/****************************** 装卸港调用接口 **************************************/
	String UPDATE_SHIP_LOADING = "/ship/upadteLoading.json";//修改装港信息
	String SAVE_SHIP_LOADING = "/ship/saveLoading.json"; //新增装港信息
	String DEL_SHIP_LOADING = "/ship/delLoading.json"; //删除装港信息
	String UPDATE_SHIP_UNLOADING = "/ship/upadteUnloading.json";//修改卸港信息
	String SAVE_SHIP_UNLOADING = "/ship/saveUnloading.json"; //新增卸港信息
	String DEL_SHIP_UNLOADING = "/ship/delUnloading.json"; //删除卸港信息
	String QUERY_LOADING_TYPE = "/ship/queryLoadingType.json";//获取装港值集
	String QUERY_UNLOADING_TYPE = "/ship/queryUnloadingType.json";//获取卸港值集
	String EDIT_SHIPINFO = "/ship/updateShip.json";//维护船舶信息
	
	/****************************** 商检功能**************************************/
	String INSPECTION_lIST = "inspection/staff/list.htm";
	String INSPECTION_ADD = "inspection/staff/add.json";
	String INSPECTION_EDIT = "inspection/staff/edit.json";
	String INSPECTION_DEL = "inspection/staff/del.json";
	
	String INSPECTION_SELECT = "bomp/inspection/selectUser.htm";//商检机构列表弹框
	String QUERY_CONTACT_BY_INSPECTION = "api/inspection/queryContactByInspection.json";//查询商公司维护的联系人
	String SAVE_LOADING_INSPECTION_MEMBER = "api/inspection/saveLoadingInspectionMember.json";//保存装港指定商检信息
	String SAVE_UNLOADING_INSPECTION_MEMBER = "api/inspection/saveUnloadingInspectionMember.json";//保存卸港指定商检公司及联系人信息
	String INSPECTION_ORDER_LIST = "inspection/order/list.htm";//商检订单列表
	String GET_INSPECTION_DISCHARGEPORT_DETAIL = "inspection/dischargeport/detail.htm";//卸港商检详情
	String EDIT_INSPECTION_DISCHARGEPORT_DETAIL = "inspection/dischargeport/edit.htm";//卸港商检详情编辑初始化
	String GET_INSPECTION_LOADPORT_DETAIL = "inspection/loadport/detail.htm";//装港商检详情
	String EDIT_INSPECTION_LOADPORT_DETAIL = "inspection/loadport/edit.htm";//装港商检详情编辑初始化
	String SAVE_INSPECTION_LOADPORT_DETAIL = "inspection/loadport/saveDetail.json";//商检详情编辑保存
	String SAVE_INSPECTION_DISCHARGEPORT_DETAIL = "inspection/dischargeport/saveDetail.json";//商检详情编辑保存
	
	/****************************** 报关报检 **************************************/
	String INIT_DECLARE_TEMPLATE = "inspection/template/declare.htm";//初始化报关委托书信息
	String INIT_INSPECTION_TEMPLATE = "inspection/template/inspection.htm";//初始化报检委托书信息
	String SAVE_DECLARE_TEMPLATE = "inspection/template/declare.json";//保存报关委托书信息
	String SAVE_INSPECTION_TEMPLATE = "inspection/template/inspection.json";//保存报检委托书信息
	String DOWNLOAD_TEMPLATE = "inspection/template/download.htm";//下载委托书
}
