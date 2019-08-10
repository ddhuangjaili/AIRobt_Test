package com.example.recognition.model;

import java.util.List;

public class OcrResultVo {

    private String timeTake;

    private List<DistinguishVo> res;

    public String getTimeTake() {
        return timeTake;
    }

    public void setTimeTake(String timeTake) {
        this.timeTake = timeTake;
    }

    public List<DistinguishVo> getRes() {
        return res;
    }

    public void setRes(List<DistinguishVo> res) {
        this.res = res;
    }
}
