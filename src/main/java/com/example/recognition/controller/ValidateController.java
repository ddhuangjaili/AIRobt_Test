package com.example.recognition.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.recognition.ConfigBeanValue;
import com.example.recognition.model.RequestParam;
import com.example.recognition.model.ResponseContent;
import com.example.recognition.model.ResultMessage;
import com.example.recognition.service.ValidateService;
import com.example.recognition.utils.BaseUtil;
import com.example.recognition.utils.FileUtil;
import com.example.recognition.utils.ImageBase64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by Perry on 2019/6/21.
 */
@RestController
@RequestMapping("/validate")
public class ValidateController {
    private static final Logger logger = LoggerFactory.getLogger(ValidateController.class);

    @Autowired
    ConfigBeanValue config;

    @Resource
    ValidateService validateService;

    @RequestMapping("/init")
    public String validate(RequestParam request){
        String result = "";
        ResponseContent response = new ResponseContent();
        if (BaseUtil.stringNotNull(request.getImageBase64())){
            //base64处理
            String path = config.image_path + System.getProperty("file.separator")
                    + System.currentTimeMillis() + "_img.jpg";
            logger.info("图片完整路径:{}",path);
            boolean b = ImageBase64.base64ToImage(request.getImageBase64(), path);
            if (b){
                //执行python脚本
                result = validateService.checkVideoForEffective(path, config.tracker_flag);
                //result = validateService.getResult(path, config.tracker_flag);
                response.setResult(result);
                if (BaseUtil.stringNull(result)){
                    response.setCode(ResultMessage.ERROR_RESULT_EMPTY.getCode());
                    response.setMessage(ResultMessage.ERROR_RESULT_EMPTY.getMessage());
                } else {
                    response.setCode(ResultMessage.SUCCESS_RESULT.getCode());
                    response.setMessage(ResultMessage.SUCCESS_RESULT.getMessage());
                }
            } else{
                //base64转换图片失败
                logger.error(ResultMessage.LOG_ERROR_BASE64_TO_FILE.getMessage());
                response.setCode(ResultMessage.ERROR_BASE64_TO_FILE.getCode());
                response.setMessage(ResultMessage.ERROR_BASE64_TO_FILE.getMessage());
            }
        } else if (request.getFile() != null && !request.getFile().isEmpty()){
            //video上传路径
            String path = FileUtil.getInstance().saveVideoForUpload(request.getFile(), config.video_path);
            if (BaseUtil.stringNotNull(path)){
                //执行python脚本
                result = validateService.checkVideoForEffective(path, config.tracker_flag);
                //result = validateService.getResult(path, config.tracker_flag);
                response.setResult(result);
                if (BaseUtil.stringNull(result)){
                    response.setCode(ResultMessage.ERROR_RESULT_EMPTY.getCode());
                    response.setMessage(ResultMessage.ERROR_RESULT_EMPTY.getMessage());
                } else {
                    response.setCode(ResultMessage.SUCCESS_RESULT.getCode());
                    response.setMessage(ResultMessage.SUCCESS_RESULT.getMessage());
                }
            } else {
                logger.error(ResultMessage.LOG_ERROR_UPLOAD_NOPATH.getMessage());
                response.setCode(ResultMessage.ERROR_FILE_UPLOAD_NOPATH.getCode());
                response.setMessage(ResultMessage.ERROR_FILE_UPLOAD_NOPATH.getMessage());
            }
        } else {
            response.setResult("empty params");
            response.setCode(ResultMessage.ERROR_EMPTY_PARAM.getCode());
            response.setMessage(ResultMessage.ERROR_EMPTY_PARAM.getMessage());
        }

        return JSONObject.toJSONString(response);
    }

    @RequestMapping("/videoFile")
    public String validateByVideoFile(MultipartFile videoFile){
        String result = "";
        ResponseContent response = new ResponseContent();
        //video上传路径
        String path = FileUtil.getInstance().saveVideoForUpload(videoFile, config.video_path);
        if (BaseUtil.stringNotNull(path)){
            //执行python脚本
            result = validateService.checkVideoForEffective(path, config.tracker_flag);
            //result = validateService.getResult(path, config.tracker_flag);
            response.setResult(result);
            if (BaseUtil.stringNull(result)){
                response.setCode(ResultMessage.ERROR_RESULT_EMPTY.getCode());
                response.setMessage(ResultMessage.ERROR_RESULT_EMPTY.getMessage());
            } else {
                response.setCode(ResultMessage.SUCCESS_RESULT.getCode());
                response.setMessage(ResultMessage.SUCCESS_RESULT.getMessage());
            }
        } else {
            logger.error(ResultMessage.LOG_ERROR_UPLOAD_NOPATH.getMessage());
            response.setCode(ResultMessage.ERROR_FILE_UPLOAD_NOPATH.getCode());
            response.setMessage(ResultMessage.ERROR_FILE_UPLOAD_NOPATH.getMessage());
        }

        return JSONObject.toJSONString(response);
    }


}
