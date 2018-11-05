package com.sinochem.crude.trade.listed.service;

import com.sinochem.crude.trade.listed.model.design.base.OilModel;
import com.sinochem.crude.trade.listed.model.vo.SymbolVO;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * 实时转价功能的服务
 * @author Yichen Zhao
 * date: 20180202
 */
public interface OilPriceConvertionService {

    /**
     * 计算油种转价结果
     */
    public BigDecimal getOilPriceConvertion(OilModel origin, OilModel target) throws Exception;

    /**
     * 获取资讯合约时间列表
     */
    public List<Calendar> getSymbolDateListByOilType(String oilType) throws Exception;

    /**
     * 获取目标年月DUBAI价格
     */
    public BigDecimal getDubaiSymbolPrice(Calendar targetCalendar) throws Exception;
}
