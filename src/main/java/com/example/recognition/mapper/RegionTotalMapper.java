package com.example.recognition.mapper;

import com.example.recognition.entity.RegionTotalEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RegionTotalMapper {

    List<RegionTotalEntity> selectAll();

    Integer selectCount(long id);
}
