package com.example.service.impl;

import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {



    private Map<String,String> msglist = new HashMap<String,String>();


    @Override
    public String readMessage() {
        Object msgjson = JSONArray.toJSON(msglist);
        System.out.println(msgjson);
        return msgjson.toString();
    }

    @Override
    public void receiveMessage(String id, String Msg) {
        msglist.put(id,Msg);
        System.out.println("消息id: " + id);
        System.out.println("内容: " + Msg);
    }
}
