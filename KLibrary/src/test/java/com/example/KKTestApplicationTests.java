package com.example;

import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class KKTestApplicationTests {

    @Test
    void contextLoads() {
        Map<String,String> msglist = new HashMap<String,String>();
        msglist.put("id","123");
        msglist.put("id2","1223");
        msglist.put("id3","12d3");
        Object msgjson = JSONArray.toJSON(msglist);
        String retmsg = msgjson.toString();
        System.out.println(msgjson);
    }

}
