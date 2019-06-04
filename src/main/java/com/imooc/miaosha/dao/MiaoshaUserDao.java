package com.imooc.miaosha.dao;

import com.imooc.miaosha.domain.Miaosha_user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MiaoshaUserDao {

    @Select("select *  from  miaosha_user where id = #{id}")
    Miaosha_user getUserById( @Param("id") Long id);



}
