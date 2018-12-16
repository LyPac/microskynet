package com.lpcheng.microskynet.controller;

import com.lpcheng.microskynet.domain.JsonResult;
import com.lpcheng.microskynet.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camera")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    @RequestMapping("/list")
    public JsonResult list() {
        return cameraService.list();
    }
}
