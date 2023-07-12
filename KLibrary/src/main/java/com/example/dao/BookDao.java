package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Books;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BookDao extends BaseMapper<Books> {

}
