package com.sinochem.crude.trade.blockchain.utils;

import com.google.gson.Gson;

import com.sinochem.crude.trade.blockchain.model.BlockChainEntity;
import com.sinochem.crude.trade.shipagent.utils.Result;
import com.zhqkl.api.BlockchainService;
import com.zhqkl.api.Records;
import com.zhqkl.api.ResObject;
import org.apache.commons.codec.binary.Hex;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;

public class BlockChainUtil {

    public static BlockchainService blockGateway=new BlockchainService();


    public static String getToken(){
        BlockchainService blockGateway=new BlockchainService();
        BlockChainEntity entity=new BlockChainEntity();
        try {
            Records records = new Records();
            records.setIp("http://10.144.136.20:4000");
            records.setUsername("Jim");
            records.setPassword("123");
            records.setOrgName("Org1");
            records.setChaincodeName("recordset");
            records.setInvokePeers("[\"peer0.org1.example.com\", \"peer1.org1.example.com\"]");
            records.setRecordId("a02");
            records.setContent("hello world02");
            records.setQueryPeer("peer0.org1.example.com");
            ResObject resObject=blockGateway.loginUser(records);
            Object data = resObject.getData();

            if(null!=resObject.getData()){
                Gson gson=new Gson();
                HashMap map =gson.fromJson(resObject.getData().toString(),HashMap.class);
                String token = map.get("token").toString();
                String message = map.get("message").toString();
                return token;
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }




    public static BlockChainEntity insertToBlockChain(String content,String url){
//        content=content.replaceAll("\"", "\'");
        /*content=content.replaceAll("\"", "❤❤❤");
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

        content=content.replaceAll("\"", "❤❤❤");
        Records records = new Records();
        records.setIp("http://10.144.136.20:4000");
        records.setUsername("Jim");
        records.setPassword("123");
        records.setOrgName("Org1");
        records.setChaincodeName("recordset");
        //records.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDA1NTEyNjksInVzZXJuYW1lIjoiSmltIiwib3JnTmFtZSI6Ik9yZzEiLCJpYXQiOjE1NDA1NDc2Njl9.qTk00EumUEnK6b1FOOsDpRzMhhXfjSFvv_p1V7LQuOs");
        records.setToken(getToken());
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

    public static BlockChainEntity findFromBlockChain(String id,String url){
        /*Records records = new Records();
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


        Records records = new Records();
        records.setIp("http://10.144.136.20:4000");
        records.setUsername("Jim");
        records.setPassword("123");
        records.setOrgName("Org1");
        records.setChaincodeName("recordset");
        //records.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDA1NTEyNjksInVzZXJuYW1lIjoiSmltIiwib3JnTmFtZSI6Ik9yZzEiLCJpYXQiOjE1NDA1NDc2Njl9.qTk00EumUEnK6b1FOOsDpRzMhhXfjSFvv_p1V7LQuOs");
        records.setToken(getToken());
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

    public static void main(String[] args) {

        try {/*
            //参数类
            Records records = new Records();
            //添加及修改方法需要传递参数，上链内容
            records.setContent("{'deal_no':'180123049438','trans_amt':'12800.12','trans_num':'188'}");
            //IP地址参数，所有都需要传递
            records.setIp("http://10.144.132.56:4010");
           *//* //修改及单独查询方法传RecordId
            records.setRecordId("f4b894b8-c549-41dc-b31f-8309a4003fdc");*//*

            //添加上链方法
            ResObject resObject=blockGateway.addRecords(records);

            //code为返回码1=未成功
            System.out.println(String.valueOf(resObject.getCode())
                    //Data数据，只有code=1时有返回参数，其他值没有
                    +resObject.getData()
                    //desc回调描述：始终又返回描述
                    +resObject.getDesc());

            records.setRecordId("a7e9ca09-e93a-4d6d-ab46-6059e1ca811a");

            //查询单条链信息方法
            ResObject resObject2=blockGateway.queryRecords(records);
            //code为返回码1=未成功
            System.out.println(String.valueOf(resObject.getCode())
                    //Data数据，只有code=1时有返回参数，其他值没有
                    +resObject.getData()
                    //desc回调描述：始终又返回描述
                    +resObject.getDesc());
*/
//            String content="{'deal_no':'180123049438','trans_amt':'12800.12','trans_num':'188'}";



            /*
            Result result=new Result();
            result.setMessage("123'567");
            Gson gson=new Gson();
            String content=gson.toJson(result);//"{\"deal_no\":\"1801230'49438\"}";
            BlockChainEntity entity=insertToBlockChain(content, "http://10.144.136.20:4000");

            BlockChainEntity entity2=findFromBlockChain("807bec72cb5d2983fc1c074b06345cf73b32fd432d78e59c47fa6436076b18cc","http://10.144.136.20:4000");
            String content2=entity2.getContent();
            String id=entity2.getId();
            System.out.print(content2);*/

            //getToken();

            String content="test.......4444";
            BlockChainEntity entity=insertToBlockChain(content, "http://10.144.136.20:4000");

            String code = entity.getCode();

          /*  BlockChainEntity entity=findFromBlockChain("cf8e9889-f586-42bf-b39a-30e8eff87379", "http://10.144.136.20:4000");
            String code = entity.getCode();
*/
        }catch (Exception e){
            e.printStackTrace();
        }




    }

    public static String getFileSha1(byte[] src) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            String sha1 = Hex.encodeHexString(messageDigest.digest(src));
            return sha1;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String FormetFileSize(Long fileLength) {
        String fileSizeString = "";
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength != null) {
            if (fileLength < 1024) {
                fileSizeString = df.format((double) fileLength) + "B";
            }
            else if (fileLength < 1048576) {
                fileSizeString = df.format((double) fileLength / 1024) + "K";
            }
            else if (fileLength < 1073741824) {
                fileSizeString = df.format((double) fileLength / 1048576) + "M";
            }
            else {
                fileSizeString = df.format((double) fileLength / 1073741824) + "G";
            }
        }
        return fileSizeString;
    }

}
