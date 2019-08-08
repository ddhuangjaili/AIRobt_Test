package com.example.recognition.model;

/**
 * Created by Perry on 2019/6/12.
 */
public enum ResultMessage {

    SUCCESS_RESULT("0","success"),
    ERROR_RESULT_EMPTY("9000","no data of result return."),
    ERROR_FILE_UPLOAD_NOPATH("9001"," video file upload fail, path is null! "),
    ERROR_BASE64_TO_FILE("9002","image base64 convert file fail."),

    JSON_FIRST_WORDS("","您可输入出差城市，如"),
    JSON_LAST_WORDS("","。我会为您查询对应的出差标准哟~"),


    ERROR_EMPTY_PARAM("9009","param list is empty, please get image base64 or file!"),

    LOG_ERROR_UPLOAD_NOPATH("","未获取到有效video文件路径,请检查文件是否上传成功!"),
    LOG_ERROR_UPLOAD_NOFILE("","待保存视频未找到,请检查当前文件是否存在......"),
    LOG_ERROR_IO_RUNTIME_EXCEPTION("","IO流出现异常"),
    LOG_ERROR_IO_CLOSE_EXCEPTION("","关闭I/O流异常"),
    LOG_ERROR_BASE64_TO_FILE("","base64转换图片失败");

    private String code;
    private String message;

    ResultMessage(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
