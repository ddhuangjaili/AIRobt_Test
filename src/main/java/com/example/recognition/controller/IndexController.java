package com.example.recognition.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.recognition.ConfigBeanValue;
import com.example.recognition.client.HttpClient;
import com.example.recognition.model.JsonParam;
import com.example.recognition.model.RequestParam;
import com.example.recognition.utils.BaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/25.
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    ConfigBeanValue config;

    @RequestMapping("/test")
    public String index(){
        return "hello world!";
    }

    @RequestMapping(value = "/ocr", method = RequestMethod.POST)
    public String readPicWords(@RequestBody RequestParam request){
        String imageBase64 = "";
        String result = "";
        Map<String,Object> paramMap = new HashMap<>();
        JsonParam jp = new JsonParam();

        imageBase64 = request.getImageBase64();
        if (BaseUtil.stringNotNull(imageBase64)){
            //base64
            jp.setImgString(imageBase64);
            paramMap.put("param", JSONObject.toJSONString(jp));
            try {
                result = HttpClient.doPost(config.orc_url, paramMap);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                logger.error("http Head设置异常!");
            }


        } else {
            //文件


        }

        return BaseUtil.stringNull(result) ? "no words!" : result;
    }
}
