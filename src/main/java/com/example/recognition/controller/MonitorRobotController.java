package com.example.recognition.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.recognition.ConfigBeanValue;
import com.example.recognition.client.HttpClient;
import com.example.recognition.model.MonitorResult;
import com.example.recognition.model.OSMessage;
import com.example.recognition.service.MonitorRobotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上交所运维监控数据管道——智能外呼
 */
@RestController
@RequestMapping("/monitor")
public class MonitorRobotController {
    private static final Logger logger = LoggerFactory.getLogger(MonitorRobotController.class);

    @Autowired
    ConfigBeanValue config;

    @Resource
    MonitorRobotService monitorRobotService;

    /**
     * 外呼机器人监控数据
     * @return
     */
    @RequestMapping(value = "/robot", method = RequestMethod.POST, produces = "application/json")
    public String robotChannel(){
        String result = "";
        Map<String, Object> paramMap = new HashMap<>();

        try {
            result = HttpClient.doPost(config.monitor_robot_url, paramMap);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;

    }

    /**
     * 机器人监控数据
     * @return
     */
    @RequestMapping(value = "/robotAlarm", method = RequestMethod.POST, produces = "application/json")
    public String robotAlarmChannel(){
        MonitorResult result = new MonitorResult();
        try {
            List<OSMessage> resultList = monitorRobotService.getResult();
            result.setCode("1");
            result.setMessage("success");
            result.setList(resultList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setCode("0");
            result.setMessage("Robot Alarm data get fail");
        }

        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/fileAlarm", method = RequestMethod.POST, produces = "application/json")
    public String fileZoomChannel(){



        return "";
    }
}
