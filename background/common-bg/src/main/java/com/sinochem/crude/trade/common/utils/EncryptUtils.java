/*
 * Copyright (c) 2016. Runyi Co., Ltd. All rights reserved.
 */

package com.sinochem.crude.trade.common.utils;

import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by HuangWj on 16/8/4.
 * 编码工具类
 */
public final class EncryptUtils {

    private static final Log log = LogFactory.getLog(EncryptUtils.class);

    private static final Base64 base64 = new Base64(0, null, true);

    /**
     * md5编码
     * @param source 原始数据
     * @return 编码后数据
     */
    public static String md5Encrypt(String source) {
        return encrypt(source, "MD5");
    }

    /**
     * sha256编码
     * @param source 原始数据
     * @return 编码后数据
     */
    public static String sha256Encrypt(String source) {
        return encrypt(source, "SHA-256");
    }

    /**
     * sha512编码
     * @param source 原始数据
     * @return 编码后数据
     */
    public static String sha512Encrypt(String source) {
        return encrypt(source, "SHA-512");
    }

    /**
     * HMACSHA256编码
     * @param source 原始数据
     * @param secret 秘钥
     * @param isBase64 是否返回base64编码后数据
     * @return 编码后数据
     */
    public static String hmacSha256Encrypt(String source, String secret, boolean isBase64) {
        String result;
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);
            byte[] bytes = mac.doFinal(source.getBytes());
            if (isBase64) {
                result = base64.encodeAsString(bytes);
            } else {
                result = HexUtils.bytesToHexString(bytes);
            }
        } catch (NoSuchAlgorithmException e) {
            result = "HmacSHA256 encoder is not supported.";
            log.error(result, e);
            throw new Error(result, e);
        } catch (InvalidKeyException e) {
            result = "HmacSHA256 key is not supported.";
            log.error(result, e);
            throw new Error(result, e);
        }
        return result;
    }

    /**
     * base64编码
     * @param source 原始数据
     * @return 编码后数据
     */
    public static String base64Encrypt(String source) {
        return base64.encodeAsString(source.getBytes(Charsets.UTF_8));
    }

    /**
     * base64编码
     * @param source 原始数据字节数组
     * @return 编码后的数据
     */
    public static String base64Encrypt(byte[] source) {
        return base64.encodeAsString(source);
    }

    /**
     * base64解码
     * @param origin 原始数据
     * @return 解码后数据
     */
    public static String base64Decode(String origin) {
        return new String(base64.decode(origin));
    }

    /**
     * base64解码
     * @param origin 原始数据
     * @return 解码后数据字节数组
     */
    public static byte[] base64DecodeBytes(String origin) {
        return base64.decode(origin);
    }

    /**
     * 生成RSA加密公钥/私钥对
     * @return 公钥/私钥对
     */
    public static KeyResolver generateRSAKeyPair() {
        return new SimpleKeyResolver(generateKeyPair("RSA"));
    }

    // RSA加解密byte数组部分

    /**
     * RSA公钥加密 - 字节版
     * 加密的最终处理方法
     * @param source 原始数据字节数组
     * @param publicKey 秘钥对象
     * @return 加密结果
     */
    public static byte[] rsaEncrypt(byte[] source, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            if (source.length > 117) {
                byte[] dataReturn = null;
                for (int i = 0; i < source.length; i += 100) {
                    byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(source, i,
                            i + 100));
                    dataReturn = ArrayUtils.addAll(dataReturn, doFinal);
                }
                return dataReturn;
            }
            return cipher.doFinal(source);
        } catch (GeneralSecurityException e) {
            String warn = "RSA encode error";
            log.error(warn, e);
            return new byte[0];
        }
    }

    /**
     * RSA公钥加密 - 字节版
     * @param source 原始数据字节数组
     * @param publicKey 秘钥字符串
     * @return 加密结果
     */
    public static byte[] rsaEncrypt(byte[] source, String publicKey) throws InvalidKeySpecException {
        return rsaEncrypt(source, readPublicKey(publicKey));
    }

    /**
     * RSA公钥加密 - 字符串版
     * @param source 原始数据字节数组
     * @param publicKey 秘钥对象
     * @return 加密结果
     */
    public static String rsaEncryptToString(byte[] source, PublicKey publicKey) {
        return base64Encrypt(rsaEncrypt(source, publicKey));
    }

    /**
     * RSA公钥加密 - 字符串版
     * @param source 原始数据字节数组
     * @param publicKey 秘钥字符串
     * @return 加密结果
     */
    public static String rsaEncryptToString(byte[] source, String publicKey) throws InvalidKeySpecException {
        return rsaEncryptToString(source, readPublicKey(publicKey));
    }

    /**
     * RSA私钥解密 - 字节版
     * 解密的最终处理方法
     * @param source 原始数据字节数组
     * @param privateKey 私钥对象
     * @return 解密结果
     */
    public static byte[] rsaDecrypt(byte[] source, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            if (source.length > 128) {
                byte[] dataReturn = null;
                for (int i = 0; i < source.length; i += 128) {
                    byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(source, i,
                            i + 128));
                    dataReturn = ArrayUtils.addAll(dataReturn, doFinal);
                }
                return dataReturn;
            }
            return cipher.doFinal(source);
        } catch (GeneralSecurityException e) {
            String warn = "RSA decode error";
            log.error(warn, e);
            return new byte[0];
        }
    }

    /**
     * RSA私钥解密 - 字节版
     * @param source 原始数据字节数组
     * @param privateKey 私钥字符串
     * @return 解密结果
     */
    public static byte[] rsaDecrypt(byte[] source, String privateKey) throws InvalidKeySpecException {
        return rsaDecrypt(source, readPrivateKey(privateKey));
    }

    /**
     * RSA私钥解密 - 字节版
     * 解密的最终处理方法
     * @param source 原始数据字节数组
     * @param privateKey 私钥对象
     * @return 解密结果
     */
    public static String rsaDecryptToString(byte[] source, PrivateKey privateKey) {
        byte[] bytes = rsaDecrypt(source, privateKey);
        if (null == bytes || bytes.length == 0) {
            return "";
        }
        return new String(bytes, Charsets.UTF_8);
    }

    /**
     * RSA私钥解密 - 字节版
     * @param source 原始数据字节数组
     * @param privateKey 私钥字符串
     * @return 解密结果
     */
    public static String rsaDecryptToString(byte[] source, String privateKey) throws InvalidKeySpecException {
        return rsaDecryptToString(source, readPrivateKey(privateKey));
    }

    // RSA加解密字符串部分

    /**
     * RSA公钥加密 - 字节版
     * @param source 原始数据字节数组
     * @param publicKey 秘钥对象
     * @return 加密结果
     */
    public static byte[] rsaEncrypt(String source, PublicKey publicKey) {
        return rsaEncrypt(source.getBytes(Charsets.UTF_8), publicKey);
    }

    /**
     * RSA公钥加密 - 字节版
     * @param source 原始数据字节数组
     * @param publicKey 秘钥字符串
     * @return 加密结果
     */
    public static byte[] rsaEncrypt(String source, String publicKey) throws InvalidKeySpecException {
        return rsaEncrypt(source, readPublicKey(publicKey));
    }

    /**
     * RSA公钥加密 - 字符串版
     * @param source 原始数据字符串
     * @param publicKey 秘钥对象
     * @return 加密结果
     */
    public static String rsaEncryptToString(String source, PublicKey publicKey) {
        return base64Encrypt(rsaEncrypt(source, publicKey));
    }

    /**
     * RSA公钥加密 - 字符串版
     * @param source 原始数据字符串
     * @param publicKey 秘钥字符串
     * @return 加密结果
     */
    public static String rsaEncryptToString(String source, String publicKey) throws InvalidKeySpecException {
        return rsaEncryptToString(source, readPublicKey(publicKey));
    }

    /**
     * RSA私钥解密 - 字符串版
     * @param source 原始数据
     * @param privateKey 私钥对象
     * @return 解密结果
     */
    public static byte[] rsaDecrypt(String source, PrivateKey privateKey) {
        return rsaDecrypt(base64.decode(source.getBytes(Charsets.UTF_8)), privateKey);
    }

    /**
     * RSA私钥解密 - 字符串版
     * @param source 原始数据
     * @param privateKey 私钥字符串
     * @return 解密结果
     */
    public static byte[] rsaDecrypt(String source, String privateKey) throws InvalidKeySpecException {
        return rsaDecrypt(base64.decode(source.getBytes(Charsets.UTF_8)), privateKey);
    }

    /**
     * RSA私钥解密 - 字节版
     * 解密的最终处理方法
     * @param source 原始数据字节数组
     * @param privateKey 私钥对象
     * @return 解密结果
     */
    public static String rsaDecryptToString(String source, PrivateKey privateKey) {
        byte[] bytes = rsaDecrypt(source, privateKey);
        if (null == bytes || bytes.length == 0) {
            return "";
        }
        return new String(bytes, Charsets.UTF_8);
    }

    /**
     * RSA私钥解密 - 字节版
     * @param source 原始数据字节数组
     * @param privateKey 私钥字符串
     * @return 解密结果
     */
    public static String rsaDecryptToString(String source, String privateKey) throws InvalidKeySpecException {
        return rsaDecryptToString(source, readPrivateKey(privateKey));
    }

    // 散列算法加密方法
    private static String encrypt(String source, String algorithm) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            String warn = algorithm + " encoder is not supported.";
            log.error(warn, e);
            throw new Error(warn, e);
        }

        //加密后的字符
        byte[] result = digest.digest(source.getBytes(Charsets.UTF_8));
        return HexUtils.bytesToHexString(result);
    }

    // 非对称加密算法公钥/私钥对生成
    private static KeyPair generateKeyPair(String algorithm) {
        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            String warn = algorithm + " encoder is not supported.";
            log.error(warn, e);
            throw new Error(warn, e);
        }
        SecureRandom random = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes());
        generator.initialize(1024, random);
        return generator.generateKeyPair();
    }

    /**
     * 密钥处理器接口
     */
    public interface KeyResolver {
        String getPublicKey();
        String getPrivateKey();
        KeyPair getKeyPair();
        void readKeys(String publicKey, String privateKey) throws InvalidKeySpecException;
    }

    // 秘钥处理
    private static class SimpleKeyResolver implements KeyResolver {

        private KeyPair keyPair;

        SimpleKeyResolver(KeyPair keyPair) {
            this.keyPair = keyPair;
        }

        @Override
        public String getPublicKey() {
            byte[] encoded = keyPair.getPublic().getEncoded();
            return base64Encrypt(encoded);
        }

        @Override
        public String getPrivateKey() {
            byte[] encoded = keyPair.getPrivate().getEncoded();
            return base64Encrypt(encoded);
        }

        @Override
        public KeyPair getKeyPair() {
            return keyPair;
        }

        @Override
        public void readKeys(String publicKey, String privateKey) throws InvalidKeySpecException {
            this.keyPair = new KeyPair(readPublicKey(publicKey), readPrivateKey(privateKey));
        }
    }

    // 从字符串中生成公钥对象
    private static PublicKey readPublicKey(String publicKey) throws InvalidKeySpecException {
        byte[] publicKeyBytes = base64DecodeBytes(publicKey);
        X509EncodedKeySpec specPublic = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory kf;
        try {
            kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(specPublic);
        } catch (NoSuchAlgorithmException e) {
            String warn = "RSA encoder is not supported.";
            log.error(warn, e);
            throw new Error(warn, e);
        } catch (InvalidKeySpecException e) {
            String warn = "KeySpec is not supported.";
            log.error(warn, e);
            throw new InvalidKeySpecException(e);
        }
    }

    // 从字符串中生成私钥对象
    private static PrivateKey readPrivateKey(String privateKey) throws InvalidKeySpecException {
        byte[] privateKeyBytes = base64DecodeBytes(privateKey);
        PKCS8EncodedKeySpec specPrivate =new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory kf;
        try {
            kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(specPrivate);
        } catch (NoSuchAlgorithmException e) {
            String warn = "RSA encoder is not supported.";
            log.error(warn, e);
            throw new Error(warn, e);
        } catch (InvalidKeySpecException e) {
            String warn = "KeySpec is not supported.";
            log.error(warn, e);
            throw new InvalidKeySpecException(e);
        }
    }

}
