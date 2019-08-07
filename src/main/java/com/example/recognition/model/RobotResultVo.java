package com.example.recognition.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RobotResultVo {
    private String id;

    private String parentSign;

    private String countryName;

    private String cityName;

    private String status;

    public RobotResultVo(String id, String parentSign, String countryName, String cityName, String status) {
        this.id = id;
        this.parentSign = parentSign;
        this.countryName = countryName;
        this.cityName = cityName;
        this.status = status;
    }
}
