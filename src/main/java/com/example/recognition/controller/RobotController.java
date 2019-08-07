package com.example.recognition.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.recognition.entity.RegionEntity;
import com.example.recognition.model.RobotResultVo;
import com.example.recognition.service.RobotDataMaintainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/robot")
public class RobotController {
    private static final Logger logger = LoggerFactory.getLogger(RobotController.class);

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

    @RequestMapping(value = "/queryByRegId", method = RequestMethod.GET)
    //@ApiOperation("查询")
    public String queryToContentByRegId(@RequestParam String jsonIds){
        List<String> result = new ArrayList<>();
        try {
            Map map = JSONObject.parseObject(jsonIds, Map.class);
            Iterator iterator = map.entrySet().iterator();
            List<String> paramList = new ArrayList<>();
            while (iterator.hasNext()) {
                Map.Entry next = (Map.Entry) iterator.next();
                paramList = (List<String>) next.getValue();
            }

            if (paramList.size() > 0) {
                List<Long> parm = new ArrayList<>();
                for(String arg : paramList){
                    parm.add(Long.parseLong(arg));
                }

                result = robotDataMaintainService.getResult(parm);
            }

        }catch (Exception e){
            logger.error("参数【{}】转换异常", jsonIds);
        } finally {
            return JSONObject.toJSONString(result.size() == 0 ? "" : result);
        }
    }

}
