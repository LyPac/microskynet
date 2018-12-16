package com.lpcheng.microskynet.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HDFSUtilTest {

    @Autowired
    private HDFSUtil hdfsUtil;

    @Test
    public void list() {
        hdfsUtil.lsr("/");
    }

    @Test
    public void isDirectory() {
        boolean flag = hdfsUtil.isDirectory("/microskynet/orginal/192.168.1.64");
        System.out.println(flag);
    }

    @Test
    public void mkdir() {
        String dir = "/microskynet/orginal/192.168.1.64";
        if (!hdfsUtil.isDirectory(dir)) {
            hdfsUtil.mkdir(dir);
        }
    }

    @Test
    public void get() {
        hdfsUtil.get("/microskynet/orginal/192.168.1.65/1544144297256.avi", "D:/out/192.168.1.65/1544144297256.avi");
    }
}