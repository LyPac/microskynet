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
        fsShell.lsr(path).forEach(fileStatus -> log.info(fileStatus.getPath().toString()));
    }

    public void put(String src, String dst) {
        fsShell.put(src, dst);
    }

    public void get(String src, String dst) {
        fsShell.get(src, dst);
    }

    public void rmr(String path) {
        fsShell.rmr(path);
    }

    public boolean isDirectory(String dir) {
        return fsShell.test(true, false, true, dir);
    }

    public void mkdir(String dir) {
        fsShell.mkdir(dir);
    }
}
