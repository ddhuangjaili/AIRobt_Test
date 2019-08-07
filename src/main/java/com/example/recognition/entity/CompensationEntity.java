package com.example.recognition.entity;

public class CompensationEntity {

    private long id;

    private long regId;

    private String regName;

    private String content;

    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public CompensationEntity(long id, long regId, String regName, String content, Integer status) {
        this.id = id;
        this.regId = regId;
        this.regName = regName;
        this.content = content;
        this.status = status;
    }
}
