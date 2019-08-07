package com.example.recognition.entity;

import lombok.Getter;
import lombok.Setter;

public class RegionEntity {

    private String id;

    private String parentSign;

    private String countryName;

    private String cityName;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentSign() {
        return parentSign;
    }

    public void setParentSign(String parentSign) {
        this.parentSign = parentSign;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
