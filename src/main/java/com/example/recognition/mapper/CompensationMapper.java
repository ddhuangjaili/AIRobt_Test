package com.example.recognition.mapper;

import com.example.recognition.entity.CompensationEntity;
import org.springframework.stereotype.Component;

@Component
public interface CompensationMapper {

    CompensationEntity queryContent(long id);
}
