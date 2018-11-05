package com.sinochem.crude.trade.shipping.service.impl;

import com.sinochem.crude.trade.shipping.constant.WeChatInfo;
import com.sinochem.crude.trade.shipping.service.WechatMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author WGP
 * @descriptioncrude-trade-product_oil
 * @date 2018/4/13
 **/
@Service
public class WechatMessageServiceImpl implements WechatMessageService {
    private  final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HashMap<String,String> templateMap = new HashMap<>(32);
    public  WechatMessageServiceImpl(){
        Class<WeChatInfo> clazz = WeChatInfo.class;
        Field[] fields = clazz.getFields();
        int len = fields.length;
        try{
            for (Field field:fields) {
                this.templateMap.put(field.getName(),field.get(clazz).toString());
            }
        }catch (Exception e){
            logger.error(clazz.getCanonicalName() + "WechatMessageServiceImpl初始化失败");
        }

    }
    @Override
    public String getTemplateContent(String key) {
        String templeteContent = this.templateMap.get(key);
        return templeteContent;
    }


}
