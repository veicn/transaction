package com.sinochem.crude.trade.transaction.helper;

import com.sinochem.crude.trade.common.model.vo.DictionaryVO;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHelper {

    public DictionaryVO getBizExceptionByCode(int code) {
        for (ExceptionEnum exceptionEnum : ExceptionEnum.values()) {
            if (code == exceptionEnum.getCode()) {
                DictionaryVO dictionaryVO = new DictionaryVO(
                        String.valueOf(exceptionEnum.getCode()),
                        exceptionEnum.getZhMessage(),
                        exceptionEnum.getEnMessage()
                );

                return dictionaryVO;
            }
        }

        return null;
    }

    public DictionaryVO convertToExceptionVO(ExceptionEnum exceptionEnum) {
        return new DictionaryVO(
                String.valueOf(exceptionEnum.getCode()),
                exceptionEnum.getZhMessage(),
                exceptionEnum.getEnMessage()
        );
    }
}
