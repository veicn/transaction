package com.sinochem.crude.trade.customs.utils;

import com.google.gson.Gson;
import com.sinochem.crude.trade.customs.model.BlockChainEntity;
import com.zhqkl.api.BlockGateway;
import com.zhqkl.api.Records;
import com.zhqkl.api.ResObject;

public class BlockChainUtil {

    static BlockGateway blockGateway=new BlockGateway();
    static String url="http://10.144.136.20:4000";

    static BlockChainEntity insertToBlockChain(String content){
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

        return entity;
    }

    static BlockChainEntity findFromBlockChain(String id){
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
            String content="{'deal_no':'180123049438','trans_amt':'12800.12','trans_num':'188'}";
            BlockChainEntity entity=insertToBlockChain(content);

            BlockChainEntity entity2=findFromBlockChain(entity.getId());
            String content2=entity2.getContent();
            String id=entity2.getId();


        }catch (Exception e){
            e.printStackTrace();
        }




    }


}
