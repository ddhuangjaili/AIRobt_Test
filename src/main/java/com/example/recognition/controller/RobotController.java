package com.example.recognition.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.recognition.entity.RegionEntity;
import com.example.recognition.model.ApiResultVo;
import com.example.recognition.model.ResultMessage;
import com.example.recognition.model.RobotResultVo;
import com.example.recognition.service.RobotDataMaintainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/robot")
//@Api(tags = "出差")
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

    @RequestMapping(value = "/queryByRegId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    //@ApiOperation("查询")
    public String queryToContentByRegId(@RequestBody String jsonIds){
        List<String> result = new ArrayList<>();
        Map map;
        ApiResultVo resultVo = null;
        try {
            map = JSONObject.parseObject(jsonIds, Map.class);

            if (!map.isEmpty()) {
                Iterator iterator = map.entrySet().iterator();
                List<String> paramList = new ArrayList<>();
                while (iterator.hasNext()) {
                    Map.Entry next = (Map.Entry) iterator.next();
                    paramList = (List<String>) next.getValue();
                }

                if (paramList.size() > 0) {
                    List<Long> parm = new ArrayList<>();
                    for (String arg : paramList) {
                        parm.add(Long.parseLong(arg));
                    }

                    result = robotDataMaintainService.getResult(parm);
                }

                resultVo = result.size() == 0 ?
                        new ApiResultVo(ResultMessage.ROBOT_RESULT_EMPTY.getCode(),
                                result,
                                ResultMessage.ROBOT_RESULT_EMPTY.getMessage()) :

                        new ApiResultVo(ResultMessage.ROBOT_RESULT_SUCCESS.getCode(),
                                result,
                                ResultMessage.ROBOT_RESULT_SUCCESS.getMessage());

            } else {
                logger.info(ResultMessage.ROBOT_JSONPARAM_EMPTY.getMessage()+ ",【{}】",jsonIds);
                resultVo = new ApiResultVo(ResultMessage.ROBOT_JSONPARAM_EMPTY.getCode(),
                        result,
                        ResultMessage.ROBOT_JSONPARAM_EMPTY.getMessage());
            }

        } catch (Exception e){
            logger.error(ResultMessage.ROBOT_JSONPARAM_ERROR.getMessage() + ",【{}】",jsonIds);
            resultVo = new ApiResultVo(ResultMessage.ROBOT_JSONPARAM_ERROR.getCode(),
                    result,
                    ResultMessage.ROBOT_JSONPARAM_ERROR.getMessage());
        } finally {
            return JSONObject.toJSONString(resultVo);
        }

    }

}
