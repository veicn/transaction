package com.sinochem.crude.trade.customs.service.impl;

import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import java.lang.reflect.Field;

import com.sinochem.crude.trade.blockchain.dao.*;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.service.BlockChainService;
import com.sinochem.crude.trade.blockchain.service.TBlockchainEventRecordService;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.customs.model.query.BlockchainInfoQuery;
import com.sinochem.crude.trade.customs.model.query.HomePageQuery;
import com.sinochem.crude.trade.customs.service.CustomsService;
import com.sinochem.crude.trade.inspector.enums.AppointEnum;
import com.sinochem.crude.trade.inspector.enums.InspectorStateEnum;
import com.sinochem.crude.trade.inspector.model.query.ReportInfoQuery;
import com.sinochem.crude.trade.inspector.model.query.TaskInfoQuery;
import com.sinochem.crude.trade.inspector.service.ReportInfoService;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.shipagent.service.BillLoadingService;
import com.sinochem.crude.trade.shipagent.service.SofService;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.enums.*;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetVO;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.web.controller.MyContractDetailController;
import com.sinochem.it.b2b.common.exception.BizException;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomsServiceImpl implements CustomsService {
    @Autowired
    private TInspectorPresentationMapper tInspectorPresentationMapper;
    @Autowired
    private TBlockchainEventRecordMapper tBlockchainEventRecordMapper;
    @Autowired
    private ReportInfoService reportInfoService;
    @Autowired
    private BillLoadingService billLoadingService;
    @Autowired
    private SofService sofService;
    @Autowired
    ContractSheetService contractSheetService;

    @Autowired
    private URLBroker docServer;

    @Autowired
    private BlockChainService blockChainService;

    @Autowired
    private TBrokerDeclarationMapper tBrokerDeclarationMapper;

    @Autowired
    private TCommonAttachmentsMapper tCommonAttachmentsMapper;

    @Autowired
    private EnterpriseService enterpriseService;

    Gson gson = new Gson();

    /**
     * 概况
     * @param blockchainInfoQuery
     * @param pageInfo
     * @return
     */
    @Override
    public List<Map<String,Object>>   surveyGetInfo(BlockchainInfoQuery blockchainInfoQuery) {

        List<Map<String,Object>>  page = tBlockchainEventRecordMapper.queryBlockchainInfoList(blockchainInfoQuery.toMap());

        Map<String, Object> tmp = new HashMap<>();
        for(Map<String , Object> map : page){
            //报关状态还没给
//            if(InspectorStateEnum.toMap().containsKey(map.get("STATE"))){
//                map.put("STATE", InspectorStateEnum.toMap().get(map.get("STATE")));
//            }
            //logo显示
            if(map.get("event_code")!=null&&"4007".equals("" + map.get("event_code"))){
                //交易达成的时候要显示交易双方的logo
                tmp = gson.fromJson("" + map.get("post_data"), map.getClass());
                map.put("buyerLogo",getEpLogo("" + tmp.get("buyerId")));
                map.put("salerLogo",getEpLogo("" + tmp.get("salerId")));
            }else if(!"4007".equals("" + map.get("event_code"))
                    &&map.get("create_user")!=null&&!"".equals("" + map.get("create_user"))){
                //其他情况
                map.put("salerLogo",getEpLogo("" + map.get("create_user")));
            }

        }

        return  page;
    }

    private String getEpLogo(String id){
        EnterpriseVo enterpriseVo = new EnterpriseVo();

//        EnterpriseVo enterpriseVo = null;
        enterpriseVo =
                enterpriseService.getByMemberId(Long.valueOf(id));
        if (enterpriseVo!=null){
            return docServer.get("/img/"+enterpriseVo.getEpLogo() +".img_56_56.img").toString();
        }else{
            return "";
        }
    }
    /**
     *  海关首页
     * @param homePageQuery
     * @param pageInfo
     * @return
     */
    @Override
    public Page<Map<String, Object>> GetInfoList(HomePageQuery homePageQuery, SimplePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), "A.ID DESC" );

        Page<Map<String, Object>> page = (Page<Map<String, Object>>) tBlockchainEventRecordMapper.queryInfoList(homePageQuery.toMap());
        //查询交易信息
        MyContractDetailController  controller = new MyContractDetailController();

        Map<String,Object> contractSheet = new HashMap<>();
        for(Map<String , Object> map : page){
            //商检状态
            if(InspectorStateEnum.toMap().containsKey(map.get("STATE"))){
                map.put("STATE", InspectorStateEnum.toMap().get(map.get("STATE")));
            }

            contractSheet = tInspectorPresentationMapper.queryTransactionInfoInfoList("" + map.get("deal_uuid"));
            if(contractSheet!=null){
                //买家公司
                map.put("buyer_company", "" + contractSheet.get("buyername"));
                //卖家公司
                map.put("seller_company", "" + contractSheet.get("salername"));
                //油种
                map.put("oil_type",
                        getZhNameByCode(ProductCategoryEnum.toMap(),"" + contractSheet.get("product_category_code")));
                //数量
                map.put("num", contractSheet.get("quantity"));
                //装期 开始时间 结束时间
                map.put("laycan_start_date", contractSheet.get("laycan_start_date"));
                map.put("laycan_end_date", contractSheet.get("laycan_end_date"));
                //委托方
                map.put("委托方主表id",map.get("tssid"));
                map.put("enterprise_id",map.get("enterprise_id"));
                map.put("enterprise_name",contractSheet.get("enterprise_name"));
                //装货港
                map.put("load_port",
                        "" + contractSheet.get("loading_port"));
                map.put("loading_country",
                        "" + contractSheet.get("loading_country"));
                //卸货港
                map.put("discharge_port",
                       "" + contractSheet.get("discharge_port"));
                map.put("discharge_country",
                        "" + contractSheet.get("discharge_country"));
                //单位
                map.put("quantity_unit_code",
                        getZhNameByCode(PricingUnitEnum.toMap(),"" + contractSheet.get("quantity_unit_code")));
            }

        }
        return  page;
    }


    /**
     * 根据code取得name
     * @param map
     * @param key
     * @return
     */
    private String getZhNameByCode(Map<String,String> map,String key){
        if(map.containsKey(key)){
            return map.get(key);
        }else{
            return "";
        }
    }
    @Override
    public Map<String, Object> GetInfo(String deal_no,String event_code) throws Exception {
        //取得当前dealno对应的最新的上传区块链的信息
        //查询条件map
        Map<String,Object> tmpquery = new HashMap<>();
        tmpquery.put("deal_no",deal_no);
        String[] tmp = event_code.split(",");
        tmpquery.put("event_code1",tmp[0]);
        tmpquery.put("event_code2",tmp[1]);
        //查询结果
        Map<String,Object> resBusid = tBlockchainEventRecordMapper.queryInfo(tmpquery);
        String businessId = "";
        if(resBusid==null||resBusid.get("busi_id")==null){
            throw new Exception("上链事件记录的busi_id为空");
        }
        //uuid取得
        if("1003".equals(tmp[0])){
            //商检uuid
            TInspectorPresentation info =
                    reportInfoService.queryReportInfoById(Long.valueOf("" + resBusid.get("busi_id")));
            businessId = info.getUuid();
        }else if("2003".equals(tmp[0])){
            //报关
            TBrokerDeclaration info =
                    tBrokerDeclarationMapper.selectByPrimaryKey(Integer.valueOf("" + resBusid.get("busi_id")));
            businessId = info.getUuid();
        }

        //根据区块链id
        BlockChainEntity entity= blockChainService.findFromBlockChain(""+ resBusid.get("blockchain_id"));
        if (entity==null){
            resBusid = new HashMap<>();
            resBusid.put("state",999);
            resBusid.put("message","entity=null;区块链数据被删除了。");
            return resBusid;
        }
        //测试用
//        String s = "" + res.get("post_data");
        Map<String,Object> res = new HashMap<>();
        //object转map
//        Map<String, Object> b = gson.fromJson(entity.getContent(), res.getClass());
//        Map<String, Object> b = gson.fromJson("" + res.get("post_data"), res.getClass());
//        res.put("datas",b) ;
//        res.put("data",gson.fromJson(entity.getContent(), res.getClass())) ;
//        String data = entity.getContent().replaceAll("'","\"");
//        res.put("data",data) ;
        res.put("data",entity.getContent()) ;
//        res.put("data",s) ;
        res.put("code",entity.getCode()) ;
        res.put("id",entity.getId()) ;
        res.put("txid",entity.getTxId()) ;
        res.put("desc",entity.getDesc()) ;

        //根据附件id查询上链信息主键
        TBlockchainEventRecord  blockchain = new TBlockchainEventRecord();
        List<BlockChainEntity> entityList = new ArrayList();
        BlockChainEntity bceTmp = new BlockChainEntity();
        String content = "";
        if("4007".equals(tmp[0])){
            //交易
            String[] blockchainIdList =
                    tCommonAttachmentsMapper.selectTradeAnnex(deal_no);

            for (String blockchain_id : blockchainIdList){
                bceTmp = new BlockChainEntity();
                //查询链上附件信息
                bceTmp = blockChainService.findFromBlockChain(blockchain_id);
//                bceTmp.setContent(content);
                entityList.add(bceTmp);
            }
        }else if("3003".equals(tmp[0])){
            //船代提单
            TShipagentBillLoading TdInfo =
                    billLoadingService.get(Long.valueOf("" + resBusid.get("busi_id")));
            businessId = TdInfo.getUuid();
            entityList = GetEntityList(businessId,entityList);
            //船代sof
            tmpquery.put("event_code1","3007");
            tmpquery.put("event_code2","3008");
            //查询结果
            resBusid = tBlockchainEventRecordMapper.queryInfo(tmpquery);
            if(resBusid!=null){
                TShipagentSof sofInfo =
                        sofService.get(Long.valueOf("" + resBusid.get("busi_id")));
                businessId = sofInfo.getUuid();
                entityList = GetEntityList(businessId,entityList);
            }
        }else if("3007".equals(tmp[0])) {
            //船代sof
            TShipagentSof sofInfo =
                    sofService.get(Long.valueOf("" + resBusid.get("busi_id")));
            businessId = sofInfo.getUuid();
            entityList = GetEntityList(businessId,entityList);
            //船代提单
            tmpquery.put("event_code1","3003");
            tmpquery.put("event_code2","3004");
            //查询结果
            resBusid = tBlockchainEventRecordMapper.queryInfo(tmpquery);
            if(resBusid!=null){
                TShipagentBillLoading TdInfo =
                        billLoadingService.get(Long.valueOf("" + resBusid.get("busi_id")));
                businessId = TdInfo.getUuid();
                entityList = GetEntityList(businessId,entityList);
            }
        }else{
           //查询附件id
           List<TCommonAttachments> tCommonAttachments =
             tCommonAttachmentsMapper.selectByBusinessId(businessId);
           for (TCommonAttachments AttachmentTmp : tCommonAttachments){
            bceTmp = new BlockChainEntity();
            blockchain = tBlockchainEventRecordMapper.selectByBusiId(AttachmentTmp.getId());
            //查询链上附件信息
            bceTmp = blockChainService.findFromBlockChain(""+ blockchain.getBlockchainId());

            entityList.add(bceTmp);
           }
        }
        res.put("attachmentList",entityList);

        res.put("return_state","成功");
        return res;
    }


    /**
     * 取得附件list
     * @param blockchain_id
     * @return
     */
    private List<BlockChainEntity> GetEntityList(String businessId,List<BlockChainEntity> entityList) {
        BlockChainEntity bceTmp = new BlockChainEntity();
        TBlockchainEventRecord blockchain = new TBlockchainEventRecord();
        String content = "";
        //查询附件id
        List<TCommonAttachments> tCommonAttachments =
                tCommonAttachmentsMapper.selectByBusinessId(businessId);
        for (TCommonAttachments AttachmentTmp : tCommonAttachments){
            bceTmp = new BlockChainEntity();
             blockchain = tBlockchainEventRecordMapper.selectByBusiId(AttachmentTmp.getId());
            //查询链上附件信息
            bceTmp = blockChainService.findFromBlockChain(""+ blockchain.getBlockchainId());
//            bceTmp.setContent(content);
            entityList.add(bceTmp);
        }
        return entityList;
    }

    @Override
    public Map<String, Object> GetBlockChain(String blockchain_id) {
        //根据区块链id取得对应的信息
        BlockChainEntity entity= blockChainService.findFromBlockChain(blockchain_id);
        Map<String, Object> res = new HashMap<>();
        //String型的content转map
        Map<String, Object> b = gson.fromJson(entity.getContent(), res.getClass());
        res.put("datas",b) ;
        res.put("code",entity.getCode()) ;
        res.put("id",entity.getId()) ;
        res.put("txid",entity.getTxId()) ;
        res.put("desc",entity.getDesc()) ;
        return res;
    }

    @Override
    public Map<String, Object> GetSFHInfo(String deal_no) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("deal_no",deal_no);
        Map<String, Object> res = tBlockchainEventRecordMapper.querySFHInfo(map);
        return res;
    }
}
