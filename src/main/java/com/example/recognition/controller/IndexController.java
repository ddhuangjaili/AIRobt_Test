package com.example.recognition.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.recognition.ConfigBeanValue;
import com.example.recognition.client.HttpClientExtend;
import com.example.recognition.model.*;
import com.example.recognition.utils.BaseUtil;
import com.example.recognition.utils.FileUtil;
import com.example.recognition.utils.ImageBase64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/25.
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    ConfigBeanValue config;

    @RequestMapping("/test")
    public String index(){
        return "hello world!";
    }

    @RequestMapping(value = "/ocr", method = RequestMethod.POST)
    public String readPicWords(RequestParam request){
        String ocResult = "";
        String result = "";
        /*图片上传详情*/
        String path = "";
        String name = "";
        boolean flag = false;
        /*图片上传详情*/
        Map<String,Object> paramMap = new HashMap<>();
        ResponseContent respCon = new ResponseContent();

        if (BaseUtil.stringNotNull(request.getImageBase64())){
            //base64
            JsonParam jp = new JsonParam();
            jp.setImgString(request.getImageBase64());
            paramMap.put("param", JSONObject.toJSONString(jp));
            try {
                ocResult = HttpClientExtend.doPost(config.ocr_url, paramMap);
            } catch (UnsupportedEncodingException e) {
                logger.error("【{}】:{}",ResultMessage.LOG_ERROR_HTTP_CHARACTER.getCode(),
                        ResultMessage.LOG_ERROR_HTTP_CHARACTER.getMessage());
                respCon = new ResponseContent(ocResult,ResultMessage.ERROR_SYS_RUNTIME.getCode(),
                        ResultMessage.ERROR_SYS_RUNTIME.getMessage());
            }

            name = System.currentTimeMillis() + "_img.jpg";
            path = config.ocr_path + System.getProperty("file.separator")
                    + name;
            /*flag = ImageBase64.base64ToImage(request.getImageBase64().substring(
                    request.getImageBase64().indexOf(";") + 1
                    ).replace("base64,",""), path);*/
            flag = ImageBase64.base64ToImage(ImageBase64.delBase64Top(request.getImageBase64()), path);
            if (!flag){
                logger.error("【{}】:{}",ResultMessage.ERROR_BASE64_TO_FILE.getCode(),
                        ResultMessage.ERROR_BASE64_TO_FILE.getMessage());
            }

        } else if (request.getFile() != null && !request.getFile().isEmpty()){
            //文件转base64
            //String path = FileUtil.getInstance().savePicToGetPath(request.getFile(), config.ocr_path);
            ImageUploadVo vo = FileUtil.getInstance().savePicToGetPath(request.getFile(), config.ocr_path);
            path = vo.getPath();
            name = vo.getName();
            if (BaseUtil.stringNotNull(path)){
                String base64 = ImageBase64.imageToBase64(path);
                if (BaseUtil.stringNotNull(base64)) {
                    JsonParam jp = new JsonParam();
                    jp.setImgString(base64);
                    paramMap.put("param", JSONObject.toJSONString(jp));
                    try {
                        ocResult = HttpClientExtend.doPost(config.ocr_url, paramMap);
                    } catch (UnsupportedEncodingException e) {
                        logger.error("【{}】:{}",ResultMessage.LOG_ERROR_HTTP_CHARACTER.getCode(),
                                ResultMessage.LOG_ERROR_HTTP_CHARACTER.getMessage());
                        /*respCon = new ResponseContent(ocResult, ResultMessage.ERROR_SYS_RUNTIME.getCode(),
                                ResultMessage.ERROR_SYS_RUNTIME.getMessage());*/
                        respCon.setCode(ResultMessage.ERROR_SYS_RUNTIME.getCode());
                        respCon.setMessage(ResultMessage.ERROR_SYS_RUNTIME.getMessage());
                    }
                }

                flag = true;
            } else {
                logger.error("【{}】:{}",ResultMessage.LOG_ERROR_UPLOAD_NOPATH.getCode(),
                        ResultMessage.LOG_ERROR_UPLOAD_NOPATH.getMessage());
                respCon.setCode(ResultMessage.ERROR_IMAGE_UPLOAD_NOPATH.getCode());
                respCon.setMessage(ResultMessage.ERROR_IMAGE_UPLOAD_NOPATH.getMessage());
            }

        } else {
            respCon.setResult("empty params");
            respCon.setCode(ResultMessage.ERROR_EMPTY_PARAM.getCode());
            respCon.setMessage(ResultMessage.ERROR_EMPTY_PARAM.getMessage());
        }

        //封装result
        if (BaseUtil.stringNotNull(ocResult)) {
            OcrResultVo ocrResultVo = JSON.parseObject(ocResult, OcrResultVo.class);
            List<DistinguishVo> res = ocrResultVo.getRes();
            if (res.size() == 0) {
                //无结果 记录code和message
                /*respCon = new ResponseContent(result, ResultMessage.ERROR_RESULT_EMPTY.getCode(),
                        ResultMessage.ERROR_RESULT_EMPTY.getMessage());*/
                respCon.setCode(ResultMessage.ERROR_RESULT_EMPTY.getCode());
                respCon.setMessage(ResultMessage.ERROR_RESULT_EMPTY.getMessage());
            } else {
                //有结果
                for (DistinguishVo dv : res) {
                    result += dv.getText();
                }

                if (flag && BaseUtil.stringNotNull(name)){
                    respCon.setImgUrl(config.img_url + name);
                }
                /*respCon = new ResponseContent(result, ResultMessage.SUCCESS_RESULT.getCode(),
                        ResultMessage.SUCCESS_RESULT.getMessage());*/
                respCon.setResult(result);
                respCon.setCode(ResultMessage.SUCCESS_RESULT.getCode());
                respCon.setMessage(ResultMessage.SUCCESS_RESULT.getMessage());
            }
        }

        return JSONObject.toJSONString(respCon);
    }

}
