package com.lpcheng.microskynet.dao;

import com.google.gson.Gson;
import com.lpcheng.microskynet.domain.Camera;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CameraDaoTest {


    @Autowired
    private CameraDao cameraDao;
    @Autowired
    private Gson gson;

    @Test
    public void list() {
        List<Camera> list = cameraDao.list();
        System.out.println(gson.toJson(list));
    }

    @Test
    public void saveOrUpdate() {
        String data = "192.168.88.106\t111.322722\t23.505307\n" +
                "192.168.88.107\t111.32442\t23.506086\n" +
                "192.168.88.108\t111.32274\t23.506649\n" +
                "192.168.88.109\t111.32115\t23.506799\n" +
                "192.168.88.110\t111.321895\t23.506003\n" +
                "192.168.88.111\t111.324231\t23.50776\n" +
                "192.168.88.112\t111.324527\t23.505042\n" +
                "192.168.88.113\t111.325327\t23.503335\n" +
                "192.168.88.114\t111.326117\t23.502615\n" +
                "192.168.88.115\t111.323162\t23.500809\n" +
                "192.168.88.116\t111.323512\t23.4994\n" +
                "192.168.88.117\t111.322129\t23.502606\n" +
                "192.168.88.118\t111.320377\t23.50283\n" +
                "192.168.88.119\t111.322093\t23.505307\n";
        String[] datas = data.split("\n");
        for (int i = 0; i < datas.length; i++) {
            String[] row = datas[i].split("\t");
            Camera camera = new Camera();
            camera.setIp(row[0]);
            camera.setLng(Double.parseDouble(row[1]));
            camera.setLat(Double.parseDouble(row[2]));
            System.out.println(camera);
            //cameraDao.saveOrUpdate(camera);
        }
        Camera camera = new Camera();
        camera.setIp("192.168.88.100");
        camera.setLng(111.322852);
        camera.setLat(23.503845);
        cameraDao.saveOrUpdate(camera);
        list();
    }

    @Test
    public void delete() {
        Camera camera = new Camera();
        camera.setIp("192.168.88.103");
        cameraDao.delete(camera);
        list();
    }
}