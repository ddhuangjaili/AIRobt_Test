package com.example.recognition.entity;

public class CompensationEntity {

    private long id;

    private long regId;

    private String regName;

    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRegId() {
        return regId;
    }

    public void setRegId(long regId) {
        this.regId = regId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

}
