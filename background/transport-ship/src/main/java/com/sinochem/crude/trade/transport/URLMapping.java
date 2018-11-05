package com.sinochem.crude.trade.transport;

public class URLMapping {

	/********************************高************************************/
	
	/**船舶装港信息维护*/
	public static final String SAVE_LOAD_PORT = "/loadPort/saveLoadPort.json";
	/**查询船舶装港信息*/
	public static final String FIND_LOAD_PORT_DEATIL = "/loadPort/findLoadPortDeatil.json";
	/**船舶航次开始信息维护*/
	public static final String SAVE_VOYAGE_START = "/voyageStart/saveVoyageStart.json";
	/**查询船舶航次开始信息*/
	public static final String FIND_VOYAGE_START_DETAIL = "/voyageStart/findVoyageStartDeatil.json";
	/**查询船舶航次开始油种信息*/
	public static final String FIND_OIL_LIST = "/voyageStart/findOilList.json";
	/**船舶在途信息维护*/
	public static final String SAVE_TRANSIT = "/transit/saveTransit.json";
	/**查询船舶在途信息详情*/
	public static final String FIND_TRANSIT_DETAIL = "/transit/findTransitDeatil.json";
	/**删除船舶在途信息*/
	public static final String DELETE_TRANSIT_BY_UUID = "/transit/deleteTransitByUuid.json";
	/**查询船舶在途卸港信息*/
	public static final String UNLOAD_LIST = "/transit/unloadList.json";
	/**查询船舶在途信息列表*/
	public static final String QUERY_TRANSIT_LIST = "/transit/queryTransitList.json";
	/**查询物流跟踪查看更多在途信息列表*/
	public static final String QUERY_MORE_TRANSIT_LIST = "/transit/queryMoreTransitList.json";
	/**船舶卸港信息维护*/
	public static final String SAVE_UNLOAD_PORT = "/unloadPort/saveUnloadPort.json";
	/**查询船舶卸港信息*/
	public static final String FIND_UNLOAD_PORT_DETAIL = "/unloadPort/findUnloadPortDeatil.json";
	/**货物装港信息维护*/
	public static final String SAVE_SHIPMENT = "/shipment/saveShipment.json";
	/**查询货物装港信息*/
	public static final String FIND_SHIPMENT_DETAIL = "/shipment/findShipmentDeatil.json";
	/**货物卸港信息维护*/
	public static final String SAVE_DISBURDEN = "/disburden/saveDisburdent.json";
	/**查询货物卸港信息*/
	public static final String FIND_DISBURDEN_DETAIL = "/disburden/findDisburdenDeatil.json";
	/**生成运单*/
	public static final String SAVE_WAYBILL = "/waybill/saveWaybill.json";
	/**生成运单多个*/
	public static final String SAVE_WAYBILL_MANY = "/waybill/saveWaybillMany.json";
	/**查询运单列表*/
	public static final String QUERY_WAYBILL_LIST = "/waybill/queryWaybillList.json";
	/**取消运单*/
	public static final String CANCEL_WAYBILL = "/waybill/cancelWaybill.json";
	/**生成代理协议*/
	public static final String SAVE_AGREEMENT = "/agreement/saveAgreement.json";
	/**生成代理协议*/
	public static final String AGREEMENT_SAVE = "/agreement/agreementSave.json";
	/**查询代理协议详情*/
	public static final String FIND_AHREEMENT_DETAIL = "/agreement/findAgreementDetail.json";
	/**根据合同uuid查询代理协议详情*/
	public static final String FIND_SHIPPACT_DETAIL = "/agreement/findShipPactDetail.json";
	/**查询代理协议详情多个*/
	public static final String FIND_AHREEMENT_DETAIL_MANY = "/agreement/findAgreementDetailMany.json";
	/**查询代理协议详情（uuid）*/
	public static final String FIND_AHREEMENT_DETAIL_BY_UUID = "/agreement/findAgreementDetailByPalletUuid.json";
	/**查询代理协议和船合同详情*/
	public static final String QUERY_AGREEMENT_AND_PACT = "/shipPact/queryAgreementAndPact.json";
	/**航次结束*/
	public static final String FINISH_SHIPPACT = "/shipPact/finishShipPact.json";
	/**校验航次结束*/
	public static final String CHECK_SHIPPACT_FINISH = "/shipPact/checkShipPactFinish.json";
	/**查询代理协议列表*/
	public static final String QUERY_AHREEMENT_LIST = "/agreement/queryAgreementList.json";
	/**取消代理协议匹配*/
	public static final String CANCEL_AGREEMENT = "/agreement/cancelAgreement.json";
	/**撤销代理协议*/
	public static final String REVOKE_AGREEMENT = "/agreement/revokeAgreement.json";
	/**删除代理协议*/
	public static final String DELETE_AGREEMENT = "/agreement/deleteAgreement.json";
	/**校验是否已租船*/
	public static final String CHECKA_GREEMENT = "/agreement/checkAgreement.json";
	/**生成船合同*/
	public static final String SAVE_SHIPPACT = "/shipPact/saveShipPact.json";
	/**查询船合同详情*/
	public static final String FIND_SHIPPACT_DEATIL = "/shipPact/findShipPactDetail.json";
	/**查询船合同列表*/
	public static final String QUERY_SHIPPACT_LIST = "/shipPact/queryShipPactList.json";
	/**值集*/
	public static final String QUERY_VALUE_SET = "/shipPact/queryValueSet.json";
	/**值集基础运价*/
	public static final String QUERY_VALUE_SET_EN = "/shipPact/queryValueSetEn.json";
	/**值集前台*/
	public static final String QUERY_VALUE_SET_ELE = "/shipPact/queryValueSetEle.json";
	/**查询物流跟踪*/
	public static final String QUERY_TRACK = "/shipPact/queryTrack.json";
	/**获取用户信息*/
	public static final String GET_USERINFO = "/shipPact/getUserInfo.json";
	/**查询船合同列表htm*/
	public static final String SHIPPACT_LIST_HTM = "/shipPact/shipPactList.htm";
	/**查询未匹配代理协议列表htm*/
	public static final String AGREEMENT_MATCH_LIST_HTM = "/agreement/agreementMatchList.htm";
//	/**租船信息_有订单列表htm*/
//	public static final String ORDER_LIST_HTM = "/agreement/orderList.htm";
//	/**租船信息_无订单列表htm*/
//	public static final String UNORDER_LIST_HTM = "/agreement/unOrderList.htm";
	/**代理协议列表htm*/
	public static final String AGREEMENT_LIST_HTM = "/agreement/agreementList.htm";
	/**查询承运商列表*/
	public static final String TRADERNAMELISTFORBACK = "/traderNameListForBack.json";
	/**货主查询代理协议htm*/
	public static final String PALLET_AGREEMENT_LIST_HTM = "/pallet/agreementList.htm";
	/**查询在途列表htm*/
	public static final String SHIP_TRANSIT_LIST_HTM = "/shipPact/shipTransit.htm";
	/**查询在途列表htm 新*/
	public static final String SHIP_TRANSIT_LIST_HTM_NEW = "/shipPactNew/shipTransit.htm";
	/**根据船盘uuid，查询船合同信息 */
	public static final String QUERY_BY_SHIPPLATEUUID= "/shipPact/queryByShipPlateUuid.json";
	/**根据询盘uuid，查询船合同信息 */
	public static final String QUERY_BY_INTENTION_UUID= "/shipPact/queryByIntentionUuid.json";
	/**平台协议查询htm*/
	public static final String QUERY_AGREEMENT_PLATFORM = "/om/platform/agreementList.htm";
	
	
	
	
	
	/********************************王************************************/

	/**平台船舶信息维护_新增*/
	public static final String SAVE_SYS_SHIP = "/sysShip/saveSysShip.json";
	/**平台船舶信息维护_修改*/
	public static final String UPDATE_SYS_SHIP = "/sysShip/updateSysShip.json";
	/**平台船舶信息维护_修改状态*/
	public static final String UPDATE_SYS_SHIP_STATUS = "/sysShip/updateSysShipStatus.json";
	/**平台船舶信息维护_删除*/
	public static final String DEL_SYS_SHIP = "/sysShip/delSysShip.json";
	/**平台船舶信息维护_详细*/
	public static final String FIND_SYS_SHIP_DETAIL = "/sysShip/findSysShipDetail.json";
	/**平台船舶信息维护_翻页列表*/
	public static final String FIND_SYS_SHIP_PAGE_LIST = "/sysShip/findSysShipPageList.json";
	/**平台船舶信息维护_列表*/
	public static final String FIND_SYS_SHIP_LIST = "/sysShip/findSysShipList.json";
	/**查询船舶名称列表*/
	public static final String FIND_SYS_SHIP_NAME_LIST = "/sysShip/findSysShipNameList.json";
	/**平台船舶信息维护_翻页列表*/
	public static final String GET_SYS_SHIP_PAGE_LIST = "/sysShip/sysShipList.htm";
	/**根据uuid、查询船舶状态*/
	public static final String FIND_SYS_SHIP_STATUS = "/sysShip/findSysShipStatus.json";
	/**根据Imo或船名查船舶信息(前台接口)_详细*/
	public static final String FIND_SYS_SHIP_BY_IMO_NAME = "/sysShip/findSysShipByImoName.json";
	/**根据imo、查询船舶MMSI*/
	public static final String FIND_SYS_SHIP_MMSI = "/sysShip/findSysShipMmsi.json";
	/**船舶审核*/
	public static final String CHECK_SHIP = "/sysShip/checkShip.json";
	
	/**潮汐信息维护_新增*/
	public static final String SAVE_TIDE = "/tide/saveTide.json";
	/**潮汐信息维护_修改*/
	public static final String UPDATE_TIDE = "/tide/updateTide.json";
	/**潮汐信息维护_修改状态*/
	public static final String UPDATE_TIDE_STATUS = "/tide/updateTideStatus.json";
	/**潮汐信息维护_删除*/
	public static final String DEL_TIDE = "/tide/delTide.json";
	/**潮汐信息维护_详细*/
	public static final String FIND_TIDE_DETAIL = "/tide/findTideDetail.json";
	/**潮汐信息维护_翻页列表*/
	public static final String FIND_TIDE_PAGE_LIST = "/tide/findTidePageList.json";
	/**潮汐信息维护_列表*/
	public static final String FIND_TIDE_LIST = "/tide/findTideList.json";
	/**潮汐信息维护_翻页列表*/
	public static final String GET_TIDE_PAGE_LIST = "/tide/tideList.htm";
	/**根据港口、日期查询潮汐信息(前台接口)_列表*/
	public static final String FIND_TIDE_LIST_BY_PORT_DATE = "/tide/findTideListByPortDate.json";
	
	/**船盘信息维护_新增*/
	public static final String SAVE_SHIP_PLATE = "/shipPlate/saveShipPlate.json";
	/**船盘信息维护_修改*/
	public static final String UPDATE_SHIP_PLATE = "/shipPlate/updateShipPlate.json";
	/**船盘信息维护_修改状态*/
	public static final String UPDATE_SHIP_PLATE_STATUS = "/shipPlate/updateShipPlateStatus.json";
	/**船盘信息维护_删除*/
	public static final String DEL_SHIP_PLATE = "/shipPlate/delShipPlate.json";
	/**船盘信息维护_详细*/
	public static final String FIND_SHIP_PLATE_DETAIL = "/shipPlate/findShipPlateDetail.json";
	/**船盘信息维护_翻页列表*/
	public static final String FIND_SHIP_PLATE_PAGE_LIST = "/shipPlate/findShipPlatePageList.json";
	/**船盘信息维护_列表（前台接口）*/
	public static final String FIND_SHIP_PLATE_LIST = "/shipPlate/findShipPlateList.json";
	/**船盘信息维护_详细（前台接口）*/
	public static final String FIND_SHIP_PLATE_DETAIL_FORGROUND = "/shipPlate/findShipPlateDetailForground.json";
	/**船盘信息维护_翻页列表（船东）*/
	public static final String GET_SHIP_PLATE_PAGE_LIST = "/shipPlate/shipPlateList.htm";
	/**船盘信息维护_翻页列表（前台接口）*/
	public static final String QUERY_SHIPPLATE_LIST = "/shipPlate/queryShipPlateList.json";
	/**船盘信息维护_翻页查询所有(前台接口)*/
	public static final String QUERY_ALL_SHIPPLATE_LIST = "/shipPlate/queryAllShipPlateList.json";
	/**船盘信息维护_优先船盘船盘推荐*/
	public static final String RECOMMEND_SHIP_PLATE = "/shipPlate/recommendShipPlate.json";
	
	
	
	

	/**船舶附件信息维护_新增*/
	public static final String SAVE_ACCESSORY = "/accessory/saveAccessory.json";
	/**船舶附件信息维护_删除*/
	public static final String DEL_ACCESSORY = "/accessory/delAccessory.json";
	/**船舶附件信息维护_列表*/
	public static final String FIND_ACCESSORY_LIST = "/accessory/findAccessoryList.json";
	
	/**经纪人信息维护_列表*/
	public static final String FIND_BROKER_LIST = "/broker/findBrokerList.json";
	
	/********************************庄************************************/
	
	/**租船信息_作废*//*
	public static final String PALLET_UPDATE_STATUS = "/pallet/updateStatus.json";*/
//	/**租船信息_无代理_无订单列表*/
//	public static final String PALLET_SELECT = "/pallet/palletList.htm";
//	/**租船信息_无代理_有订单列表*/
//	public static final String PALLET_QUEREY = "/pallet/palletQueryList.htm";
//	/**租船信息_有代理_无订单列表*/
//	public static final String PALLET_SELECT_AGENCY = "/pallet/palletListAgency.htm";
//	/**租船信息_无代理_有订单列表*/
//	public static final String PALLET_QUEREY_AGENCY = "/pallet/palletQueryListAgency.htm";
	/**租船信息_详情*/
	public static final String PALLET_SELECT_UUID = "/pallet/palleDetail.json";
	
	public static final String PALLET_SELECT_BY_UUID = "/pallet/palleDetailByUuid.json";
	/**租船信息_新增*/
	public static final String PALLET_SAVE = "/pallet/save.json";
	/**租船信息_取消*/
	public static final String PALLET_DELETE = "/pallet/delete.json";
	/**租船信息_修改*/
	public static final String PALLET_UPDATE = "/pallet/update.json";
	/**承运商*/
	public static final String TRADER_NAME_LIST = "/pallet/traderNameList.htm";
	/**货主查询代理协议列表*/
	public static final String FIND_AHREEMENT_LIST = "/pallet/agreementList.htm";
	
	/**买家租船信息列表*/
	public static final String PALLET_BUYERS_LIST = "/pallet/buyPalletList.htm";
	/**卖家租船信息列表*/
	public static final String PALLET_SELLER_LIST = "/pallet/sellPalletList.htm";
	/**订单列表*/
	public static final String PALLET_ORDER_PAGE_LIST = "/pallet/orderPageList.htm";
	/**租船页面*/
	public static final String PALLET_EDIT = "/pallet/palletEdit.htm";
	
	/**有订单租船列表数据*/
	public static final String PALLET_QUEREY_APP = "/pallet/palletQueryListAp.json";
	/**无订单租船列表数据*/
	public static final String PALLET_SELECT_APP = "/pallet/palletListAp.json";
	/**货主协议列表数据*/
	public static final String PALLET_AGREEMENT_APP = "/pallet/agreementListAp.json";
	/**货盘滚动列表*/
	public static final String FIND_PALLET_LIST = "/pallet/findPalletList.json";
	/**货盘滚动查看更多列表*/
	public static final String FIND_MORE_PALLET_LIST = "/pallet/findMorePalletList.json";
	/**货盘查看详情*/
	public static final String FIND_PALLET_DETAIL = "/pallet/findPalletDetail.json";
	
//	/**船合同_列表*/
//	public static final String SHIPPACT_SELECT = "/shipPact/select.json";
//	/**船合同_详情*/
//	public static final String SHIPPACT_DEATIL = "/shipPact/deatil.json";
//	/**船合同_修改*/
//	public static final String SHIPPACT_UPDATE = "/shipPact/update.json";
	
	
	/******************************浩start**************************************/
	
	/**单位换算信息维护_查询列表*/
	public static final String UNIT_FINDALL = "/om/unit/findAll.json";
	/**单位换算信息维护_查询对象*/
	public static final String UNIT_FINDUNIT = "/om/unit/findUnit.json";
	/**单位换算信息维护_新增*/
	public static final String UNIT_SAVEUNIT = "/om/unit/saveUnit.json";
	/**单位换算信息维护_删除*/
	public static final String UNIT_DELETEUNIT = "/om/unit/deleteUnit.json";
	/**单位换算信息维护_修改*/
	public static final String UNIT_UPDATEUNIT = "/om/unit/updateUnit.json";
	/**单位换算信息维护_翻页列表*/
	public static final String UNIT_GET_UNIT_PAGE_LIST = "/om/unit/unitList.htm";
	
	/**单位换算比率_查询列表*/
	public static final String UNITRATE_FINDALL = "/om/unit/findRateAll.json";
	/**单位换算比率信息维护_新增*/
	public static final String UNITRATE_SAVEUNITRATE = "/om/unit/saveUnitRate.json";
	/**单位换算比率信息维护_查询对象*/
	public static final String UNITRATE_FINDUNITRATE = "/om/unit/findUnitRate.json";
	/**单位换算比率信息维护_删除*/
	public static final String UNITRATE_DELETEUNITRATE = "/om/unit/deleteUnitRate.json";
	/**单位换算比率信息维护_修改*/
	public static final String UNITRATE_UPDATEUNITRATE = "/om/unit/updateUnitRate.json";
	/**单位换算比率信息维护_翻页列表*/
	public static final String UNIT_GET_UNITRATE_PAGE_LIST = "/om/unit/unitRateList.htm";
	/**通过单位名获取比率信息*/
	public static final String GET_UNITRATE_BY_RECORD = "/om/unit/getRateInfo.json";
	
	/**基础运价信息维护_翻页列表*/
	public static final String BASICTARIFF_GET_PAGE_LIST = "/om/basicTariff/basicTariffList.htm";
	/**基础运价_翻页列表*/
	public static final String BASICTARIFF_FINDALL = "/om/basicTariff/findBasicTariffList.json";
	/**基础运价信息维护_新增*/
	public static final String BASICTARIFF_SAVEBT = "/om/basicTariff/saveBasicTariff.json";
	/**基础运价信息维护_逻辑删除*/
	public static final String BASICTARIFF_DELETEBT = "/om/basicTariff/deleteBasicTariff.json";
	/**基础运价信息维护_修改*/
	public static final String BASICTARIFF_UPDATEBT = "/om/basicTariff/updateBasicTariff.json";	
	/**基础运价信息维护_查找对象*/
	public static final String BASICTARIFF_FINDBT = "/om/basicTariff/getBasicTariff.json";
	/**基础运价前台调用接口*/
	public static final String BASICTARIFF_FINDBT_BY_PORT = "/om/basicTariff/findBasicTariffByPort.json";
	/**基础运价查询现有所有港口*/
	public static final String FINDALL_PORT_LIST = "/om/basicTariff/findAllPortList.json";
	
	/**运费小工具*/
	public static final String BASICTARIFF_TOOLS = "/om/basicTariff/freightTools.json";
	
	/**成交点数信息维护_翻页列表*/
	public static final String DEALPOINTS_GET_PAGE_LIST = "/om/dealPoints/dealPointsList.htm";
	/**成交点数_翻页列表*/
	public static final String DEALPOINTS_FINDALL = "/om/dealPoints/findDealPointsList.json";
	/**成交点数信息维护_新增*/
	public static final String DEALPOINTS_SAVEDP = "/om/dealPoints/saveDealPointsIn.json";
	/**咨询接口*/
	public static final String DEALPOINTS_SAVEDPZX = "/om/dealPoints/saveDealPoints.json";
	/**成交点数信息维护_逻辑删除*/
	public static final String DEALPOINTS_DELETEDP = "/om/dealPoints/deleteDealPoints.json";
	/**成交点数信息维护_修改*/
	public static final String DEALPOINTS_UPDATEDP = "/om/dealPoints/updateDealPoints.json";	
	/**成交点数信息维护_查找对象*/
	public static final String DEALPOINTS_FINDDP = "/om/dealPoints/getDealPoints.json";
	
	/**成交点数所有参考时间*/
	public static final String DEALPOINTS_FINDDATE = "/om/dealPoints/getDealPointsDates.json";
	
	/**查询最新成交点数，时间倒序返回*/
	public static final String DEALPOINTS_GET_TODAY = "/om/dealPoints/getTodayDealPoints.json";
	/**根据地区获取线性图所需数据*/
	public static final String DEALPOINTS_GET_LINEDATAS = "/om/dealPoints/getLineDatas.json";
	/**获取最新成交点数（交易模块使用）*/
	public static final String QUERY_DEALPOINTS_TODAY = "/om/dealPoints/queryTodayDealPointsList.json";
	
	
	
	/**结算单管理代理协议列表（代理）*/
	public static final String AGREEMENT_STATEMENT_LIST = "/statements/agreementsStatementsList.htm";
	/**结算单管理代理协议列表（货主）*/
	public static final String SHIPPER_AGREEMENT_STATEMENT_LIST = "/shipperstatements/shipperagreementsStatementsList.htm";
	/**结算单录入*/
	public static final String SAVE_STATEMENTS = "/statements/saveStatements.json";
	/**结算单逻辑删除*/
	public static final String DELETE_STATEMENTS = "/statements/deleteStatements.json";
	/**结算单修改*/
	public static final String UPDATE_STATEMENTS = "/statements/updateStatements.json";
	/**结算单根据协议uuid查数据*/
	public static final String FINDBYUUID_STATEMENTS = "/statements/findStatementsByUuid.json";
	/**结算单根据uuid查数据*/
	public static final String FINDBYUUID_STATEMENTSS = "/statements/findStatementsByUuids.json";
	/**结算单根据uuid查数据*/
	public static final String SHIPPER_FINDBYUUID_STATEMENTS = "/shipperstatements/shipperfindStatementsByUuid.json";
	
	
	/**结算单管理租船合同列表*/
	public static final String PACT_STATEMENT_LIST = "/statements/pactList.htm";
	/**结算单管理租船合同列表（货主）*/
	public static final String SHIPPER_PACT_STATEMENT_LIST = "/shipperstatements/shipperpactList.htm";
	/**结算单租船合同根据uuid查数据（代理）*/
	public static final String PACT_STATEMENT_FINDBYUUID = "/statements/findPactStatementsByUuid.json";
	/**结算单租船合同根据uuid查数据（货主）*/
	public static final String SHIPPER_PACT_STATEMENT_FINDBYUUID = "/shipperstatements/shipperfindPactStatementsByUuid.json";
	/** 跳转无代理查看结算数据界面 */
	public static final String TO_SHIPPER_PACT_STATEMENT_FINDBYUUID = "/shipperstatements/toshipperfindPactStatementsByUuid.json";
	/**结算单管理对账列表*/
	public static final String AGREEMENT_PACT_STATEMENT_LIST = "/statements/agreementPactList.htm";
	/**结算单管理列表（OM）*/
	public static final String AGREEMENT_STATEMENT_OM = "/om/platform/agreementStatementList.htm";
	/**结算单详情（OM）*/
	public static final String AGREEMENT_STATEMENT_DETAILS = "/statements/findStatementsDetails.json";
	
	/******************************浩end**************************************/
	/**移动端查询物流跟踪*/
	public static final String MOBILEQUERY_TRACK = "/shipPact/mobileQueryTrack.json";
	/**根据船名模糊查询船合同信息或代理协议信息*/
	public static final String QUERY_TRACK_UUID  = "/shipPact/queryUuid.json";
	/**移动端租船需求信息__无订单翻页列表(代理)*/
	public static final String MOBILEQUERY_PALLETLIST_AGENCY1 = "/pallet/palletListAgency1.json";
	/**移动端租船需求信息__有订单翻页列表(代理)*/
	public static final String MOBILEQUERY_PALLETLIST_AGENCY2 = "/pallet/palletListAgency2.json";
	/**移动端代理协议信息__列表(代理)*/
	public static final String MOBILEQUERY_AGREEMENTLIST_AGENCY = "/agreement/agreementListAgency.json";
	/**移动端船合同__列表(代理)*/
	public static final String MOBILEQUERY_SHIPPACT_LIST = "/shipPact/queryShipPactListAgency.json";
	/**移动端船盘__列表(代理)*/
	public static final String MOBILEQUERY_SHIPPLATE_LIST = "/shipPlate/queryshipPlateListAgency.json";
	
	
	
	/********************************ZhengC start************************************/
	/**询盘信息维护_详细*/
	public static final String FIND_INTENTION_DETAIL = "/intention/findIntentionDetail.json";
	/**询盘信息维护_新增询盘信息*/
	public static final String SAVE_INTENTION = "/intention/saveIntention.json";
	/**询盘信息维护_重复询盘校验*/
	public static final String CHECK_AGAIN_INTENTION = "/intention/checkAgainIntention.json";
	/**询盘信息维护_修改询盘状态*/
	public static final String UPDATE_INTENTION_STATUS = "/intention/updateIntentionStatus.json";
	/**询盘信息维护_查询询盘信息翻页列表（船东/船东经纪人）*/
	public static final String SHIPOWNER_INTENTION_LIST = "/intention/shipOwnerIntentionList.htm";
	/**询盘信息维护_查询询盘信息翻页列表（货主）*/
	public static final String CARGOOWNER_INTENTION_LIST = "/intention/cargoOwnerIntentionList.htm";
	/**询盘信息维护_查询询盘信息翻页列表（代理）*/
	public static final String SHIPAGENT_INTENTION_LIST = "/intention/shipAgentIntentionList.htm";
	/**询盘信息维护_询盘列表（查看向我询盘信息列表）*/
	public static final String FIND_INTENTION_LIST = "/intention/myIntentionList.htm";
	/**询盘信息维护_询盘列表（货主，租船需求管理-询盘信息）*/
	public static final String FIND_INTENTION_LISTS = "/intention/myIntentionLists.htm";
	/**询盘信息维护_租船确认单*/
	public static final String FIND_CHARTER_SHIP_CONFIRM = "/intention/findCharterShipConfirm.json";
	/**询盘信息维护_修改询盘信息*/
	public static final String UPDATE_INTENTION = "/intention/updateIntention.json";
	/**询盘信息维护_修改询盘状态（确认成交）*/
	public static final String UPDATE_CONFIRM_TRANSACTION = "/intention/updateConfirmTransaction.json";
	/**询盘信息维护_询盘列表（二船东，船盘管理-洽谈列表）*/
	public static final String TALK_INTENTION_LIST = "/intention/talkingList.htm";
	
	/**船盘信息维护_翻页列表（代理）*/
	public static final String GET_AGENT_SHIPPLATE_PAGE_LIST = "/shipPlate/shipAgentShipPlateList.htm";
	/**船舶信息维护_翻页列表（船东/二船东）*/
	public static final String SHIPOWNER_SYSSHIP_PAGE_LIST = "/shipOwnerSysShip/sysShipList.htm";
	/**船盘信息维护_发送报盘_船盘列表（二船东）*/
	public static final String SEND_CLAUSE_SHIPPLATE_LIST = "/shipPlate/sendClauseShipPlateList.htm";
	/**查询承运商列表：4船东,5船经纪人,7转租船东*/
	public static final String QUERY_TRANDERNAME = "/queryTranderName.json";
	/**船盘信息维护_翻页列表（平台）*/
	public static final String GET_OM_SHIPPLATE_PAGE_LIST = "/om/platform/shipPlateList.htm";
	/**船盘信息维护_详细（om平台）*/
	public static final String FIND_SHIP_PLATE_DETAIL_OM = "/shipPlate/findShipPlateDetailOM.json";
	/**船盘信息维护_批量删除（om平台）*/
	public static final String BATCH_DEL_SHIPPLATE_OM = "/shipPlate/batchDelShipPlateOM.json";
	/**船盘信息维护_删除（om平台）*/
	public static final String DEL_SHIP_PLATE_OM = "/shipPlate/delShipPlateOM.json";
	
	/********************************ZhengC end************************************/
	
	
	
	
	/******************************************************niuwk start**************************************************************/
	/**查询船合同列表（船东/经纪人）htm*/
	public static final String SHIP_OWNER_SHIPPACT_LIST_HTM = "/shipPact/shipOwnerShipPactList.htm";
	
	/**查询船合同列表（代理）htm*/
	public static final String SHIP_AGENT_SHIPPACT_LIST_HTM = "/shipPact/shipAgentShipPactList.htm";
	
	/**查询船合同列表（货主）htm*/
	public static final String CARGO_OWNER_SHIPPACT_LIST_HTM = "/shipPact/cargoOwnerShipPactList.htm";
	
	/**查询船合同详情（代理）*/
	public static final String FIND_SHIPPACT_DEATIL_INTENTION_OR_PLATE = "/shipPact/findShipPactDetailIntentionOrPlate.json";
	
	/**查询船合同列表htm*/
	public static final String SHIPPACT_LIST_HTM_NEW = "/shipPactNew/shipPactListNew.htm";
	/**查询平台船合同列表htm*/
	public static final String QUERY_SHIPPACTP_LATFORM = "/om/platform/shipPactList.htm";
	/**查询船合同列表htm*/
	public static final String SHIPPACT_LIST_HTM_NEW_OWNER = "/shipPactNew/shipPactListNewOwner.htm";
	/**查询船合同列表船代*/
	public static final String SHIPPACT_LIST_HTM_AGENCY = "/shipPactNew/shipPactListAgency.htm";
	/**合同协议匹配完成*/
	public static final String SHIPPACT_AGREENMENT_MAPPING_OVER = "/shipPact/mappingOver.json";
	
	public static final String FIND_SYS_SHIP_BY_KEYWORD = "/sysShip/findSysShipByKeyword.json";
	
	/******************************************************niuwk  end**************************************************************/
	
	
	/******************************************************hetao start**************************************************************/
	/**新增报盘*/
	public static final String SAVE_CLAUSE = "/clause/saveClause.json";
	/**报盘详情*/
	public static final String CLAUSE_DETAIL = "/clause/clauseDetails.json";
	/**报盘详情*/
	public static final String FIND_CLAUSE_DETAIL = "/clause/findClauseDetails.json";
	/**新增报盘页面数据*/
	public static final String TO_CLAUSE_ADD = "/clause/toAddClause.json";
	/**报盘确认*/
	public static final String AFFIRM_CLAUSE = "/clause/affirmClause.json";
	/**修改报盘状态*/
	public static final String UPDATE_CLAUSE_STATUS = "/clause/updateClauseStatus.json";
	
	/**修改报盘信息*//*
	public static final String UPDATE_CLAUSE = "";*/
	/**洽谈报盘列表*/
	public static final String TALKING_CLAUSE_= "/clause/talkingClause.htm";
	 /**查询报盘信息翻页列表（代理）*//*
	public static final String QUERY_CLAUSE_PAGE_LIST = "/clause/clauseQueryList.htm";*/
	
	
	
	/**租船需求_无订单列表_货主*/
	public static final String PALLET_SELECT = "/pallet/palletList.htm";
	/**租船需求_有订单列表_货主*/
	public static final String PALLET_QUEREY = "/pallet/palletQueryList.htm";
	/**租船需求_无订单列表_二船东*/
	public static final String PALLET_SELECT_OWNER = "/pallet/palletListShipowner.htm";
	/**租船需求_有订单列表_二船东*/
	public static final String PALLET_QUEREY_OWNER = "/pallet/palletQueryListShipowner.htm";
	/**租船信息_撤销*/
	public static final String PALLET_UPDATE_STATUS = "/pallet/updateStatus.json";
	/**指定二船东*/
	public static final String PALLET_APPOINT = "/pallet/appointTradertrader.json";
	/**船盘推荐*/
	public static final String RECOMMEND_SHIPPLATE= "/shipPlateNew/recommendShipPlate.htm";
	/**更多船盘*/
	public static final String MORESHIPPLATE = "/shipPlate/moreShipPlate.htm";
	/**租船确认详情*/
	public static final String CONFIRMSDETAILS = "/pallet/confirmsDetail.json";
	/**om租船需求列表*/
	public static final String PALLETLIST_OM = "/om/platform/palletList.htm";
	private URLMapping() throws Exception{
		throw new Exception("Illegal Access");
	}
}
