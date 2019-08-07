package com.example.recognition.utils;

import com.example.recognition.ConfigBeanValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by Perry on 2019/6/21.
 */
@Component
public class PythonExeUtil {
    private static final Logger logger = LoggerFactory.getLogger(PythonExeUtil.class);
    private static String resultStr = "";

    @Autowired
    ConfigBeanValue config;

    /**
     * python脚本执行程序
     * @param param
     * @return
     */
    public String pythonResult(String param, String tracker_flag){
        if (BaseUtil.stringNotNull(resultStr))
            resultStr = "";
        logger.info("执行python脚本...");
        String[] args = new String[]{"python", config.python_path, param, tracker_flag};
        logger.info("python请求参数:" + Arrays.toString(args));

        try {
            final Process process = Runtime.getRuntime().exec(args);

            new Thread(){
                @Override
                public void run(){
                    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = null;

                    try {
                        while ((line = in.readLine()) != null){
                            logger.info("输出.........."+line+"..........");
                            resultStr += line;
                        }
                    } catch (IOException e) {
                        logger.error("", e.getMessage());
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            logger.error("", e.getMessage());
                        }
                    }
                }
            }.start();

            new Thread(){
                @Override
                public void run(){
                    BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    String line = null;
                    try {
                        while ((line = err.readLine()) != null){
                            logger.error("错误---------------" + line + "---------------");
                        }
                    } catch (IOException e) {
                        logger.error("", e.getMessage());
                    } finally {
                        try {
                            err.close();
                        } catch (IOException e) {
                            logger.error("", e.getMessage());
                        }
                    }
                }
            }.start();


            int flag = process.waitFor();
            logger.info("进程状态值:" + flag);
            if (flag > 0 && process != null){
                process.destroy();
            }
        } catch (IOException e) {
            logger.error("执行python脚本:【{}】出现错误!",config.python_path);
            logger.error("", e.getMessage());
        } catch (InterruptedException e) {
            logger.error("挂起进程失败!");
            logger.error("", e.getMessage());
        }

        logger.info("python脚本执行完毕...");
        logger.info("返回结果:"+resultStr);
        return resultStr;
    }
}
