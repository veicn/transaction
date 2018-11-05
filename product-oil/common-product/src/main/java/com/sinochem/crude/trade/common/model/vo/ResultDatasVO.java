package com.sinochem.crude.trade.common.model.vo;

import com.sinochem.it.b2b.common.result.ResultDatas;

/**
 * 用于返回多种语言的ResultDatasVO
 * @author Yichen Zhao
 * date: 20180313
 */
public class ResultDatasVO<T> extends ResultDatas<T> {

    private DictionaryVO messageVO;

    public DictionaryVO getMessageVO() {
        return messageVO;
    }

    public void setMessageVO(DictionaryVO messageVO) {
        this.messageVO = messageVO;
    }
}
