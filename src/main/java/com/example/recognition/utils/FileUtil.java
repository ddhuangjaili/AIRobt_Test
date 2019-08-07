package com.example.recognition.utils;

import com.example.recognition.model.ResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Perry on 2019/6/3.
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static FileUtil instance = new FileUtil();

    private FileUtil(){}

    public static FileUtil getInstance(){
        return instance;
    }

    /**
     * 视频上传
     * @param file
     * @param savePath
     * @return
     */
    public String saveVideoForUpload(MultipartFile file, String savePath){
        String path = "";
        InputStream inputStream = null;
        OutputStream os = null;
        int len;
        /*设置缓冲区*/
        byte[] bs = new byte[1024*1024];

        /*获取保存路径*/
        File tempFile = new File(savePath);
        if (!tempFile.exists()){
            tempFile.mkdir();
        }

        try {
            inputStream = file.getInputStream();
            String name = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(File.separator)+1);
            logger.info("当前保存的文件名:"+name);
            path = tempFile.getPath() + File.separator + System.currentTimeMillis() + "_" +name;
            logger.info("文件的完整路径:"+path);
            name = System.currentTimeMillis() + "_" +name;
            logger.info("新文件名:"+name);
            os = new FileOutputStream(tempFile.getPath() + File.separator + name);
            while ((len = inputStream.read(bs)) != -1){
                os.write(bs, 0, len);
            }
            os.flush();
        } catch (FileNotFoundException e) {
            logger.error(ResultMessage.LOG_ERROR_UPLOAD_NOFILE.getMessage());
            logger.error("",e.getMessage());
        } catch (IOException e) {
            logger.error(ResultMessage.LOG_ERROR_IO_RUNTIME_EXCEPTION.getMessage() + ":outputStream");
            logger.error("",e.getMessage());
        } finally {
            try {
                os.close();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error(ResultMessage.LOG_ERROR_IO_CLOSE_EXCEPTION.getMessage());
                logger.error("", e.getMessage());
            }
        }

        return path;
    }



    /**
     * 保存上传图片
     * @param file
     * @return
     */
    public String savePicToGetPath(MultipartFile file, String savePath){
        logger.info("开始保存图片...");
        String path = null;
        InputStream inputStream = null;
        OutputStream os = null;
        int len;
        /*设置缓冲区*/
        byte[] bs = new byte[1024];

        /*获取保存路径*/
        File tempFile = new File(savePath);
        if (!tempFile.exists()){
            tempFile.mkdir();
        }

        try {
            inputStream = file.getInputStream();
            String name = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(File.separator)+1);
            logger.info("当前保存的文件名:"+name);
            path = tempFile.getPath() + File.separator + System.currentTimeMillis() + "_" +name;
            logger.info("文件的完整路径:"+path);
            name = System.currentTimeMillis() + "_" +name;
            logger.info("新文件名:"+name);
            os = new FileOutputStream(tempFile.getPath() + File.separator + name);
            while ((len = inputStream.read(bs)) != -1){
                os.write(bs, 0, len);
            }
            os.flush();
        } catch (FileNotFoundException e) {
            logger.error("待保存图片未找到,请检查当前文件是否存在......");
            logger.error("", e.getMessage());
        } catch (IOException e) {
            logger.error("IO流出现异常:outputStream");
            logger.error("", e.getMessage());
        } finally {
            try {
                os.close();
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("关闭I/O流异常");
                logger.error("", e.getMessage());
            }
        }

        return path;
    }
}
