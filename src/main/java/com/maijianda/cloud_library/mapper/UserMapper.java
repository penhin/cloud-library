package com.maijianda.cloud_library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.maijianda.cloud_library.entity.User;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    User selectUserByUsername(@Param("username") String username);
    
    List<User> selectByCondition(User user);
} 
