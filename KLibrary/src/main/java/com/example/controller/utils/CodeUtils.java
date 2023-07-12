package com.example.controller.utils;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CodeUtils {

    private String[] patch = {"000000","00000","0000","000","00","0",""};
    public String generator(String tele){
        int hash = tele.hashCode();
        int encryption = 20206666;
        long result = hash^encryption;
        long nowTime = System.currentTimeMillis();
        result = result ^ nowTime;
        long code = result % 100000;
        code = code < 0 ? -code : code;
        String codestr = code + "";
        int len = codestr.length();
        return patch[len] + codestr;

    }
    @Cacheable(value = "smsCode",key = "#tele")
    public String get(String tele){
        return null;
    }

//    public static void main(String[] args) {
//        System.out.println(new CodeUtils().generator("18866668888"));
//    }
}
