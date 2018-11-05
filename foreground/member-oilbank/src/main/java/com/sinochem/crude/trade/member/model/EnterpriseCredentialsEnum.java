package com.sinochem.crude.trade.member.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xyuser on 2018/1/19.
 */
public enum EnterpriseCredentialsEnum {

    SYSTEM("系统", 0),
    POTRERILLOS("炼厂资质", 1),
    TRADINGCOMPANY("贸易商资质", 2),
    INSPECTION("商检资质", 3),
    SHIP_OWNER("船东资质", 4),
    SHIP_BROKER("船经纪资质", 5) ,
    SHIP_PROXY("船经纪资质", 6) ,
    SHIP_CHARTER_PROXY("租船代理资质", 7) ,
    ENTERPRISES("企业资质", 99);


    private String name ;
    private int code ;

    EnterpriseCredentialsEnum( String name , int code ){
        this.name = name ;
        this.code = code ;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public static String toName(int code){
        for(EnterpriseCredentialsEnum item : EnterpriseCredentialsEnum.values()){
            if(item.getCode()==code)return item.getName();
        }
        return null;
    }

    public static List toList(){
        List list = new ArrayList();
        for(EnterpriseCredentialsEnum item : EnterpriseCredentialsEnum.values()){
            Map testMap = new HashMap();
            testMap.put("code",item.getCode());
            testMap.put("name",item.getName());
            list.add(item);
        }
        return list;
    }

}
