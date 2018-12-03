package com.lpcheng.microskynet;


import org.bytedeco.javacpp.opencv_videoio;
import org.bytedeco.javacv.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FFmpegTest {

    @Test
    public void process() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("ping www.baidu.com");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            System.out.println(stringBuilder.toString());
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rtsp() {
        String rtspPath = "rtsp://admin:asdfg123456@192.168.1.65:554/h264/ch1/main/av_stream";
        String rtmpPath = "rtmp://192.168.1.101:1935/live/stream";

        try {
            FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(rtspPath);
            grabber.setOption("rtsp_transport", "tcp");
            grabber.setOption("re", " ");

            FFmpegFrameRecorder recorder = FFmpegFrameRecorder.createDefault(rtmpPath, 1280, 720);
            recorder.setFormat("flv");
            recorder.setVideoCodecName("libx264");
            recorder.setOption("preset", "ultrafast");

            grabber.start();
            recorder.start();
            Frame frame = null;
            while ((frame = grabber.grabFrame()) != null) {
                recorder.record(frame);
            }
            recorder.stop();
            grabber.stop();
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        }
    }
}
