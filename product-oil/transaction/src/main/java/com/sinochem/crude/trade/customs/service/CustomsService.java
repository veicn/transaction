package com.sinochem.crude.trade.customs.service;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.customs.domain.po.ResultInfoDatas;
import com.sinochem.crude.trade.customs.model.query.BlockchainInfoQuery;
import com.sinochem.crude.trade.customs.model.query.HomePageQuery;
import com.sinochem.crude.trade.transaction.domain.SimplePageInfo;
import com.sinochem.it.b2b.common.exception.BizException;

import java.util.List;
import java.util.Map;

public interface CustomsService {

    /**
     * 查询海关概要
     */
    List<Map<String,Object>> surveyGetInfo(BlockchainInfoQuery blockchainInfoQuery);

    /**
     *海关首页
     */
    Page<Map<String, Object>>  GetInfoList(HomePageQuery homePageQuery, SimplePageInfo pageInfo);

    /**
     *查询详情信息
     */
    Map<String, Object>  GetInfo(String deal_no, String event_code) throws Exception;

    /**
     * 查询区块链凭证
     * @param blockchain_id 区块链凭证id
     * @return
     */
    Map<String, Object> GetBlockChain(String blockchain_id);

    /**
     *收付汇详情
     */
    Map<String, Object>  GetSFHInfo(String deal_no) throws Exception;
}
