package com.example.recognition.mapper;

import com.example.recognition.entity.RegionEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RegionMapper {

    List<RegionEntity> selectAll();
}
