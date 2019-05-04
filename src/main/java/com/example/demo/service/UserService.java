package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    void insert(User user);

    User queryUserById(Long id);

    int deleteById(Long id);

    List<User> getAllUser();
}
