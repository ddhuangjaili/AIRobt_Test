package com.example.recognition.model;

import java.util.List;

public class RegionTotalArrayVo {
    private String countryName;

    private List<String> cityNames;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<String> getCityNames() {
        return cityNames;
    }

    public void setCityNames(List<String> cityNames) {
        this.cityNames = cityNames;
    }
}
