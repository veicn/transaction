package com.sinochem.crude.trade.listed.enums;

import com.sinochem.crude.trade.listed.model.vo.DictionaryVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 贸易链创建时间枚举
 * @author Yichen Zhao
 * date： 20180420
 */
public enum TradeChainPublishTimeEnum {

    THREE_MONTHS_BEFORE(3, "3个月内", "within 3 months"),
    SIX_MONTHS_BEFORE(6, "6个月内", "within 6 months"),
    TEWLVE_MONTHS_BEFORE(12, "1年内", "within 1 year");

    private Integer monthsBefore;

    private String zhName;

    private String enName;

    TradeChainPublishTimeEnum(Integer monthsBefore, String zhName, String enName) {
        this.monthsBefore = monthsBefore;
        this.zhName = zhName;
        this.enName = enName;
    }

    public static List<DictionaryVO> getTradeChainPublishTimeOptions() {
        List<DictionaryVO> tradeChainPublishTimeOptionList = new ArrayList<>();

        for (TradeChainPublishTimeEnum tradeChainPublishTimeEnum : TradeChainPublishTimeEnum.values()) {
            DictionaryVO dictionaryVO = new DictionaryVO(
                    tradeChainPublishTimeEnum.monthsBefore,
                    tradeChainPublishTimeEnum.zhName,
                    tradeChainPublishTimeEnum.enName
            );

            tradeChainPublishTimeOptionList.add(dictionaryVO);
        }

        return tradeChainPublishTimeOptionList;
    }

    /** getters */
    public Integer getMonthsBefore() {
        return monthsBefore;
    }

    public String getZhName() {
        return zhName;
    }

    public String getEnName() {
        return enName;
    }
}
