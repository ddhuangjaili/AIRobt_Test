package com.example.recognition.model;

/**
 * Created by Perry on 2019/6/21.
 */
public class ResponseContent {
    /*返回结果*/
    private String result;
    /*状态值*/
    private String code;
    /*返回信息*/
    private String message;
    /*图片访问地址*/
    private String imgUrl;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public ResponseContent() {
    }

    public ResponseContent(String result, String code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public ResponseContent(String result, String code, String message, String imgUrl) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.imgUrl = imgUrl;
    }
}
