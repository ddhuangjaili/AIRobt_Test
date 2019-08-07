package com.example.recognition.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.recognition.entity.RegionEntity;
import com.example.recognition.model.RobotResultVo;
import com.example.recognition.service.RobotDataMaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/robot")
public class RobotController {

    @Autowired
    RobotDataMaintainService robotDataMaintainService;

    @RequestMapping("/test")
    public String index(){
        return "云小蜜接口";
    }

    @RequestMapping ("/query")
    public String query(){
        List<RegionEntity> regionEntities = robotDataMaintainService.selectAll();
        List<RobotResultVo> list = new ArrayList<>();
        RobotResultVo vo;
        for (RegionEntity entity : regionEntities){
            vo = new RobotResultVo(
                    entity.getId(),
                    entity.getParentSign(),
                    entity.getCountryName(),
                    entity.getCityName(),
                    entity.getStatus());
            list.add(vo);
        }

        return JSONObject.toJSONString(list);
    }

}
