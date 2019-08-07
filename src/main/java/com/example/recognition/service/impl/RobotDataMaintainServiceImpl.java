package com.example.recognition.service.impl;

import com.example.recognition.entity.CompensationEntity;
import com.example.recognition.entity.RegionEntity;
import com.example.recognition.mapper.CompensationMapper;
import com.example.recognition.mapper.RegionMapper;
import com.example.recognition.mapper.RegionTotalMapper;
import com.example.recognition.entity.RobotQAEntity;
import com.example.recognition.service.RobotDataMaintainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RobotDataMaintainServiceImpl implements RobotDataMaintainService {

    private boolean flag = false;

    @Resource
    RegionMapper regionMapper;

    @Resource
    RegionTotalMapper regionTotalMapper;

    @Resource
    CompensationMapper compensationMapper;

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



            flag = false;
        } else {
            for (long id : list) {
                CompensationEntity com = compensationMapper.queryContent(id);
                resultList.add(com.getRegName() + ":" + com.getContent());
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
            if (i>1){
                removeList.add(lg);
            }
        }

        if (removeList.size() < list.size()) {
            list.removeAll(removeList);
        } else {
            flag = true;
        }
        return list;
    }
}
