package com.example.recognition.mapper;

import com.example.recognition.entity.RegionSingleEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RegionSingleMapper {

    /**
     * 国内地区
     * @return
     */
    List<RegionSingleEntity> selectAllForCN();

    /**
     * 国际地区
     * @return
     */
    List<RegionSingleEntity> selectAllForFN();

    String queryToCountry(long id);
}
