package com.lpcheng.microskynet.util;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HBaseUtilTest {

    @Autowired
    private HBaseUtil hBaseUtil;
    @Autowired
    private Gson gson;

    @Test
    public void get() {
        List list = hBaseUtil.find("20181113", null);
        System.out.println(gson.toJson(list));
    }
}