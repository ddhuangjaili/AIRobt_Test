package com.example.recognition.service;

/**
 * Created by Administrator on 2019/6/21.
 */
public interface ValidateService {

    /**
     * 检测视频有效性
     * @return
     */
    String checkVideoForEffective(String path, String flag);

}
