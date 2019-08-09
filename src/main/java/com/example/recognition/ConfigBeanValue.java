package com.example.recognition;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Perry on 2019/6/21.
 * properties文件配置项
 */
@Component
public class ConfigBeanValue {
    /*视频存放地址*/
    @Value("${video_path}")
    public String video_path;

    /*图片存放地址*/
    @Value("${image_path}")
    public String image_path;

    /*python脚本地址*/
    @Value("${python_path}")
    public String python_path;

    /*模型加载服务*/
    @Value("${server_path}")
    public String server_path;

    /*追踪器开关*/
    @Value("${tracker_flag}")
    public String tracker_flag;

    @Value("${ocr_url}")
    public String orc_url;
}
