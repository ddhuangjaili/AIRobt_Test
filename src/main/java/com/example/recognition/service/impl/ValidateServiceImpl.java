package com.example.recognition.service.impl;

import com.example.recognition.ConfigBeanValue;
import com.example.recognition.service.ValidateService;
import com.example.recognition.utils.PythonExeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Perry on 2019/6/21.
 */
@Service
public class ValidateServiceImpl implements ValidateService {
    private static final Logger logger = LoggerFactory.getLogger(ValidateServiceImpl.class);
    private static String resultStr = "";

    @Autowired
    PythonExeUtil pyUtil;
    @Autowired
    ConfigBeanValue config;

    @Override
    public String checkVideoForEffective(String path, String flag) {
        logger.info("开始执行检测......");
        long beginTime = System.currentTimeMillis();
        String result = pyUtil.pythonResult(path, flag);
        logger.info("模型检测耗时:{}s",(System.currentTimeMillis()-beginTime)/1000);
        return result;
    }

}
