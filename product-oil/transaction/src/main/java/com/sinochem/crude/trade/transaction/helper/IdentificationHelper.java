package com.sinochem.crude.trade.transaction.helper;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 用以生成编号，uuid等唯一标识
 */
@Component
public class IdentificationHelper {

    public String generateUuid() {
        return UUID.randomUUID().toString();
    }

    public String generateSerialNumber() {

        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        // 格式化日期
        return format.format(new Date());
    }
}
