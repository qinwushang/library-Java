package com.example.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.controller.utils.R;
import com.example.controller.utils.TokenUtil;
import com.example.domain.Books;
import com.example.service.IBookService;
import com.example.service.impl.BookServiceImplNew;
import com.example.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService iBookService;

    @Autowired
    private RedisServiceImpl redisService;

    @Autowired
    private BookServiceImplNew bookServiceImplNew;

    @GetMapping
    public R getAll(){
        return new R(true,redisService.findUserById(1));
    }

    @PostMapping
    public R save(@RequestBody Books books) throws Exception{
        boolean flag = iBookService.save(books);
        return new R(flag,flag ? "加载成功" : "加载失败");
    }

    @RequestMapping("/bookadd")
    @PostMapping
    public R save(@RequestParam("file") MultipartFile file,@RequestParam("name") String name,@RequestParam("type") String type,@RequestParam("description") String description,@RequestParam("url") String url) throws Exception{
        String address_bookurl = "/Users/nuncio/IdeaProjects/springtest/KLibrary/src/main/resources/bookRealUrl/";
        Books books = new Books();
        books.setName(name);
        books.setType(type);
        books.setUrl(address_bookurl + file.getOriginalFilename());
        books.setDescription(description);
        InputStream is = null;
        FileOutputStream fos = null;
        byte[] buffer = new byte[10000 * 1024];//根据上传文件大小设定
        try {
            is = file.getInputStream();// 获得上传文件流
            //创建文件输出流  使用FileOutputStream打开服务器端的上传文件
            fos = new FileOutputStream(address_bookurl + file.getOriginalFilename());
            int len = 0;
            //开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
            //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
            while ((len = is.read(buffer)) > 0) {
                fos.write(buffer, 0, len);//写入到指定的目录当中
            }
            fos.flush();
            is.close();
            fos.close();
        }catch (IOException e) {
            System.out.println("文件上传失败！");
            throw new Exception(e);
        }
        boolean flag = iBookService.save(books);
//        System.out.println("geturl: " + address_bookurl + file.getOriginalFilename());
        return new R(flag,flag ? "加载成功" : "加载失败");
    }


    @PutMapping
    public R upedate(@RequestBody Books books){
        return new R(iBookService.updateById(books));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(iBookService.removeById(id));
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,redisService.findUserById(id));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Books books){
        IPage<Books> page = iBookService.getPage(currentPage,pageSize, books);
        if(currentPage > page.getPages()){
            page = iBookService.getPage((int)page.getPages(),pageSize, books);
        }
        return new R(true,page);

    }


    @GetMapping("/login")
    public Map<String,Object> login(Books books){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        if(StringUtils.isEmpty(books.getId().toString()) || StringUtils.isEmpty(books.getName()) ){
            map.put("msg","信息为空！");
            return map;
        }
        QueryWrapper<Books> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",books.getId())
                .eq("name",books.getName());
        Books bookDb = iBookService.getOne(queryWrapper);
        if(bookDb != null){
            String token= TokenUtil.generateToken(bookDb);
            map.put("code",1);
            map.put("data",bookDb);
            map.put("token",token);
        }else{
            map.put("msg","信息错误！");
        }
        System.out.println(map);
        return map;
    }
}
