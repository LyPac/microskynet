package com.lpcheng.microskynet.controller;

import com.google.gson.Gson;
import com.lpcheng.microskynet.domain.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private Gson gson;

    @RequestMapping("test")
    public JsonResult test() {
        Map<String, Object> map = new HashMap();
        map.put("username", "admin");
        map.put("password", "admin");
        map.put("registertime", new Date());
        log.debug("{}", gson);
        return JsonResult.success(map);
    }
}
