package com.example.service;


import com.example.domain.SMScode;

public interface SMSCodeService {
    public String get(String tele);
    public boolean check(SMScode smScode);
}
