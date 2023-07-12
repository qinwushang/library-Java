package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Books;

public interface IBookService extends IService<Books> {

    IPage<Books> getPage(int currantPage, int pageSize);

    //void updateurl(Integer id);

    IPage<Books> getPage(int currantPage, int pageSize, Books books);

}
