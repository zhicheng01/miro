package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void insert(User user);

    User queryUserById(Long id);

    int deleteById(Long id);

    List<User> getAllUser();
}
