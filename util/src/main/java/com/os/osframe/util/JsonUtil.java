package com.os.osframe.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

    private static final Log logger = LogFactory.getLog(FileUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 将 Java 对象转为 JSON 字符串
    public static <T> String toJson(T obj) {
        String jsonStr;
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("Java 转 JSON 出错！", e);
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    // 将 Json 字符串转为 Java 对象
    public static <T> T fromJson(String json, Class<T> type) {
        T obj;
        try {
            obj = objectMapper.readValue(json, type);
        } catch (Exception e) {
            logger.error("JSON 转 Java 出错！", e);
            throw new RuntimeException(e);
        }
        return obj;
    }
}
