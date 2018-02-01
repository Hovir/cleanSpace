package com.godfkc.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/31
 * @description
 */
public class JsonUtils {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转json
     * @param object
     * @return
     */
    public static String Object2Json(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T Json2Object(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json转map
     * @param json
     * @return
     */
    public static Map<String, Object> JsonToMap(String json){
        try{
            return objectMapper.readValue(json, new TypeReference<HashMap<String,Object>>(){});
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
