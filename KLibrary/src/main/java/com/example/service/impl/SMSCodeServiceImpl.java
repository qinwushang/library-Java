package com.example.service.impl;

import com.example.controller.utils.CodeUtils;
import com.example.domain.SMScode;
import com.example.service.SMSCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class SMSCodeServiceImpl implements SMSCodeService {

    @Autowired
    private CodeUtils codeUtils;


    @Override
    //@Cacheable(value = "smsCode",key = "#tele")
    @CachePut(value = "smsCode",key = "#tele")
    public String get(String tele) {
        String code = codeUtils.generator(tele);
        return code;
    }

    @Override
    public boolean check(SMScode smScode) {
        String code = smScode.code;
        String cachecode = codeUtils.get(smScode.tele);
        return code.equals(cachecode);
    }
}
