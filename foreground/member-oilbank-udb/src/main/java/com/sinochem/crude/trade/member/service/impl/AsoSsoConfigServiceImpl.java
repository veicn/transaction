package com.sinochem.crude.trade.member.service.impl;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.dao.TMemberAccountMapper;
import com.sinochem.crude.trade.member.domain.TMemberAccount;
import com.sinochem.crude.trade.member.service.AsoSsoConfigService;
import com.sinochem.crude.trade.member.util.Encrypt;
import com.sinochem.it.b2b.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class AsoSsoConfigServiceImpl implements AsoSsoConfigService {
    Logger logger = LoggerFactory.getLogger(AsoSsoConfigServiceImpl.class);

    @Value("${groms.service.tgs}")
    private String tgsUrl;
    @Value("${groms.service.tvs}")
    private String tvsUrl;

    @Autowired
    URLBroker appServerBroker;

    @Override
    public String getTgs(String sys) {
        return tgsUrl;
    }

    @Override
    public String getTvs(String sys) {
        return tvsUrl;
    }

    @Override
    public String getReturnUrl() {
        return appServerBroker.get("sso/aso/tvs.htm").toString();
    }

    @Override
    public String encryption(String Sys, String Time, String param) throws BizException {
        logger.info("加密明文：sys="+Sys + " time="+Time + " param="+param);
        String sign = Encrypt.md5WithSalt(Sys, Time);
        String data = Sys + ";" + Time + ";" + param;
        String key = sign.substring(0, 16);
        String iv = sign.substring(16,32);
        String encStr = null;
        try {
            encStr = Encrypt.encryptAES(data, key, iv);
            String retStr = sign + encStr;
            retStr = URLEncoder.encode(retStr, "GB18030");
            logger.info("加密密文：sys="+Sys + " time="+Time + " param="+param);
            return retStr;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            logger.error("加密失败：" + e);
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO25));
        }
    }

    @Override
    public String decrypt(String encStr) throws BizException {
        logger.info("解密密文：encStr="+encStr);
        String key = encStr.substring(0, 16);
        String iv = encStr.substring(16,32);
        String subEnStr = encStr.substring(32, encStr.length());
        //返回格式： 各参数间用分号隔开
        String retStr = null;
        try {
            retStr = Encrypt.decryptAES(subEnStr, key, iv);
            logger.info("解密明文：retStr="+retStr);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error("解密失败：" + e);
            throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO4));
        }
        return retStr;
    }

}
