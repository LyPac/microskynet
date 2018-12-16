package com.lpcheng.microskynet.service;

import com.lpcheng.microskynet.dao.CameraDao;
import com.lpcheng.microskynet.domain.Camera;
import com.lpcheng.microskynet.domain.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CameraService {

    @Autowired
    private CameraDao cameraDao;

    public JsonResult list() {
        List<Camera> list = cameraDao.list();
        Map map = new HashMap();
        map.put("cameraList", list);
        return JsonResult.success(map);
    }
}
