package com.os.osframe.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncryptUtil {

    private static final Log logger = LogFactory.getLog(FileUtil.class);
    // 将 URL 编码
    public static String urlEncode(String str) {
        String target;
        try {
            target = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将 URL 解码
    public static String urlDecode(String str) {
        String target;
        try {
            target = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 编码
    public static String encodeBase64(String str) {
        String target;
        try {
            target = Base64.encodeBase64URLSafeString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            logger.error("编码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 Base64 解码
    public static String decodeBase64(String str) {
        String target;
        try {
            target = new String(Base64.decodeBase64(str), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("解码出错！", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    // 将字符串 MD5 加密
    public static String encryptMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    // 将字符串 SHA 加密
    public static String encryptSHA(String str) {
        return DigestUtils.sha1Hex(str);
    }

    // 创建随机数
    public static String createRandom(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

}
