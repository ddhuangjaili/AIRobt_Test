package com.example.recognition.model;

public class RobotResultVo {
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

    public RobotResultVo(String id, String parentSign, String countryName, String cityName, Integer status) {
        this.id = id;
        this.parentSign = parentSign;
        this.countryName = countryName;
        this.cityName = cityName;
        this.status = status;
    }
}
