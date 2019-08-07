package com.example.recognition.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Perry on 2019/6/24.
 */
public class RequestParam {
    /*图片base64*/
    private String imageBase64;
    /*文件*/
    private MultipartFile file;

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
