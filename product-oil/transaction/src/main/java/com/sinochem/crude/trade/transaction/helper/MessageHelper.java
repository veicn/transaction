package com.sinochem.crude.trade.transaction.helper;

import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.transaction.enums.MessageEnum;
import org.springframework.stereotype.Component;

/**
 * 交易模块前端交互消息的helper类
 * @author Yichen Zhao
 * date: 20180309
 */
@Component
public class MessageHelper {

    /**
     * 将MessageEnum中的消息转化为VO
     */
    public DictionaryVO convertToMessageVO(MessageEnum messageEnum) {
        return new DictionaryVO(
                messageEnum.getCode(),
                messageEnum.getZhMessage(),
                messageEnum.getEnMessage()
        );
    }
}
