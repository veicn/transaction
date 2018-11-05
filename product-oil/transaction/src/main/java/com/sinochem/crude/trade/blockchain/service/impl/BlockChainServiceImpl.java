package com.sinochem.crude.trade.blockchain.service.impl;

import com.google.gson.Gson;

import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.blockchain.service.BlockChainService;
import com.sinochem.crude.trade.blockchain.utils.BlockChainUtil;
import com.zhqkl.api.BlockchainService;
import com.zhqkl.api.Records;
import com.zhqkl.api.ResObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class BlockChainServiceImpl implements BlockChainService {

    @Value("${blockchain_gateway}")
    private String url;

    public  BlockChainEntity insertToBlockChain(String content){
//        content=content.replaceAll("\"", "\'");


        /*content=content.replaceAll("\"", "❤❤❤");
        BlockGateway blockGateway=new BlockGateway();
        Records records = new Records();
        records.setContent(content);
        records.setIp(url);
        ResObject resObject=blockGateway.addRecords(records);
        BlockChainEntity entity=new BlockChainEntity();
        if(null!=resObject.getData()){
            Gson gson=new Gson();
            entity=gson.fromJson(resObject.getData().toString(),BlockChainEntity.class);
            entity.setContent(entity.getContent().replaceAll("❤❤❤", "\""));
            entity.setCode(String.valueOf(resObject.getCode()));
            entity.setDesc(resObject.getDesc());

        }

        return entity;*/

        BlockchainService blockGateway=new BlockchainService();

        content=content.replaceAll("\"", "❤❤❤");
        Records records = new Records();
        records.setIp("http://10.144.136.20:4000");
        records.setUsername("Jim");
        records.setPassword("123");
        records.setOrgName("Org1");
        records.setChaincodeName("recordset");
        //records.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDA1NTEyNjksInVzZXJuYW1lIjoiSmltIiwib3JnTmFtZSI6Ik9yZzEiLCJpYXQiOjE1NDA1NDc2Njl9.qTk00EumUEnK6b1FOOsDpRzMhhXfjSFvv_p1V7LQuOs");
        records.setToken(BlockChainUtil.getToken());
        records.setInvokePeers("[\"peer0.org1.example.com\", \"peer1.org1.example.com\"]");
        String uuid = UUID.randomUUID().toString();
        records.setRecordId(uuid);
        records.setContent(content);
        records.setQueryPeer("peer0.org1.example.com");
        records.setLimits("10");
        records.setFcn("insertData");
        records.setChannelName("mychannel2");
        ResObject resObject=blockGateway.addOrModifyRecords(records);
        BlockChainEntity entity=new BlockChainEntity();
        if(null!=resObject.getData()){
            Gson gson=new Gson();
            entity=gson.fromJson(resObject.getData().toString(),BlockChainEntity.class);
            entity.setCode(String.valueOf(resObject.getCode()));
            entity.setDesc(resObject.getDesc());
            entity.setId(uuid);
            entity.setContent(content);
        }

        return entity;
    }

    public  BlockChainEntity findFromBlockChain(String id){
        /*BlockGateway blockGateway=new BlockGateway();
        Records records = new Records();
        records.setRecordId(id);
        records.setIp(url);
        ResObject resObject=blockGateway.queryRecords(records);
        BlockChainEntity entity=new BlockChainEntity();
        if(null!=resObject.getData()){
            Gson gson=new Gson();
            entity=gson.fromJson(resObject.getData().toString(),BlockChainEntity.class);
            entity.setContent(entity.getContent().replaceAll("❤❤❤", "\""));
            entity.setCode(String.valueOf(resObject.getCode()));
            entity.setDesc(resObject.getDesc());

        }

        return entity;*/

        BlockchainService blockGateway=new BlockchainService();
        Records records = new Records();
        records.setIp("http://10.144.136.20:4000");
        records.setUsername("Jim");
        records.setPassword("123");
        records.setOrgName("Org1");
        records.setChaincodeName("recordset");
        //records.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDA1NTEyNjksInVzZXJuYW1lIjoiSmltIiwib3JnTmFtZSI6Ik9yZzEiLCJpYXQiOjE1NDA1NDc2Njl9.qTk00EumUEnK6b1FOOsDpRzMhhXfjSFvv_p1V7LQuOs");
        records.setToken(BlockChainUtil.getToken());
        records.setInvokePeers("[\"peer0.org1.example.com\", \"peer1.org1.example.com\"]");
        records.setRecordId(id);
        records.setQueryPeer("peer0.org1.example.com");
        records.setLimits("10");
        records.setFcn("insertData");
        records.setChannelName("mychannel2");
        records.setTxid("0aab5d6219de4e68a06a9b2e1bf8cb4c911c43cd6de4b99c965f7802a21b5f62");
        ResObject resObject=blockGateway.queryRecords(records);
        BlockChainEntity entity=new BlockChainEntity();
        if(null!=resObject.getData()){
            Gson gson=new Gson();
            entity=gson.fromJson(resObject.getData().toString(),BlockChainEntity.class);
            HashMap map =gson.fromJson(resObject.getData().toString(),HashMap.class);
            entity.setContent(map.get("payload").toString().replaceAll("❤❤❤", "\""));
            entity.setCode(String.valueOf(resObject.getCode()));
            entity.setDesc(resObject.getDesc());
            entity.setId(id);

        }

        return entity;


    }

    public  BlockChainEntity insertToBlockChainByUrl(String content,String url){
        /*content=content.replaceAll("\"", "\'");
        BlockGateway blockGateway=new BlockGateway();
        Records records = new Records();
        records.setContent(content);
        records.setIp(url);
        ResObject resObject=blockGateway.addRecords(records);
        BlockChainEntity entity=new BlockChainEntity();
        if(null!=resObject.getData()){
            Gson gson=new Gson();
            entity=gson.fromJson(resObject.getData().toString(),BlockChainEntity.class);
            entity.setCode(String.valueOf(resObject.getCode()));
            entity.setDesc(resObject.getDesc());

        }

        return entity;*/


        BlockchainService blockGateway=new BlockchainService();

        content=content.replaceAll("\"", "❤❤❤");
        Records records = new Records();
        records.setIp("http://10.144.136.20:4000");
        records.setUsername("Jim");
        records.setPassword("123");
        records.setOrgName("Org1");
        records.setChaincodeName("recordset");
        //records.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDA1NTEyNjksInVzZXJuYW1lIjoiSmltIiwib3JnTmFtZSI6Ik9yZzEiLCJpYXQiOjE1NDA1NDc2Njl9.qTk00EumUEnK6b1FOOsDpRzMhhXfjSFvv_p1V7LQuOs");
        records.setToken(BlockChainUtil.getToken());
        records.setInvokePeers("[\"peer0.org1.example.com\", \"peer1.org1.example.com\"]");
        String uuid = UUID.randomUUID().toString();
        records.setRecordId(uuid);
        records.setContent(content);
        records.setQueryPeer("peer0.org1.example.com");
        records.setLimits("10");
        records.setFcn("insertData");
        records.setChannelName("mychannel2");
        ResObject resObject=blockGateway.addOrModifyRecords(records);
        BlockChainEntity entity=new BlockChainEntity();
        if(null!=resObject.getData()){
            Gson gson=new Gson();
            entity=gson.fromJson(resObject.getData().toString(),BlockChainEntity.class);
            entity.setCode(String.valueOf(resObject.getCode()));
            entity.setDesc(resObject.getDesc());
            entity.setId(uuid);
            entity.setContent(content);
        }

        return entity;
    }

    public  BlockChainEntity findFromBlockChainByUrl(String id,String url){
       /* BlockGateway blockGateway=new BlockGateway();
        Records records = new Records();
        records.setRecordId(id);
        records.setIp(url);
        ResObject resObject=blockGateway.queryRecords(records);
        BlockChainEntity entity=new BlockChainEntity();
        if(null!=resObject.getData()){
            Gson gson=new Gson();
            entity=gson.fromJson(resObject.getData().toString(),BlockChainEntity.class);
            entity.setCode(String.valueOf(resObject.getCode()));
            entity.setDesc(resObject.getDesc());

        }

        return entity;*/

        BlockchainService blockGateway=new BlockchainService();
        Records records = new Records();
        records.setIp("http://10.144.136.20:4000");
        records.setUsername("Jim");
        records.setPassword("123");
        records.setOrgName("Org1");
        records.setChaincodeName("recordset");
        //records.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDA1NTEyNjksInVzZXJuYW1lIjoiSmltIiwib3JnTmFtZSI6Ik9yZzEiLCJpYXQiOjE1NDA1NDc2Njl9.qTk00EumUEnK6b1FOOsDpRzMhhXfjSFvv_p1V7LQuOs");
        records.setToken(BlockChainUtil.getToken());
        records.setInvokePeers("[\"peer0.org1.example.com\", \"peer1.org1.example.com\"]");
        records.setRecordId(id);
        records.setQueryPeer("peer0.org1.example.com");
        records.setLimits("10");
        records.setFcn("insertData");
        records.setChannelName("mychannel2");
        records.setTxid("0aab5d6219de4e68a06a9b2e1bf8cb4c911c43cd6de4b99c965f7802a21b5f62");
        ResObject resObject=blockGateway.queryRecords(records);
        BlockChainEntity entity=new BlockChainEntity();
        if(null!=resObject.getData()){
            Gson gson=new Gson();
            entity=gson.fromJson(resObject.getData().toString(),BlockChainEntity.class);
            HashMap map =gson.fromJson(resObject.getData().toString(),HashMap.class);
            entity.setContent(map.get("payload").toString().replaceAll("❤❤❤", "\""));
            entity.setCode(String.valueOf(resObject.getCode()));
            entity.setDesc(resObject.getDesc());
            entity.setId(id);
        }

        return entity;

    }

}
