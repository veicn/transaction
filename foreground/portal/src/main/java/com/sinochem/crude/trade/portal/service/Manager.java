package com.sinochem.crude.trade.portal.service;

import com.sinochem.crude.trade.portal.model.Contract;
import com.sinochem.crude.trade.portal.model.OilModelTypeEnum;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Manager {
    private String testServer = "http://192.168.48.1:8080/info/";

    public static Contract getContract(String contractUrl, int year, int month){
        Contract contract = new Contract();
        String content= ReadUrl(contractUrl);
        JSONObject jsonObject = new JSONObject(content);
        JSONArray jsonArray = jsonObject.getJSONArray("contract");
        if(jsonArray.length()>0){
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject job = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                if(year == job.getInt("year") && month == job.getInt("month")) {
                    contract.setYear(job.getInt("year"));
                    contract.setMonth(job.getInt("month"));
                    contract.setPrice(job.getDouble("price"));
                    break;
                }
            }
        }
        return contract;
    }

    /**
     * 计算同种油之间n月到m月的价格差值(待转时间在原始时间之后)
     */
    public double differenceOfOil(String oilType, int originalYear, int originalMonth, int conversionYear, int conversionMonth) {
        double difference = 0;
        //System.out.println(oilType.toUpperCase().equals(Oil.DUBAI.toString()));
        if(oilType.toUpperCase().equals(OilModelTypeEnum.ICE_BRENT.toString()) || oilType.toUpperCase().equals(OilModelTypeEnum.WTI.toString())) {
            //直接用m月价格减去n月价格得到
            String originalUrl = testServer + oilType + ".json";//infoServer.get(oilType + ".json").toString();
            Contract originalContract = Manager.getContract(originalUrl,originalYear,originalMonth);
            String conversionlUrl = testServer + oilType + ".json";//infoServer.get(oilType + ".json").toString();
            Contract conversionlContract = Manager.getContract(conversionlUrl,conversionYear,conversionMonth);
            difference = originalContract.getPrice() - conversionlContract.getPrice();
        } else if(oilType.toUpperCase().equals(OilModelTypeEnum.DUBAI.toString()) || oilType.toUpperCase().equals(OilModelTypeEnum.DTD_BRENT.toString())){
            //用连续的月差得到,此处没有考虑转换后的时间在原始时间之前的情况,应用n表示n->m的月差
            int months = (conversionMonth - originalMonth) >= 0 ? (conversionMonth - originalMonth) : (12 - originalMonth + conversionMonth);
            for(int i = 0; i < months; i++) {
                String originalUrl = testServer + oilType + ".json";//infoServer.get(oilType + ".json").toString();
                int year = (originalMonth + i <= 12) ? originalYear : (originalYear + 1);
                int month = (originalMonth + i <= 12) ? (originalMonth + i) : (originalMonth + i - 12);
                Contract contract = Manager.getContract(originalUrl,year,month);
                difference += contract.getPrice();
            }
        }
        return difference;
    }

    /**
     * 得到不同品种油在同一个年月的月差值
     * @param originalOil
     * @param conversionOil
     * @param year
     * @param month
     * @return
     */
    public double conversionPrice(String originalOil, String conversionOil, int year, int month) {
        double difference = 0;
        //原始品种油和待转品种油之间的转换链
        ArrayList<String> origTogConverList = new ArrayList<>();
        getConverList(originalOil,originalOil,conversionOil,origTogConverList);
        System.out.println(origTogConverList);
        for(String oilType : origTogConverList) {
            int sign = 1;
            String name = originalOil + oilType;
            switch (name) {
                case "ICEBrentDubai": name = "EFS"; sign = 1; break;
                case "DubaiICEBrent": name = "EFS"; sign = -1; break;
                case "DTDBrentDubai":
                case "WTIICEBrent": sign = 1; break;
                case "DubaiDTDBrent":
                case "ICEBrentWTI": name = oilType+originalOil; sign = -1; break;
            }
            String originalUrl = testServer + name + ".json";//infoServer.get(name + ".json").toString();
            System.out.println(originalUrl);
            Contract contract = Manager.getContract(originalUrl,year,month);
            difference += (sign * contract.getPrice());
            originalOil = oilType;
        }
        return  difference;
    }

    /**
     * 得到原始品种油和待转品种油之间的品种转换链,格式为[next1,next2,...,conversionOil]
     * @param originalOil
     * @param conversionOil
     * @param converList
     * @return
     */
    private void getConverList(String originalOil, String originalOilTemp, String conversionOil, ArrayList<String> converList) {
        OilNode oilNode = getOilNode(originalOilTemp);
        for(int i = 0; i < oilNode.conversionList.size(); i++) {
            String oilType = oilNode.conversionList.get(i);
            converList.add(oilType);
            if(oilType.equals(conversionOil))
                return;
            else if(oilType.equals(originalOil)) {
                converList.remove(converList.size() - 1);
                if (i == oilNode.conversionList.size() - 1)
                    converList.remove(converList.size() - 1);
                continue;
            } else {
                getConverList(originalOil, oilType, conversionOil, converList);
            }
        }
    }

    /**
     * 得到各个油转换的对照表
     */
    private OilNode getOilNode(String oilType) {
        ArrayList<String> conversionList = new ArrayList<>();
        switch (oilType) {
            case "DTDBrent":
                conversionList.add("Dubai");
                break;
            case "Dubai":
                conversionList.add("DTDBrent");
                conversionList.add("ICEBrent");
                break;
            case "ICEBrent":
                conversionList.add("WTI");
                conversionList.add("Dubai");
                break;
            case "WTI":
                conversionList.add("ICEBrent");
                break;
        }
        return new OilNode(oilType, conversionList);
    }

    private class OilNode{
        private String oilType;
        private ArrayList<String> conversionList;
        public OilNode(String oilType, ArrayList<String> conversionList) {
            this.oilType = oilType;
            this.conversionList = conversionList;
        }
    }

    /**
     * 读取远程文件
     * @param FileName
     * @return
     */
    public static String ReadUrl(String FileName) {
        String read;
        String readStr ="";
        try{
            URL url =new URL(FileName);
            HttpURLConnection urlCon = (HttpURLConnection)url.openConnection();
            urlCon.setConnectTimeout(5000);
            urlCon.setReadTimeout(5000);
            BufferedReader br = new BufferedReader(new InputStreamReader( urlCon.getInputStream()));
            while ((read = br.readLine()) !=null) {
                readStr = readStr + read;
            }
            br.close();
        } catch (IOException e) {
            readStr ="f";
        }
        return readStr;
    }

    public static void main(String[] args) {
        //测试不同油同一年月的差价
        Manager manager = new Manager();
        ArrayList<String> convList = new ArrayList<>();
        manager.getConverList("WTI","WTI","ICEBrent",convList);
        System.out.println(convList);

       /*String s = ReadUrl("http://192.168.48.1/WTI.json");
       System.out.print(s);*/

      /* Contract contract = getContract("http://192.168.48.1/WTI.json", 2011, 11);
       System.out.print(contract.getPrice());*/

      /*Manager manager = new Manager();
      //double d = manager.differenceOfOil("WTI", 2011,1,2011,4);
        double d = manager.conversionPrice("ICEBrent","WTI",2011,4);
        System.out.println(d);*/
    }
}
