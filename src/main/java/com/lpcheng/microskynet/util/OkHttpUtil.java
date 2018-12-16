package com.lpcheng.microskynet.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class OkHttpUtil {

    @Autowired
    private OkHttpClient okHttpClient;

    public String request(String url, Map<String, String> map) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        map.forEach((key, value) -> builder.add(key, value));
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        String json = null;
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            json = responseBody.string();
            responseBody.close();
        }
        return json;
    }
}
