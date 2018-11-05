package com.sinochem.crude.trade.portal.controller;

import com.sinochem.crude.trade.portal.model.Contract;
import com.sinochem.crude.trade.portal.service.Manager;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eyeieye.melody.web.url.URLBroker;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

@Controller
@RequestMapping("contract")
public class ContractTransferCalcController {
	
	@Autowired
	private URLBroker infoServer;

	@RequestMapping(value="/calc",method = RequestMethod.GET)
	public double calc (String originalOil,int originalYear, int originalMonth, String  conversionOil, int conversionYear, int conversionMonth){
		//历史数据的访问路径127.0.0.1:8080/info/123
		double difference = 0;
		Manager manager = new Manager();
		//TDOD 远程json调用getContract得到合约对象
		if(originalOil.equals(conversionOil)) {
			System.out.println("same type");
			difference = manager.differenceOfOil(originalOil, originalYear, originalMonth, conversionYear, conversionMonth);
		} else{
			System.out.println("diff type");
			difference += manager.conversionPrice(originalOil, conversionOil, originalYear, originalMonth);
			difference += manager.differenceOfOil(conversionOil, originalYear, originalMonth, conversionYear, conversionMonth);
		}
		return difference;
	}

	@RequestMapping("test")
	public void test() {
		String originalUrl = infoServer.get("1.json").toString();
		System.out.print(originalUrl);
	}


	/*@RequestMapping("/xxxx/getPrice.json")
	public Contract getContract(String contractUrl, int year, int month){
		Contract contract = new Contract();
		try {
			URL url = new URL(contractUrl);
			String content= FileUtils.readFileToString(new File(url.getFile()),"UTF-8");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contract;
	}*/
	public static void main(String[] args) {
		ContractTransferCalcController contractTransferCalcController = new ContractTransferCalcController();
		/*double d = contractTransferCalcController.calc("Dubai",2011,2,"Dubai", 2011,4);
		System.out.println(d);*/
		contractTransferCalcController.test();
	}
}
