package com.example.recognition.service.impl;

import com.example.recognition.entity.CompensationEntity;
import com.example.recognition.entity.RegionEntity;
import com.example.recognition.entity.RegionSingleEntity;
import com.example.recognition.entity.RegionTotalEntity;
import com.example.recognition.mapper.CompensationMapper;
import com.example.recognition.mapper.RegionMapper;
import com.example.recognition.mapper.RegionSingleMapper;
import com.example.recognition.mapper.RegionTotalMapper;
import com.example.recognition.model.ResultMessage;
import com.example.recognition.service.RobotDataMaintainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RobotDataMaintainServiceImpl implements RobotDataMaintainService {

    private boolean flag = false;

    @Resource
    RegionMapper regionMapper;

    @Resource
    RegionTotalMapper regionTotalMapper;

    @Resource
    CompensationMapper compensationMapper;

    @Resource
    RegionSingleMapper regionSingleMapper;

    @Override
    public List<RegionEntity> selectAll() {
        return regionMapper.selectAll();
    }

    @Override
    public List<String> getResult(List<Long> paramList) {
        paramList = removeToNewList(paramList);
        return resultStr(paramList);
    }

    private List<String> resultStr(List<Long> list){
        List<String> resultList = new ArrayList<>();

        if (flag){
            //根据父ID查所有包含的地区
            for (long id : list){
                String countryName = regionTotalMapper.queryCountryById(id);
                List<RegionTotalEntity> entities = regionTotalMapper.queryCityByPid(id);
                List<String> cityList = new ArrayList<>();
                for (RegionTotalEntity entity :entities){
                    cityList.add(entity.getRegName());
                }

                //String result = countryName + ":" + cityList.toString();
                String result = countryName + ":"
                        + ResultMessage.JSON_FIRST_WORDS.getMessage()
                        + cityList.toString().replace("[","【").replace("]","】")
                        + ResultMessage.JSON_LAST_WORDS.getMessage();
                resultList.add(result);
            }

            flag = false;
        } else {
            for (long id : list) {
                CompensationEntity com = compensationMapper.queryContent(id);
                resultList.add(com.getRegPid() == 0 ? "" : (getCountryName(com.getRegPid()) + "-") + com.getRegName() + ":" + com.getContent());
            }
        }

        return resultList;
    }

    /**
     * 筛选id
     * @param list
     * @return
     */
    private List<Long> removeToNewList(List<Long> list){
        List<Long> removeList = new ArrayList<>();
        for (long lg :list){
            int i = regionTotalMapper.selectCount(lg);
            if (i == 1){
                removeList.add(lg);
            } else {
                int er = regionTotalMapper.effectiveQuery(lg);
                if (er == 0) {
                    removeList.add(lg);
                }
            }
        }

        if (removeList.size() < list.size()) {
            list.removeAll(removeList);
        } else {
            flag = true;
        }
        return list;
    }

    private String getCountryName(long id){
        return regionSingleMapper.queryToCountry(id);
    }
}
