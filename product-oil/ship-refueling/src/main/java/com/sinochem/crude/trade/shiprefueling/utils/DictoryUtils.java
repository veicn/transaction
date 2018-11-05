package com.sinochem.crude.trade.shiprefueling.utils;

import com.sinochem.crude.trade.shiprefueling.enums.CredentialsEnums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author WGP
 * @descriptioncrude-trade
 * @date 2018/6/7
 **/
public class DictoryUtils {

    private static HashMap<String,CredentialsEnums> credentialEnumsMap = new HashMap<>(64);

    public static String[] getCredentials(String[] credentialCodes){
        if(credentialEnumsMap.isEmpty()){
            init();
        }
        if(credentialCodes == null || credentialCodes.length<=0){
            return new String[]{};
        }
        int len = credentialCodes.length;
        List<String> credentialList=new ArrayList<>();
        int count =0;
       for(int i =0;i<len;i++){
          String name = credentialEnumsMap.get(credentialCodes[i])==null?null:credentialEnumsMap.get(credentialCodes[i]).getZhName();
           if(name != null && name.trim().length()>0 && !"null".equals(name.trim())){
               credentialList.add(name);
           }
       }
       int realCount = credentialList.size();
       String[] credentials = new String[realCount];
       for(int i=0;i<realCount;i++){
           credentials[i] = credentialList.get(i);
       }
       return credentials;
    }

    private static void init(){
        for(CredentialsEnums obj:CredentialsEnums.values()){
            credentialEnumsMap.put(obj.getCode(),obj);
        }
    }
}
