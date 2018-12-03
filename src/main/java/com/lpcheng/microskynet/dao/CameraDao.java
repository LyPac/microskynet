package com.lpcheng.microskynet.dao;

import com.lpcheng.microskynet.domain.Camera;
import com.lpcheng.microskynet.util.HBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CameraDao {

    @Autowired
    private HBaseUtil hBaseUtil;

    public List<Camera> list() {
        List<Map<String, Object>> list = hBaseUtil.find("camera", null, null);
        List cameraList = new ArrayList();
        list.forEach(item -> {
            Camera camera = new Camera();
            camera.setIp((String) item.get("rowKey"));
            Map<String, String> infoMap = (Map<String, String>) item.get("info");
            infoMap.forEach((key, value) -> {
                switch (key) {
                    case "lng":
                        camera.setLng(Double.parseDouble(value));
                        break;
                    case "lat":
                        camera.setLat(Double.parseDouble(value));
                        break;
                }
            });
            cameraList.add(camera);
        });
        return cameraList;
    }

    public void saveOrUpdate(Camera camera) {
        hBaseUtil.save("camera", camera.getIp(), "info", "lng", camera.getLng() + "");
        hBaseUtil.save("camera", camera.getIp(), "info", "lat", camera.getLat() + "");
    }

    public void delete(Camera camera) {
        hBaseUtil.delete("camera", camera.getIp());
    }
}
