package com.sinochem.crude.trade.transaction.model.vo;

import com.sinochem.crude.trade.common.model.vo.DictionaryVO;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: fengzk
 * @CreateDate: 2018/7/13 11:13
 * @Version: [v1.0]
 */
public class ContractSheetPullDownVO {
    private List<DictionaryVO> category;
    private List<DictionaryVO> status;
    private List<DictionaryVO> tradeTerms;

    public List<DictionaryVO> getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(List<DictionaryVO> dischargePort) {
        this.dischargePort = dischargePort;
    }

    private List<DictionaryVO> dischargePort;

    public List<DictionaryVO> getCategory() {
        return category;
    }

    public void setCategory(List<DictionaryVO> category) {
        this.category = category;
    }

    public List<DictionaryVO> getStatus() {
        return status;
    }

    public void setStatus(List<DictionaryVO> status) {
        this.status = status;
    }

    public List<DictionaryVO> getTradeTerms() {
        return tradeTerms;
    }

    public void setTradeTerms(List<DictionaryVO> tradeTerms) {
        this.tradeTerms = tradeTerms;
    }
}
