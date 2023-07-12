package com.example.controller.utils;

import lombok.Data;

@Data
public class R { //前后端数据一致
    private Boolean flag;
    private Object data;
    private String msg;

    public R(){}

    public R(Boolean flag){
        this.flag = flag;
    }

    public R(Boolean flag,Object data){
        this.flag = flag;
        this.data = data;
    }
    public R(String msg){ //错误异常报告
        this.msg = msg;
    }

    public R(Boolean flag,String msg){
        this.flag = flag;
        this.msg = msg;
    }
}
