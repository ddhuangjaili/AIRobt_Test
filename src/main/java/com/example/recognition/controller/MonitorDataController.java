package com.example.recognition.controller;

import com.example.recognition.ConfigBeanValue;
import com.example.recognition.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 上交所运维监控数据管道——人工坐席
 */
@RestController
@RequestMapping("/monitor")
public class MonitorDataController {

    @Autowired
    ConfigBeanValue config;

    /**
     * 注册服务器数据
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public String regChannel(){
        return getString("/conf/rest/register/monitor");
    }

    private String getString(String s) {
        String result = "";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("action", s);
        paramMap.put("data","{}");
        paramMap.put("sessionId",config.session_id);
        paramMap.put("action_id",config.action_id);

        try {
            result = HttpClient.doPost(config.monitor_data_url, paramMap);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 媒体服务器数据
     * @return
     */
    @RequestMapping(value = "/switch", method = RequestMethod.POST, produces = "application/json")
    public String switchChannel(){
        return getString("/conf/rest/switch/monitor");
    }

    /**
     * 应用服务器数据
     * @return
     */
    @RequestMapping(value = "/app", method = RequestMethod.POST, produces = "application/json")
    public String appChannel(@RequestParam("startPage") String startPage){
        String result = "";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("action","/conf/rest/appserver/monitor");
        //paramMap.put("data","{\"startPage\":0,\"pageNum\":10,\"instance_ip\":\"\",\"instance_type\":\"-1\"}");
        paramMap.put("data","{\"startPage\":"+startPage+",\"pageNum\":10,\"instance_ip\":\"\",\"instance_type\":\"-1\"}");
        paramMap.put("sessionId","s95E2B128A732674E6EDE16648E2862C5-7");
        paramMap.put("action_id","0.3666080908604674");

        try {
            result = HttpClient.doPost(config.monitor_data_url, paramMap);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 告警详情数据
     * @return
     */
    @RequestMapping(value = "/alarm", method = RequestMethod.POST, produces = "application/json")
    public String alarmChannel(@RequestParam("startPage") String startPage){
        String result = "";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("action","/conf/rest/alarm/list");
        //paramMap.put("data","{\"startPage\":0,\"pageNum\":10,\"alarmLevel\":\"\",\"startTime\":\"\",\"endTime\":\"\"}");
        paramMap.put("data","{\"startPage\":"+startPage+",\"pageNum\":10,\"alarmLevel\":\"\",\"startTime\":\"\",\"endTime\":\"\"}");
        paramMap.put("sessionId","s95E2B128A732674E6EDE16648E2862C5-7");
        paramMap.put("action_id","0.3666080908604674");

        try {
            result = HttpClient.doPost(config.monitor_data_url, paramMap);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }
}
