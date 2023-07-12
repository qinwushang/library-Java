package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.BookDao;
import com.example.domain.Books;
import com.example.service.IBookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImplNew extends ServiceImpl<BookDao, Books> implements IBookService {

//    private HashMap<Integer,Book> hashMap = new HashMap<Integer,Book>();


    @Autowired
    private BookDao bookDao;

//    @Cacheable(value = "cachespace",key = "#id")
//    public Book getById(Integer id){
//        return bookDao.selectById(id);
//    }

    @Override
    public IPage<Books> getPage(int currantPage, int pageSize) {
        IPage page = new Page(currantPage,pageSize);
        bookDao.selectPage(page,null);
        return page;
    }

//    @Override
//    public void updateurl(Integer id) {
//        bookDao.updateById();
//    }

    @Override
    public IPage<Books> getPage(int currantPage, int pageSize, Books books) {
        LambdaQueryWrapper<Books> lambdaQueryWrapper = new LambdaQueryWrapper<Books>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(books.getType()), Books::getType, books.getType());
        lambdaQueryWrapper.like(Strings.isNotEmpty(books.getName()), Books::getName, books.getName());
        lambdaQueryWrapper.like(Strings.isNotEmpty(books.getDescription()), Books::getDescription, books.getDescription());
        IPage page = new Page(currantPage,pageSize);
        bookDao.selectPage(page,lambdaQueryWrapper);
        return page;
    }
}
