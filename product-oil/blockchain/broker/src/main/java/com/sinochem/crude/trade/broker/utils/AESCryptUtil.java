package com.sinochem.crude.trade.broker.utils;

import com.sinochem.crude.trade.broker.helper.JsonUtilsHelper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Map;

/**
 * AES加密解密；
 */
@Service
public class AESCryptUtil {
    private static final String AESTYPE = "AES/ECB/PKCS5Padding";
    private static Logger logger = LoggerFactory.getLogger(AESCryptUtil.class);
    private static String key;//="FHhPatHL4DF8link";

    @Value("${aeskey}")
    public void setKey(String key) {
        this.key = key;
    }

    private static String useaes;

    @Value("${useaes}")
    public void setUseaes(String useaes) {
        this.useaes = useaes;
    }


    public static String encryptAES(String encryptData) {
        return encryptAES(encryptData, key);
    }

    /**
     * 加密
     *
     * @param plainText
     * @param keyStr
     * @return
     */
    public static String encryptAES(String plainText, String keyStr) {
        byte[] encrypt = null;
        try {
            Key key = generateKey(keyStr);
            //“算法/模式/填充”
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // encrypt = cipher.doFinal(plainText.getBytes());
            encrypt = cipher.doFinal(plainText.getBytes("utf-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(encrypt));
    }

    /**
     * 解密
     *
     * @param encryptData
     * @return
     */
    public static String decryptAES(String encryptData) {
        if ("true".equals(useaes)) {
            StringBuilder sb = new StringBuilder("[解密前：]" + encryptData);
            String str = decryptAES(encryptData, key);
            sb.append("[解密后：]" + str);
            logger.info(sb.toString());
            return str;
        } else
            return encryptData;
    }


    /**
     * 解密
     *
     * @param encryptData
     * @param keyStr
     * @return
     */
    public static String decryptAES(String encryptData, String keyStr) {
        byte[] decrypt = null;
        String str = "";
        try {
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypt = cipher.doFinal(Base64.decodeBase64(encryptData));
            str = new String(decrypt, "UTF-8").trim();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //str= UncodeUtil.getUTF8XMLString(str);
        return str;
    }

    private static Key generateKey(String key) throws Exception {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            return keySpec;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    private static String encryptAESData(String appsecret,
                                         Map<String, String> dataMap, boolean encode) {
        String data = "";
        if (encode) {
            try {
                data = URLEncoder.encode(encryptAES(JsonUtilsHelper.object2Json(dataMap), appsecret), "UTF8");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                data = encryptAES(JsonUtilsHelper.object2Json(dataMap), appsecret);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return data;
    }

    public static void main(String[] args) {


    }
}
