package com.example.recognition.utils;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.presets.opencv_objdetect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * Created by Perry on 2019/6/22.
 */
public class RtspToRtmpUtil {
    private static final Logger logger = LoggerFactory.getLogger(RtspToRtmpUtil.class);

    private static RtspToRtmpUtil instance = new RtspToRtmpUtil();

    private RtspToRtmpUtil(){}

    public static RtspToRtmpUtil getInstance(){
        return instance;
    }

    public void recordPush(String inputFile, String outputFile, int v_rs) throws FrameGrabber.Exception, FrameRecorder.Exception, InterruptedException {
        Loader.load(opencv_objdetect.class);
        long startTime = 0;
        FrameGrabber grabber = FFmpegFrameGrabber.createDefault(inputFile);
        try {
            grabber.start();
        } catch (Exception e){
            try{
                grabber.restart();
            } catch (Exception e1){
                throw e;
            }
        }

        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        Frame grabframe = grabber.grab();
        IplImage grabbedImage = null;
        if (grabframe != null){
            logger.info("取到第一帧");
            grabbedImage = converter.convert(grabframe);
        } else {
            logger.error("没有取到第一帧");
        }

        FrameRecorder recorder;
        try {
            recorder = FrameRecorder.createDefault(outputFile, 1280, 720);
        } catch (FrameRecorder.Exception e) {
            throw e;
        }

        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setFormat("flv");
        recorder.setFrameRate(v_rs);
        recorder.setGopSize(v_rs);
        logger.info("准备开始推流.....");
        try {
            recorder.start();
        } catch (FrameRecorder.Exception e){
            try {
                logger.error("录制器启动失败,重新启动.....");
                if (recorder != null) {
                    logger.info("尝试关闭录制器");
                    recorder.stop();
                    logger.info("尝试重新开启录制器");
                    recorder.start();
                }
            } catch (FrameRecorder.Exception e1){
                throw e;
            }
        }

        logger.info("开始推流......");
        CanvasFrame frame = new CanvasFrame("camera", CanvasFrame.getDefaultGamma()/grabber.getGamma());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);

        while (frame.isVisible() && (grabframe=grabber.grab()) != null){
            logger.info("推流......");
            frame.showImage(grabframe);
            grabbedImage = converter.convert(grabframe);
            Frame rotetedFrame = converter.convert(grabbedImage);

            if (startTime == 0){
                startTime = System.currentTimeMillis();
            }
            recorder.setTimestamp(1000 * (System.currentTimeMillis() - startTime));
            if (rotetedFrame != null){
                recorder.record(rotetedFrame);
            }

            Thread.sleep(40);
        }

        frame.dispose();
        recorder.stop();
        recorder.release();
        grabber.stop();
        System.exit(2);
    }
}
