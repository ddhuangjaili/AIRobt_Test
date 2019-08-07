package com.example.recognition.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Created by Perry on 2019/5/30.
 * image与base64互转
 */
public class ImageBase64 {
    private static final Logger logger = LoggerFactory.getLogger(ImageBase64.class);

    /**
     * 图片转换base64
     * @param imgPath
     * @return
     */
    public static String imageToBase64(String imgPath){
        InputStream inputStream = null;
        byte[] data = null;

        try {
            inputStream = new FileInputStream(imgPath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (FileNotFoundException e) {
            logger.error("图片转换文件未找到,请检查当前文件是否存在,图片路径:"+imgPath);
            logger.error("", e.getMessage());
        } catch (IOException e) {
            logger.error("IO流出现异常:inputStream");
            logger.error("", e.getMessage());
        }

        BASE64Encoder encoder = new BASE64Encoder();
        //logger.info(encoder.encode(data));
        return encoder.encode(data);
    }


    /**
     * base64转文件
     * @param imgStr
     * @param path
     * @return
     */
    public static boolean base64ToImage(String imgStr, String path){
        if (BaseUtil.stringNull(imgStr))
            return false;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i]<0)
                    b[i] += 256;
            }
            File tempFile = new File(path);
            if (!tempFile.getParentFile().exists()){
                tempFile.getParentFile().mkdir();
            }
            OutputStream out = new FileOutputStream(tempFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            logger.error("IO流出现异常:outputStream");
            logger.error("", e.getMessage());
            return false;
        }
    }
}
