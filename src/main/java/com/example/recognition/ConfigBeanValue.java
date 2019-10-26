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
    public String ocr_url;

    @Value("${ocr_img}")
    public String ocr_path;

    @Value("${img_base_url}")
    public String img_url;

    /*人工坐席监控数据接口地址*/
    @Value("${monitor_data_url}")
    public String monitor_data_url;

    /*通信标识-session_id*/
    @Value("${session_id}")
    public String session_id;

    /*通信标识-action_id*/
    @Value("${action_id}")
    public String action_id;

    /*外呼机器人监控数据接口地址*/
    @Value("${monitor_robot_url}")
    public String monitor_robot_url;

    /*环境切换开关 0:zt | 1:sjd */
    @Value("${env_flag}")
    public String env_flag;

    /*上交所服务器IP group*/
    @Value("${env_sjs}")
    public String env_sjs;

    /*证通服务器IP group*/
    @Value("${env_zt}")
    public String env_zt;
}
