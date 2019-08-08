package com.example.recognition.model;

import java.util.List;

public class ApiResultVo {

    private String retCode;

    private List<String> result;

    private String resMsg;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public ApiResultVo(String retCode, List<String> result, String resMsg) {
        this.retCode = retCode;
        this.result = result;
        this.resMsg = resMsg;
    }
}
