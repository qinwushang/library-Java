package com.example.controller;

import com.example.domain.SMScode;
import com.example.service.SMSCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SMSCodeController {

    @Autowired
    private SMSCodeService smsCodeService;

    @GetMapping
    public String getCode(String tele){
        return smsCodeService.get(tele);
    }

    @PostMapping
    public boolean checkCode(SMScode smScode){
        return smsCodeService.check(smScode);
    }
}
