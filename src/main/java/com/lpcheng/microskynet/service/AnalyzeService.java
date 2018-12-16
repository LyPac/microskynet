package com.lpcheng.microskynet.service;

import com.google.gson.Gson;
import com.lpcheng.microskynet.sparkjob.JobSubmit;
import com.lpcheng.microskynet.util.HDFSUtil;
import com.lpcheng.microskynet.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AnalyzeService {

    @Autowired
    private OkHttpUtil okHttpUtil;

    @Autowired
    private Gson gson;

    @Autowired
    private HDFSUtil hdfsUtil;

    public void clean(String msg) throws IOException {
        String[] paths = msg.substring(msg.indexOf("out/") + 4).split("/");
        String dir = "/microskynet/original/" + paths[0];
        String file = paths[1];
        if (!hdfsUtil.isDirectory(dir)) {
            hdfsUtil.mkdir(dir);
        }
        String path = dir + "/" + file;
        hdfsUtil.put(msg, path);

        Map<String, String> map = new HashMap();
        map.put("path", path);

        String json1 = okHttpUtil.request("", map);
        JobSubmit.cleanJob(JobSubmit.PLATE, path, json1);

        String json2 = okHttpUtil.request("", map);
        JobSubmit.cleanJob(JobSubmit.FACE, path, json2);
    }

    public void recognition(String key, String path) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("path", path);
        String json;

        switch (key) {
            case "plate":
                json = okHttpUtil.request("", map);
                Map result1 = gson.fromJson(json, Map.class);
                String plateNumber = (String) result1.get("plate_number");
                break;
            case "face":
                json = okHttpUtil.request("", map);
                List result2 = gson.fromJson(json, List.class);
                result2.forEach(item -> {
                    Map tmp = (Map) item;
                    String imgPath = (String) tmp.get("path");
                    List<Double> embedding = (List<Double>) tmp.get("embedding");
                });
                break;
        }
    }
}
