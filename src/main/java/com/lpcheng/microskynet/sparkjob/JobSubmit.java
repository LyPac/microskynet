package com.lpcheng.microskynet.sparkjob;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.rest.CreateSubmissionResponse;
import org.apache.spark.deploy.rest.RestSubmissionClientApp;
import scala.collection.immutable.HashMap;

@Slf4j
public class JobSubmit {

    private final static String MASTER = "spark://dev-120.microskynet.com:6066";
    private final static String APP_RESOURCE = "hdfs://dev-120.microskynet.com:9000/microskynet/sparkjob/sparkjob-1.0.jar";
    private final static String RTSP_CLASS = "com.lpcheng.microskynet.sparkjob.RtspJob";
    private final static String CLEAN_CLASS = "com.lpcheng.microskynet.sparkjob.CleanJob";
    private static SparkConf conf;

    public final static int PLATE = -1;
    public final static int FACE = 1;

    static {
        conf = new SparkConf();
        conf.setMaster(MASTER)
                .set("spark.submit.deployMode", "cluster")
                .set("spark.jars", APP_RESOURCE)
                .set("spark.driver.memory", "2G");
    }

    public static void cleanJob(int key, String hdfsPath, String segmentList) {
        String[] args = new String[]{
                hdfsPath,
                segmentList
        };
        switch (key) {
            case PLATE:
                break;
            case FACE:
                break;
        }
        conf.setAppName("clean:" + hdfsPath);
        submit(CLEAN_CLASS, args);
    }

    public static void orignalJob(String ip) {
        String[] args = new String[]{
                ip
        };
        conf.setAppName("rtsp:" + ip);
        submit(RTSP_CLASS, args);
    }

    private static void submit(String mainClass, String[] args) {
        // RestSubmissionClient client = new RestSubmissionClient(MASTER);
        CreateSubmissionResponse response = (CreateSubmissionResponse) new RestSubmissionClientApp().run(APP_RESOURCE, mainClass, args, conf, new HashMap<>());
        System.out.println(response.toJson());
        System.out.println(response.submissionId());
    }
}
