package com.sinochem.crude.trade.listed.constant;

/**
 * listed模块所有controller层UrlMapping
 * made by WangTing
 */
public interface UrlMapping {

    /****************************** 供移动端使用的控制层 **************************************/
    String  APP_CRUDEOILCONDITION = "/app/crudeOilCondition.json";                                 /****原油筛选数据****/
    String  APP_QUERYCRUDEOILLIST = "/app/queryCrudeOilList.json";                                 /****分页查询原油****/
    String  APP_QUERYSHIPBERTHLIST = "/app/queryShipBerthList.json";                               /****根据ID获取泊位信息****/
    String  APP_QUERYHOTSPOTLIST = "/app/queryHotspotList.json";                                   /****获取热点信息****/
    String  APP_UPDATEDEMANDSTATUS = "/app/updateDemandStatus.json";                               /****更新状态****/
    String  APP_GETDICLISTMAPBYBIZNAME = "/app/getDicListMapByBizName.json";                       /****获取字典信息****/
    String  APP_DEMANDSCOMPARE = "/app/demandsCompare.json";                                       /****获取字典信息****/

    /****************************** 原油APP接口维护控制层 **************************************/
    String  APP_BUYINGLEADSDEMANDLISTAPP = "/app/buyingLeadsDemandListApp.json";                   /****采购需求列表页面****/
    String  APP_BIDDINGLISTAPP = "/app/biddingListApp.json";                                       /****报价列表页面****/
    String  APP_BIDDINGDEMANDLISTAPP = "/app/biddingDemandListApp.json";                           /****需求的报价列表****/
    String  APP_SAVEDEMAND = "/app/saveDemand.json";                                               /****保存需求单  保存或发布（status:1保存 2发布）****/
    String  APP_QUOTETYPE = "/app/quoteType.json";                                                 /****保存（投标、报价、正式报价）****/
    String  APP_QUERYCRUDEOILINFOS = "app/queryCrudeOilInfos.json";                                /****查询原油产品信息****/
    String  APP_ACCEPTBIDDING = "/app/acceptBidding.json";                                         /****确认中标****/

    /****************************** 买家中心contain的控制层 **************************************/
    String  BUYCENTERCONTAIN_BIDDINGDEMANDLIST = "/buyCenterContain/biddingDemandList.htm";        /****需求的报价列表****/
    String  BUYCENTERCONTAIN_DEMANDSTATUS = "/buyCenterContain/demandStatus";                      /****状态查询****/
    String  BUYCENTERCONTAIN_TOPFIVEBIDDINGLIST = "/buyCenterContain/topfivebiddinglist";          /****获取5条报价数据列表****/
    String  BUYCENTERCONTAIN_TOPFIVEDEMANDLIST = "/buyCenterContain/topfivedemandlist";            /****获取5条需求数据列表****/
    String  BUYCENTERCONTAIN_QUERYCOUNT = "/buyCenterContain/queryCount";                          /****查询当前人相关的需求单数量****/

    /****************************** 原油大厅控制层 **************************************/
    String  CRUDEOILLOBBY_CRUDEOILSUPMLIST = "/crudeoillobby/crudeoilsupmlist.htm";                /****原油大厅****/
    String  CRUDEOILLOBBY_PURCHASINGDEMAND = "/crudeoillobby/purchasingDemand.htm";                /****原油买家****/
    String  CRUDEOILLOBBY_PURCHASINGDEMANDDETAIL = "/crudeoillobby/purchasingDemandDetail.htm";    /****采购需求详情****/
    String  CRUDEOILLOBBY_CRUDEOILBIDDING = "/crudeoillobby/crudeOilBidding.htm";                  /****供应商报价下单页demand信息回显****/
    String  CRUDEOILLOBBY_SAVEDEMAND = "/crudeoillobby/saveDemand.htm";                            /**保存需求单  保存或发布（status:1保存 2发布）*/
    String  CRUDEOILLOBBY_QUOTETYPE = "/crudeoillobby/quoteType.htm";                              /****立即报价****/
    String  CRUDEOILLOBBY_INDEX = "/crudeoillobby/index.htm";                                      /****原油大厅 发布需求分页查询****/
    String  CRUDEOILLOBBY_PURCHASINGDEMANDEDIT = "/crudeoillobby/purchasingDemandEdit.htm";        /****保存后数据回显****/
    String  CRUDEOILLOBBY_QUERYCRUDEOILINFOS = "/crudeoillobby/queryCrudeOilInfos.json";           /****查询原油产品信息****/
    String  CRUDEOILLOBBY_ELECT = "/crudeoillobby/elect";                                          /****原油大厅查询所有发布信息总条数****/
    String  CRUDEOILLOBBY_CONTRAST = "/crudeoillobby/contrast";                                    /****对比****/
    String  CRUDEOILLOBBY_SELLINGDEMAND = "/crudeoillobby/sellingDemand.htm";                      /****销售资源维护_原油大厅的发布销售****/
    String  CRUDEOILLOBBY_INDEXSELL = "/crudeoillobby/indexSell";                                  /****销售资源维护_原油大厅的发布销售分页查询**/
    String  CRUDEOILLOBBY_SELLINGDEMANDDETAIL = "/crudeoillobby/sellingDemandDetail.htm";          /****销售资源维护_原油大厅的销售资源详情**/
    String  CRUDEOILLOBBY_INSTRUMENTOFEXCHANGE = "/crudeoillobby/instrumentOfExchange.htm";        /****交易工具**/

    /****************************** 原油大厅详情控制层 **************************************/
    String  CRUDEOILHALLFORM_PRICEDETAIL = "/crudeoilhallform/pricedetail.htm";                    /****采购信息回显**/
    String  CRUDEOILHALLFORM_PRICEDETAIL_COMPARE = "/crudeoilhallform/pricedetail_compare.htm";    /****需求、采购信息对比回显**/
    String  CRUDEOILHALLFORM_BERTHLIST = "/crudeoilhallform/berthlist";                            /****查询泊位信息**/
    String  CRUDEOILHALLFORM_BERTHLISTT = "/crudeoilhallform/berthlistT";                          /****查询运输、泊位信息**/
    String  CRUDEOILHALLFORM_QUERYDEMANDBYUUID = "/crudeoilhallform/queryDemandByUuid";            /****查询demand通过uuid**/
    String  CRUDEOILHALLFORM_PRICELIST = "/crudeoilhallform/pricelist";                            /****查询denand列表**/
    String  CRUDEOILHALLFORM_BUYERLIST = "/crudeoilhallform/buyerlist";                            /****查询demand相关项**/
    String  CRUDEOILHALLFORM_SUPPLIERLIST = "/crudeoilhallform/supplierList";                      /****查询demand相关项**/
    String  CRUDEOILHALLFORM_OILTYPELIST = "/crudeoilhallform/oiltypeList";                        /****查询原油列表**/
    String  CRUDEOILHALLFORM_CRUDEOILLIST = "/crudeoilhallform/crudeoillist";                      /****查询原油列表**/
    String  CRUDEOILHALLFORM_CRUDEOILBIDDINGLIST = "/crudeoilhallform/crudeoilbiddinglist";        /****原油大厅的需求招标**/
    String  CRUDEOILHALLFORM_CRUDEOILREQUIRELIST = "/crudeoilhallform/crudeoilrequirelist";        /****原油大厅的需求询价**/
    String  CRUDEOILHALLFORM_CRUDEOILHOTDEMANDLIST = "/crudeoilhallform/crudeoilhotdemandlist";    /****原油大厅的热点需求**/
    String  CRUDEOILHALLFORM_RICHTEXT = "/crudeoilhallform/richtext.htm";                          /****查询demand详情**/
    String  CRUDEOILHALLFORM_SKETCHLIST = "/crudeoilhallform/sketchlist";                          /****通过demandid获取demand**/
    String  CRUDEOILHALLFORM_PRICELISTTWO = "/crudeoilhallform/pricelistTwo.htm";                  /****查询demand船务**/
    String  CRUDEOILHALLFORM_BERTHLISTNEW = "/crudeoilhallform/berthlistnew";                      /****查询泊位信息**/
    String  CRUDEOILHALLFORM_SUPPLIERDETAIL = "/crudeoilhallform/supplierDetail";                  /****查询供应商详情**/
    String  CRUDEOILHALLFORM_SALEBUYERDETAIL = "/crudeoilhallform/saleBuyerDetail";                /****查询买家详情**/
    String  CRUDEOILHALLFORM_BUYERDETATIL = "/crudeoilhallform/buyerdetatil";                      /****查询买家企业信息**/
    String  CRUDEOILHALLFORM_SPECIFYENTERPRISESHOW = "/crudeoilhallform/specifyEnterpriseShow";    /****详情页面获取需求的定向企业信息**/
    String  CRUDEOILHALLFORM_SPECIFYENTERPRISE = "/crudeoilhallform/specifyEnterprise";            /****定向企业信息列表**/
    String  CRUDEOILHALLFORM_SELLSKETCHLIST = "/crudeoilhallform/sellSketchlist";            /****销售资源维护_原油大厅_查看销售资源（概要信息）**/
    String  CRUDEOILHALLFORM_SELLERLIST = "/crudeoilhallform/sellerlist";                    /****销售资源维护_原油大厅_查看销售资源（卖家信息）**/
    String  CRUDEOILHALLFORM_SELLPRICELIST = "/crudeoilhallform/sellpricelist";                    /****销售资源维护_新增销售资源（销售信息）**/
    String  CRUDEOILHALLFORM_SELLERDETAIL = "/crudeoilhallform/sellerdetail";                      /****销售资源维护_新增销售资源（卖家信息）**/
    String  CRUDEOILHALLFORM_SELLPRICEDETAIL = "/crudeoilhallform/sellpricedetail.htm";            /****销售资源维护_查看销售资源（销售信息）**/
    String  CRUDEOILHALLFORM_CRUDESELLBIDDINGLIST = "/crudeoilhallform/crudesellbiddinglist";      /****销售资源维护_原油大厅的需求招标**/
    String  CRUDEOILHALLFORM_CRUDESELLREQUIRELIST = "/crudeoilhallform/crudesellrequirelist";      /****销售资源维护_原油大厅的需求询价**/

    /****************************** 用于转价功能的控制层 **************************************/
    String  OILPRICECONVERTION_GETOILMODELTYPELIST = "/oilPriceConvertion/getOilModelTypeList.json";/****油种类型列表**/
    String  OILPRICECONVERTION_GETSYMBOLDATELIST = "/oilPriceConvertion/getSymbolDateList.json";    /****获取资讯合约时间列表**/
    String  OILPRICECONVERTION_GETPRICEDIFFERENCE = "/oilPriceConvertion/getPriceDifference.json";  /****获取差价**/
    String  OILPRICECONVERTION_GETDUBAISYMBOLPRICE = "/oilPriceConvertion/getDubaiSymbolPrice"; /****获取目标年月DUBAI价格**/

    /****************************** 报价控制层 **************************************/
    String  OILSALEBIDDING_BIDDINGLIST = "/oilSaleBidding/biddingList";                             /****我发布的报价列表页面**/
    String  OILSALEBIDDING_BIDDINGDETAIL = "/oilSaleBidding/biddingDetail";                         /****报价详情页面**/
    String  OILSALEBIDDING_PRICEDETAIL_COMPARE = "/oilSaleBidding/pricedetail_compare.htm";         /****需求、采购信息对比回显**/
    String  OILSALEBIDDING_DEMANDBIDDINGDETAIL = "/oilSaleBidding/demandBiddingDetail";             /****需求对应的报价列表页面**/
    String  OILSALEBIDDING_DEMANDBIDDINGLIST = "/oilSaleBidding/demandBiddingList";                 /****需求的报价列表**/
    String  OILSALEBIDDING_CRUDEOILBIDDING = "/oilSaleBidding/crudeOilBidding.htm";                 /****供应商报价下单页demand信息回显**/
    String  OILSALEBIDDING_PRICELISTTWO = "/oilSaleBidding/pricelistTwo.htm";                       /****查询demand船信息**/
    String  OILSALEBIDDING_QUOTETYPE = "/oilSaleBidding/quoteType";                                 /****立即报价**/

    /****************************** 销售资源维护控制层 **************************************/
    String  OILSALERESOURCES_RESOURCESLIST = "/oilSaleResources/resourcesList";                     /****销售资源维护_销售资源列表页面**/
    String  OILSALERESOURCES_BATCHADDED = "/oilSaleResources/batchAdded.json";                      /****销售资源维护_批量上架**/
    String  OILSALERESOURCES_BATCHUNDERCHARGED = "/oilSaleResources/batchUnderCharged.json";        /****销售资源维护_批量下架**/
    String  OILSALERESOURCES_BATCHDELETE = "/oilSaleResources/batchDelete";                         /****销售资源维护_待删除资源id串**/
    String  OILSALERESOURCES_NEWSALELEADS = "/oilSaleResources/newSaleLeads";                       /****销售资源维护_增加销售需求**/
    String  OILSALERESOURCES_OILSALERESOURCESDETAIL = "/oilSaleResources/oilSaleResourcesDetail";   /****销售资源维护_销售详情页面**/
    String  OILSALERESOURCES_POSTPONE = "/oilSaleResources/postpone.json";                          /****销售资源维护_延期**/
    String  OILSALERESOURCES_SAVEDEMAND = "/oilSaleResources/saveDemand.htm";  /****销售资源维护_保存销售需求单  保存或发布（status:1保存 2发布）**/

    /****************************** om控制层 **************************************/
    String  OM_WELCOME = "/om/welcome";                                                             /****om欢迎首页**/

    /****************************** 通用的分页组件 **************************************/
    String  COMMON_PAGE = "/common/page";                                                           /****通用的分页组件**/

    /****************************** 计价公式Controller **************************************/
    String  PRICINGFORMULA_PHYSICALPRICINGEDITOR = "/pricingFormula/physicalPricingEditor";         /****计价编辑器初始化**/
    String  PRICINGFORMULA_GETPRICINGINFO = "/pricingFormula/getPricingInfo.json";                  /****取计价公式信息**/

    /****************************** 采购管理控制层 **************************************/
    String  PURCHASEMANAGER_BIDDINGLIST = "/purchaseManager/biddingList";                           /****我发布的报价列表页面**/
    String  PURCHASEMANAGER_DEMANDBIDDINGLIST = "/purchaseManager/demandBiddingList";               /****需求对应的报价列表页面**/
    String  PURCHASEMANAGER_BUYINGLEADSDEMANDLIST = "/purchaseManager/buyingLeadsDemandList";       /****采购需求列表页面**/
    String  PURCHASEMANAGER_BIDDINGDETAIL = "/purchaseManager/biddingDetail";                       /****报价详情页面**/
    String  PURCHASEMANAGER_BUYBIDDINGEDIT = "/purchaseManager/buyBiddingEdit";                     /****报价修改页面**/
    String  PURCHASEMANAGER_BUYBIDDINGSAVE = "/purchaseManager/buyBiddingSave.htm";                 /****保存报价信息**/
    String  PURCHASEMANAGER_SELLBIDDINGEDIT = "/purchaseManager/sellBiddingEdit";                   /****报价修改页面**/
    String  PURCHASEMANAGER_SELLBIDDINGSAVE = "/purchaseManager/sellBiddingSave.htm";               /****保存报价信息**/
    String  PURCHASEMANAGER_NEWBUYINGLEADS = "/purchaseManager/newBuyingLeads";                     /****新增采购需求**/
    String  PURCHASEMANAGER_DEMANDDETAIL = "/purchaseManager/demandDetail";                         /****采购需求详情页面**/
    String  PURCHASEMANAGER_BUY_CONFIRMBIDDINGANDREMIND = "/purchaseManager/buy/confirmBiddingAndRemind";  /****买家确认中标**/
    String  PURCHASEMANAGER_SALES_CONFIRMBIDDINGANDREMIND = "/purchaseManager/sales/confirmBiddingAndRemind";  /****卖家家确认中标**/
    String  PURCHASEMANAGER_BIDDINGCONTRAST = "/purchaseManager/biddingContrast";                   /****报价单对比**/
    String  PURCHASEMANAGER_BATCHADDED = "/purchaseManager/batchAdded.json";                        /****批量上架**/
    String  PURCHASEMANAGER_BATCHUNDERCHARGED = "/purchaseManager/batchUnderCharged.json";          /****批量下架**/
    String  PURCHASEMANAGER_BATCHDELETE = "/purchaseManager/batchDelete";                           /****待删除资源id串**/
    String  PURCHASEMANAGER_DEMANDCOMPARE = "/purchaseManager/demandCompare";                       /****商品比较**/
    String  PURCHASEMANAGER_POSTPONE = "/purchaseManager/postpone.json";                            /****延期**/

    /****************************** 贸易链控制层 **************************************/
    String  TRADECHAIN_QUERYTRADECHAINLIST = "/tradeChain/tradeChainList";                           /****贸易链列表**/
    String  TRADECHAIN_INSERTTRADECHAIN = "/tradeChain/insertTradeChain";   					 /****创建贸易链**/
    String  TRADECHAIN_UPDATETRADECHAIN = "/tradeChain/updateTradeChain";   					 /****修改贸易链草约订单**/
    String  TRADECHAIN_TOTRADECHAININSERT = "/tradeChain/toTradeChainInsert";   				 /****跳转到创建贸易链界面**/
    String  TRADECHAIN_SUBMITORWITHDRAWTRADINGCHAIN = "/tradeChain/submitOrWithdrawTradingChain";    /****贸易链订单提交/撤回**/
    String  TRADECHAIN_CONFIRMTRADECHAINORDER = "/tradeChain/confirmTradeChain";   			         /****贸易链订单确认**/
    String  TRADECHAIN_REJECTTRADINGCHAIN = "/tradeChain/rejectTradingChain";   			         /****贸易链订单驳回**/
    String  TRADECHAIN_GETTRADINGCHAINDETAILS = "/tradeChain/getTradingChainDetails";   			 /****贸易链详情**/
    String  TRADECHAIN_TOTRADECHAINEDIT = "/tradeChain/toTradeChainEdit";                        /****跳转到修改贸易链界面**/
    String  QUERY_TRADERS = "/queryTraders";   /**** 获取所有贸易商 ****/
    String  TRADECHAIN_TRADETRAINALREADYEXISTS = "/tradeChainAlreadyExists";  /**** 判断是否已发起贸易链 ****/
}
