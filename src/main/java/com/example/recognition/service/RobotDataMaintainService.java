package com.example.recognition.service;

import com.example.recognition.entity.RegionEntity;

import java.util.List;

public interface RobotDataMaintainService {

    List<RegionEntity> selectAll();

    List<String> getResult(List<Long> paramList);
}
