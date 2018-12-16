package com.lpcheng.microskynet;

import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkSubmit;
import org.apache.spark.deploy.rest.CreateSubmissionResponse;
import org.apache.spark.deploy.rest.RestSubmissionClient;
import org.apache.spark.deploy.rest.RestSubmissionClientApp;
import org.junit.Test;
import scala.collection.immutable.HashMap;

public class SparkSubmitTest {

    @Test
    public void sparkSubmit() {
        //spark-submit --master spark://dev-120.microskynet.com:6066 \
        // --deploy-mode cluster \
        // --class com.lpcheng.microskynet.sparkjob.RtspJob \
        // hdfs://dev-120.microskynet.com:9000/microskynet/sparkjob/sparkjob-1.0.jar \
        // 192.168.1.65
        String ip = "192.168.1.65";
        String[] args = new String[]{
                "--master", "spark://dev-120.microskynet.com:6066",
                "--deploy-mode", "cluster",
                "--driver-memory", "2G",
                "--class", "com.lpcheng.microskynet.sparkjob.RtspJob",
                "hdfs://dev-120.microskynet.com:9000/microskynet/sparkjob/sparkjob-1.0.jar",
                ip
        };
        SparkSubmit.main(args);
    }

    @Test
    public void restSubmissionClient() throws InterruptedException {
        String master = "spark://dev-120.microskynet.com:6066";
        String appResource = "hdfs://dev-120.microskynet.com:9000/microskynet/sparkjob/sparkjob-1.0.jar";
        //String mainClass = "com.lpcheng.microskynet.sparkjob.RtspJob";
        //String ip = "192.168.1.65";
        String mainClass = "com.lpcheng.microskynet.sparkjob.CleanJob";
        String path = "/microskynet/orginal/192.168.1.65/1544144268835.avi";
        String segment1 = "{\"start\":0,\"end\":100}";
        String segment2 = "{\"start\":150,\"end\":250}";
        String[] args = new String[]{
                //ip
                path,
                segment1,
                segment2
        };
        SparkConf conf = new SparkConf();
        conf.setMaster(master)
                .setAppName("Clean:" + path)
                .set("spark.submit.deployMode", "cluster")
                .set("spark.jars", appResource)
                .set("spark.driver.memory", "2G");
        RestSubmissionClient client = new RestSubmissionClient(master);
        CreateSubmissionResponse response = (CreateSubmissionResponse) new RestSubmissionClientApp().run(appResource, mainClass, args, conf, new HashMap<>());
        System.out.println(response.toJson());
        System.out.println(response.submissionId());
        /*int i = 0;
        while (true) {
            Thread.sleep(1000);
            System.out.println(i++);
        }*/
        //client.killSubmission(response.submissionId());
    }

    @Test
    public void test() {
        String path = "D:/out/192.168.1.65/1544142076779.avi";
        String[] paths = path.substring(path.indexOf("out/") + 4).split("/");
        String dir = "/microskynet/orginal/" + paths[0];
        String file = paths[1];
        System.out.println(dir + "\n" + dir + "/" + file);
    }
}
