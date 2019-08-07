package com.example.recognition.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Perry on 2019/5/30.
 * 基础工具类
 */
public class BaseUtil {
    private static final Logger logger = LoggerFactory.getLogger(BaseUtil.class);

    /**
     * String 非空判断(正向)  fasle：非空；true：空
     * @param str
     * @return
     */
    public static boolean stringNull(String str){
        return str == null ? true : ((str.length() == 0 || str == "") ? true : false);
    }

    /**
     * String 非空判断(反向)  fasle：空；true：非空
     * @param str
     * @return
     */
    public static boolean stringNotNull(String str){
        return !stringNull(str);
    }

    /**
     * 获取当前操作系统类型
     * true :linux  ||  false : windows
     * @return
     */
    public static boolean getSystemName(){
        String os = System.getProperty("os.name");
        logger.info("================================当前系统："+os+" ================================");
        return os.startsWith("win") || os.startsWith("Win") ? false : true;
    }

    /**
     * 判断String串是否能转化成jsonObject
     * @param arg
     * @return
     */
    public static boolean isJsonObject(String arg){
        if (stringNull(arg.trim()))
            return false;
        try {
            JSONObject.parseObject(arg);
            logger.info("json格式正确!");
            return true;
        } catch (Exception e){
            logger.error("json格式错误!");
            return false;
        }
    }

    /**
     * 判断String串是否能转化成JSON数组
     * @param arg
     * @return
     */
    public static boolean isJsonArray(String arg){
        if (stringNull(arg.trim()))
            return false;
        try {
            JSONArray.parseArray(arg);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
