package com.sinochem.crude.trade.blockchain.service.impl;

import com.google.gson.Gson;
import com.sinochem.crude.trade.blockchain.dao.TBlockchainEventRecordMapper;
import com.sinochem.crude.trade.blockchain.dao.TSapEventRecordMapper;
import com.sinochem.crude.trade.blockchain.dao.TTransExtendMapper;
import com.sinochem.crude.trade.blockchain.domain.*;
import com.sinochem.crude.trade.blockchain.enums.BlockchainEventEnums;
import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.service.TBlockchainEventRecordService;
import com.sinochem.crude.trade.blockchain.service.TSapEventRecordService;
import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.sinochem.crude.trade.blockchain.utils.HttpUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.it.b2b.common.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class TSapEventRecordServiceImpl implements TSapEventRecordService {

    @Autowired
    private TSapEventRecordMapper tSapRecordMapper;

    @Autowired
    private TTransExtendMapper tTransExtendMapper;

    @Value("${blockchain_gateway}")
    private String url;


    @PostConstruct
    public void init(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12); // 控制时
        calendar.set(Calendar.MINUTE, 0);    // 控制分
        calendar.set(Calendar.SECOND, 0);    // 控制秒

        Date time = calendar.getTime();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("1---####----设定要指定任务--------");
                try {
                    saveTSapEventRecord();

                } catch (BizException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, time, 1000 * 60 * 60 * 12);// 这里设定将延时每天固定执行
    }


    @Override
    public void saveTSapEventRecord() throws BizException, InterruptedException {

        List<TTransExtend> tTransExtendList = tTransExtendMapper.selectAllList();
        for(TTransExtend tTransExtend : tTransExtendList){
            String purchaseContractNo = tTransExtend.getPurchaseContractNo();
            String res = HttpUtil.sapPostService(purchaseContractNo);
            //Thread.currentThread().sleep(3000);
            Gson gson=new Gson();
            Map map= gson.fromJson(res, HashMap.class);
            Double status = (Double)map.get("status");
            if(null==status||status.doubleValue()>0)
                continue;

            //上链
            SapSettlement sapSettlement = tSapRecordMapper.selectSapByBSTKD(purchaseContractNo);
            this.saveBlockChain(sapSettlement);
        }


    }

    @Override
    public TSapEventRecord findByPurchaseContractNo(String purchaseContractNo) throws BizException{

        return tSapRecordMapper.selectByPurchaseContractNo(purchaseContractNo);
    }

    @Override
    public SapSettlement selectSapByBSTKD(String purchaseContractNo) throws BizException {
        return tSapRecordMapper.selectSapByBSTKD(purchaseContractNo);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int deleteByPurchaseContractNo(String purchaseContractNo) throws BizException {
        return tSapRecordMapper.deleteByPurchaseContractNo(purchaseContractNo);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int insert(TSapEventRecord tSapEventRecord) throws BizException {
        return tSapRecordMapper.insert(tSapEventRecord);
    }

    @Transactional(rollbackFor=Exception.class)
    public int saveBlockChain(SapSettlement sapSettlement) throws BizException {
        BizException bizException=new BizException();
        Gson gson=new Gson();
        String cnt = gson.toJson(sapSettlement);
        //if(sapSettlement.getBSTKD().equals("18SG11XA2333MG032"))
        //url="123.123.123.123";

        BlockChainEntity entity= BlockChainUtil.insertToBlockChain(cnt,url);
        if (null==entity||null==entity.getCode()||!entity.getCode().equals("1")) {
            bizException.setCode(ExceptionEnum.BLOCKCHAIN_SAVE_ERROR.getCode());
            throw bizException;
        }
        TSapEventRecord tSapEventRecord=new TSapEventRecord();
        tSapEventRecord.setPurchaseContractNo(sapSettlement.getBSTKD());
        tSapEventRecord.setPostData(cnt);
        tSapEventRecord.setBlockchainId(entity.getId());
        tSapEventRecord.setBlockchainTxid(entity.getTxId());
        tSapEventRecord.setCreateDate(new Date());
        this.deleteByPurchaseContractNo(sapSettlement.getBSTKD());

        int res = this.insert(tSapEventRecord);
        return res;
    }

}
