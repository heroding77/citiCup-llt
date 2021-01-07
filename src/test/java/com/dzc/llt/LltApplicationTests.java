package com.dzc.llt;

import com.dzc.llt.Util.OkHttpCli;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import net.sf.json.JSONObject;
import okhttp3.Call;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashMap;

@SpringBootTest
class LltApplicationTests {
    @Autowired
    private OkHttpCli okHttpCli;

    @Test
    void contextLoads() {
//        String url = "http://localhost:8890/android/login_data";
//        String json = "{\"username\":\"dalaoyang\",\"password\":\"123\"}";
//        String message = okHttpCli.doPostJson(url, json);
//        System.out.println(message);
        String baseurl = "http://localhost:8890";
        String url=baseurl+"/android/login_data";
        JSONObject Jparams =new JSONObject();
        Jparams.put("username","dzc");
        Jparams.put("password","123456");
        HttpRequest request= HttpRequest.get(url);
        request.send(Jparams.toString());//添加传输数据
        String responsebody=request.body("utf-8");//接收返回数据
        System.out.println(responsebody);

    }

}
