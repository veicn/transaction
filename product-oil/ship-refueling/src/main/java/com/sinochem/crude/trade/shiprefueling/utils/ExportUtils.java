package com.sinochem.crude.trade.shiprefueling.utils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import java.math.BigDecimal;
import java.util.List;

public class ExportUtils {
	private static Logger log = LoggerFactory.getLogger(ExportUtils.class);

    /**
     * 船供导出标题
     * @param 
     * @return
     */
    public static void rSupplyHeadline(List<String> headList){
    	//标题
  		headList.add("订单日期");
		headList.add("订单号");
		headList.add("加油日期");
  		headList.add("供油单号");
		headList.add("卖家名称");
		headList.add("买家名称");
		headList.add("船名");
		headList.add("港口");
  		headList.add("总价");
  		headList.add("油种1");
  		headList.add("类型1");
  		headList.add("单价1");
  		headList.add("数量1");
  		headList.add("分类价格1");
		headList.add("油种2");
		headList.add("类型2");
		headList.add("单价2");
		headList.add("数量2");
		headList.add("分类价格2");
		headList.add("油种3");
		headList.add("类型3");
		headList.add("单价3");
		headList.add("数量3");
		headList.add("分类价格3");
		headList.add("油种4");
		headList.add("类型4");
		headList.add("单价4");
		headList.add("数量4");
		headList.add("分类价格4");

	}
	/**
	 * 导入标题
	 * @param
	 * @return
	 */
	public static void rIgnitionHeadline(List<String> headList){
		//标题
		headList.add("订单日期");
		headList.add("提货日期");
		headList.add("订单号");
        headList.add("提货方式");
		headList.add("运输方式");
		headList.add("买家名称");
		headList.add("卖家名称");
		//headList.add("总价");
		headList.add("油品类别1");
		headList.add("规格1");
		headList.add("单价1");
		headList.add("数量1");
        headList.add("单位1");
		//headList.add("分类价格1");
		headList.add("油品类别2");
		headList.add("规格2");
		headList.add("单价2");
		headList.add("数量2");
        headList.add("单位2");
		//headList.add("分类价格2");
		headList.add("油品类别3");
		headList.add("规格3");
		headList.add("单价3");
		headList.add("数量3");
        headList.add("单位3");
		//headList.add("分类价格3");
		headList.add("油品类别4");
		headList.add("规格4");
		headList.add("单价4");
		headList.add("数量4");
        headList.add("单位4");
		//headList.add("分类价格4");

	}
    /**
     * 保留2位有效小数
     * @param 
     * @return
     */
    public static BigDecimal reserveTwo(BigDecimal i){
    	if(i!=null){
    		i = i.setScale(2,BigDecimal.ROUND_DOWN);
    	}
    	return i;
    }
}
