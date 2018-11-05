package com.sinochem.crude.trade.blockchain.dao;

import com.sinochem.crude.trade.blockchain.domain.TBlockchainEventRecord;

import java.util.List;
import java.util.Map;

public interface TBlockchainEventRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TBlockchainEventRecord record);

    int insertSelective(TBlockchainEventRecord record);

    TBlockchainEventRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TBlockchainEventRecord record);

    int updateByPrimaryKey(TBlockchainEventRecord record);


    //**************************以下方法为开发者补充*********************************/

    //根据busiId取得附件上传删除等信息
    TBlockchainEventRecord selectByBusiId(Long busiId);

    //海关概况
    public List<Map<String,Object>> queryBlockchainInfoList(Map<String,Object> map);

    //海关首页
    public List<Map<String,Object>> queryInfoList(Map<String,Object> map);

    //查询区块链信息
    public Map<String,Object> queryInfo(Map<String,Object> map);

    //查询区块链附件上传信息(删除用)
    public Map<String,Object> queryDeleteInfo(Map<String,Object> map);

    //查询收付汇信息
    public Map<String,Object> querySFHInfo(Map<String,Object> map);
}