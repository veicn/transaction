package com.sinochem.crude.trade.inspector.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinochem.crude.trade.blockchain.dao.TInspectorAppointMapper;
import com.sinochem.crude.trade.blockchain.dao.TInspectorPresentationMapper;
import com.sinochem.crude.trade.blockchain.domain.TBlockchainEventRecord;
import com.sinochem.crude.trade.blockchain.domain.TInspectorAppoint;
import com.sinochem.crude.trade.blockchain.domain.TInspectorPresentation;
import com.sinochem.crude.trade.blockchain.domain.TTransExtend;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.service.BlockChainService;
import com.sinochem.crude.trade.blockchain.service.TBlockchainEventRecordService;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.inspector.constant.Constant;
import com.sinochem.crude.trade.inspector.enums.AppointEnum;
import com.sinochem.crude.trade.inspector.enums.InspectorStateEnum;
import com.sinochem.crude.trade.inspector.model.query.ReportInfoQuery;
import com.sinochem.crude.trade.inspector.model.query.TaskInfoQuery;
import com.sinochem.crude.trade.inspector.service.ReportInfoService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.enums.PricingUnitEnum;
import com.sinochem.crude.trade.transaction.enums.ProductCategoryEnum;
import com.sinochem.crude.trade.transaction.enums.ProductSpecificationEnum;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetCombineVO;
import com.sinochem.crude.trade.transaction.model.vo.ResultDatas;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.web.controller.MyContractDetailController;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportInfoServiceImpl implements ReportInfoService {
    @Autowired
    private TInspectorPresentationMapper tInspectorPresentationMapper;

    @Autowired
    private TInspectorAppointMapper tInspectorAppointMapper;

    @Autowired
    private BlockChainService blockChainService;

    @Autowired
    private TBlockchainEventRecordService tBlockchainEventRecordService;

    Gson gson = new Gson();

    @Override
    public ResultDatas insertRecord(TInspectorPresentation reportInfo) throws BizException {
        ResultDatas res = new ResultDatas();
        // /企业信息查询并插入
        reportInfo.setCreateDate(new Date());
        reportInfo.setAliveFlag("1");
        reportInfo.setUuid(KeyGenUtils.newUuid());
        reportInfo.setId(null);
        int result = tInspectorPresentationMapper.insert(reportInfo);
        res.setDatas(reportInfo);
        res.setStatus(0);
        res.setMessage("商检报告保存成功。");
        //更新任务列表
        TInspectorAppoint inspectorAppoint = new TInspectorAppoint();
        inspectorAppoint.setId(reportInfo.getInspAppId());
        inspectorAppoint.setInspPreId(reportInfo.getId());
        //委托商检状态：2已确认
        if("0".equals(reportInfo.getState())){
            //商检报告状态（暂存:0，已提交:1）
            inspectorAppoint.setStatus(AppointEnum.HANG_IN_THE_AIR.getCode());
        }else{
            inspectorAppoint.setStatus(AppointEnum.COMPLETED_FLAG.getCode());
        }
        tInspectorAppointMapper.updateByPrimaryKeySelective(inspectorAppoint);

        res.setStatus(0);
        res.setMessage("商检报告保存成功，商检任务更新成功。");

        //上链
        //提交状态才上链
        if ("0".equals(reportInfo.getState())){
            return res;
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String content = gson.toJson(reportInfo);
        BlockChainEntity entity= blockChainService.insertToBlockChain(content);

        BizException bizException=new BizException();
        if (null==entity||!entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw new BizException();
        }

        res.setStatus(0);
        res.setMessage("商检报告保存成功，商检任务更新成功，商检报告上链成功。");
        TBlockchainEventRecord tBlockchainEventRecord=new TBlockchainEventRecord();
        tBlockchainEventRecord.setCreateUser(reportInfo.getCreateUser());
        tBlockchainEventRecord.setDealNo(reportInfo.getDealNo());
        tBlockchainEventRecord.setBusiId(Long.valueOf(reportInfo.getId()));
        if(content.length()>1000){
            tBlockchainEventRecord.setPostData(content.substring(0,1000));
        }else {
            tBlockchainEventRecord.setPostData(content);
        }
        tBlockchainEventRecord.setEventCode("1004");
        tBlockchainEventRecord.setDescription("上传商检单信息");
        tBlockchainEventRecord.setBlockchainId(entity.getId());
        tBlockchainEventRecord.setBlockchainTxid(entity.getTxId());
        tBlockchainEventRecord.setCreateDate(new Date());
        tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);

        res.setStatus(0);
        res.setMessage("商检报告保存成功，商检任务更新成功，商检报告上链成功，商检报告回写上链事件记录成功。");
        return res;
    }

    @Override
    public ResultDatas updateRecord(TInspectorPresentation reportInfo) throws BizException {
        ResultDatas res = new ResultDatas();
        //企业信息查询并插入
        reportInfo.setUpdateDate(new Date());
        reportInfo.setAliveFlag("1");
        int result = tInspectorPresentationMapper.updateByPrimaryKey(reportInfo);
        res.setDatas(reportInfo);

        res.setStatus(0);
        res.setMessage("商检报告更新成功。");
        //更新任务列表
        TInspectorAppoint inspectorAppoint = new TInspectorAppoint();

        //商检任务主键
        inspectorAppoint.setId(reportInfo.getInspAppId());
        //委托商检状态：已完成
        if("0".equals(reportInfo.getState())){
            //商检报告状态（暂存:0，已提交:1）
            inspectorAppoint.setStatus(AppointEnum.HANG_IN_THE_AIR.getCode());
        }else{
            inspectorAppoint.setStatus(AppointEnum.COMPLETED_FLAG.getCode());
        }
        //商检报告主键
        inspectorAppoint.setInspPreId(reportInfo.getId());
        tInspectorAppointMapper.updateByPrimaryKeySelective(inspectorAppoint);

        res.setStatus(0);
        res.setMessage("商检报告跟新成功，商检任务更新成功。");
        //上链
        //提交状态才上链
        if ("0".equals(reportInfo.getState())){
            return res;
        }
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        String content = gson.toJson(reportInfo);
        BlockChainEntity entity= blockChainService.insertToBlockChain(content);
        BizException bizException=new BizException();
        if (null==entity||!entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw new BizException();
        }

        res.setStatus(0);
        res.setMessage("商检报告跟新成功，商检任务更新成功，商检报告上链成功");

        TBlockchainEventRecord tBlockchainEventRecord=new TBlockchainEventRecord();
        tBlockchainEventRecord.setCreateUser(reportInfo.getCreateUser());
        tBlockchainEventRecord.setDealNo(reportInfo.getDealNo());
        tBlockchainEventRecord.setBusiId(Long.valueOf(reportInfo.getId()));
        if(content.length()>1000){
            tBlockchainEventRecord.setPostData(content.substring(0,1000));
        }else {
            tBlockchainEventRecord.setPostData(content);
        }
        tBlockchainEventRecord.setEventCode("1003");
        tBlockchainEventRecord.setDescription("更新商检单信息");
        tBlockchainEventRecord.setBlockchainId(entity.getId());
        tBlockchainEventRecord.setBlockchainTxid(entity.getTxId());
        tBlockchainEventRecord.setCreateDate(new Date());
        tBlockchainEventRecordService.saveTBlockchainEventRecord(tBlockchainEventRecord);

        res.setStatus(0);
        res.setMessage("商检报告跟新成功，商检任务更新成功，商检报告上链成功，商检报告回写上链事件记录成功。");
        return res;
    }

    @Override
    public int submitRecord(TInspectorPresentation reportInfo,/** List<ChmentInspectorVO> chmentsVOList ,*/CurrentUser user) {
        return 0;
    }

    @Override
    public TInspectorPresentation queryReportInfo(ReportInfoQuery reportInfoQuery) {
        return null;
    }
    @Override
    public TInspectorPresentation queryReportInfoById(Long id) {
        return tInspectorPresentationMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Map<String, Object>>  queryTaskList(TaskInfoQuery inspectorAppoint, SimplePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), "date DESC" );
        Page<Map<String, Object>> page = (Page<Map<String, Object>>) tInspectorAppointMapper.queryTaskInfoList(inspectorAppoint.toMap());
        MyContractDetailController controller = new MyContractDetailController();

        Map<String,Object> contractSheet = new HashMap<>();
        for(Map<String , Object> map : page){
            //商检状态
            if(AppointEnum.toMap().containsKey(map.get("STATE"))){
                map.put("STATE", AppointEnum.toMap().get(map.get("STATE")));
            }
            contractSheet = tInspectorPresentationMapper.queryTransactionInfoInfoList("" + map.get("deal_uuid"));
            if(contractSheet!=null){
                //油种
                map.put("oilseed",
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
    public Page<Map<String, Object>> queryReportInfoList(ReportInfoQuery reportInfoQuery, SimplePageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize(), "date DESC" );

        Page<Map<String, Object>> page = (Page<Map<String, Object>>) tInspectorPresentationMapper.queryReportInfoList(reportInfoQuery.toMap());

        Map<String,Object> contractSheet = new HashMap<>();

        for(Map<String , Object> map : page){
            //商检状态
            if(InspectorStateEnum.toMap().containsKey(map.get("STATE"))){
                map.put("STATE", InspectorStateEnum.toMap().get(map.get("STATE")));
            }
            contractSheet = tInspectorPresentationMapper.queryTransactionInfoInfoList("" + map.get("deal_uuid"));
            if(contractSheet!=null){
                //油种
                map.put("oilseed", getZhNameByCode(ProductCategoryEnum.toMap(),"" + contractSheet.get("product_category_code")));
                //数量
                map.put("num", contractSheet.get("quantity"));
                //装期 开始时间 结束时间
                map.put("laycan_start_date", contractSheet.get("laycan_start_date"));
                map.put("laycan_end_date", contractSheet.get("laycan_end_date"));
                //委托方
                map.put("委托方主表id",map.get("tssid"));
                map.put("enterprise_id",map.get("enterprise_id"));
                map.put("enterprise_name",contractSheet.get("enterprise_name"));
                map.put("appoint_enterprise_id",map.get("appoint_enterprise_id"));
                map.put("appoint_enterprise_name",contractSheet.get("appoint_enterprise_name"));
                //单位
                map.put("quantity_unit_code",
                        getZhNameByCode(PricingUnitEnum.toMap(),"" + contractSheet.get("quantity_unit_code")));
            }
        }
        return  page;
    }

    /**
     * 查询交易信息
     * @return
     */
    @Override
    public Map<String, Object> getTransactionInfo(String id){
        Map<String, Object> map = new HashMap<>();
        if(id==null&&"".equals(id)){
            return null;
        }
        TInspectorAppoint tInspectorAppoint =  tInspectorAppointMapper.selectByPrimaryKey(Long.valueOf(id));

        if (tInspectorAppoint==null){
            map.put("status",999);
            map.put("message","没查询到任务详情");
        }else if (tInspectorAppoint.getDealUuid()==null){
            map.put("status",999);
            map.put("message","任务详情没有对应的deal_uuid");
        }
        Map<String,Object> contractSheet = new HashMap<>();
        contractSheet = tInspectorPresentationMapper.queryTransactionInfoInfoList("" + tInspectorAppoint.getDealUuid());
        if(contractSheet!=null){
            //油种
            map.put("oilseed",
                    getZhNameByCode(ProductCategoryEnum.toMap(),"" + contractSheet.get("product_category_code")));
            //数量
            map.put("num", contractSheet.get("quantity"));
            //装期 开始 结束
            map.put("laycan_start_date", contractSheet.get("laycan_start_date"));
            map.put("laycan_end_date", contractSheet.get("laycan_end_date"));
            //委托方
            map.put("委托方主表id",map.get("tssid"));
            map.put("enterprise_id",map.get("enterprise_id"));
            map.put("enterprise_name",contractSheet.get("enterprise_name"));
            map.put("appoint_enterprise_id",map.get("appoint_enterprise_id"));
            map.put("appoint_enterprise_name",contractSheet.get("appoint_enterprise_name"));
            //规格
            map.put("specifications",getZhNameByCode(ProductSpecificationEnum.toMap(),"" + contractSheet.get("product_specification_code")));
            //单位
            map.put("quantity_unit_code",
                    getZhNameByCode(PricingUnitEnum.toMap(),"" + contractSheet.get("quantity_unit_code")));
        }
        map.put("status",0);
        map.put("message","商检交易信息查询成功");
        return  map;
    }

    @Override
    public Map<String, Object> getReportBillNo(ReportInfoQuery reportInfoQuery) {
        Map<String, Object> res = new HashMap<>();
        List<String > billNoList=  tInspectorPresentationMapper.selectBillNo(reportInfoQuery.toMap());
        res.put("billnoList",billNoList);
        return  res;
    }
}
