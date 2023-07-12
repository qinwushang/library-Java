package com.example.service.impl;

import com.example.domain.Books;
import com.example.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl {
    @Autowired
    private IBookService ibookservice;
    @Autowired
    private RedisTemplate redisTemplate;


//    //查所有用户
//    public List<Books> queryAll(){
//        return ibookservice.list();
//    }

    /**
     * 获取用户策略:先从缓存中获取用户，没有则读mysql数据，再将数据写入缓存
     */
    public Books findUserById(int id){
        String key = "book_"+id;
        ValueOperations<String,Books> operations = redisTemplate.opsForValue();
        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            Books books = operations.get(key);
            System.out.println("从缓存中获取bookId:"+books.getId());
            System.out.println("-----------------------------");
            return books;
        }else{
            Books books = ibookservice.getById(id);
            System.out.println("查询数据库获取数据:"+books.getName());
            System.out.println("------------写入缓存---------------------");
            //写入缓存
            operations.set(key,books,5, TimeUnit.HOURS);
            return books;
        }
    }

    //删除用户策略:删除数据表中数据，然后删除缓存
//    public int deleteUserById(int id){
//        int result = ibookservice.deleteUserById(id);
//        String key = "user_"+id;
//        if(result!=0){
//            boolean hasKey = redisTemplate.hasKey(key);
//            if(hasKey){
//                redisTemplate.delete(key);
//                System.out.println("删除了缓存中的key:"+key);
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 更新用户策略：先更新数据表，成功之后，删除原来的缓存，再更新缓存
//     */
//    public int updateUser(User user) {
//        ValueOperations<String, User> operations = redisTemplate.opsForValue();
//        int result = ibookservice.updateUser(user);
//        if (result != 0) {
//            String key = "user_" + user.getId();
//            boolean haskey = redisTemplate.hasKey(key);
//            if (haskey) {
//                redisTemplate.delete(key);
//                System.out.println("删除缓存中的key-----------> " + key);
//            }
//            // 再将更新后的数据加入缓存
//            User userNew = ibookservice.findUserById(user.getId());
//            if (userNew != null) {
//                operations.set(key, userNew, 3, TimeUnit.HOURS);
//            }
//        }
//        return result;
//    }



}
