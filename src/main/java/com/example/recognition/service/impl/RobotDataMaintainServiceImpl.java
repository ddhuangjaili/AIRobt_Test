package com.example.recognition.service.impl;

import com.example.recognition.entity.RegionEntity;
import com.example.recognition.mapper.RegionMapper;
import com.example.recognition.service.RobotDataMaintainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class RobotDataMaintainServiceImpl implements RobotDataMaintainService {

    @Resource
    RegionMapper regionMapper;

    @Override
    public List<RegionEntity> selectAll() {
        return regionMapper.selectAll();
    }
}
