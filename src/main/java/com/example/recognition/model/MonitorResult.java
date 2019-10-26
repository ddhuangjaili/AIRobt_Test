package com.example.recognition.model;

import java.util.List;

public class MonitorResult {
    private String code;
    private String message;
    private List<OSMessage> list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OSMessage> getList() {
        return list;
    }

    public void setList(List<OSMessage> list) {
        this.list = list;
    }
}
