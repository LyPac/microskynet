package com.lpcheng.microskynet.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.fs.FsShell;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HDFSUtil {

    @Autowired
    private FsShell fsShell;

    public void lsr(String path) {
        fsShell.lsr(path).forEach(fileStatus -> System.out.println(fileStatus.getPath()));
    }

    public void put(String src, String dst) {
        fsShell.put(src, dst);
    }

    public void rmr(String path) {
        fsShell.rmr(path);
    }
}
