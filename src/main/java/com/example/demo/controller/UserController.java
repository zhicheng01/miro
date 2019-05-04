package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="新增用户", notes="根据User对象新增用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addUser(@RequestBody User user){
        userService.insert(user);
    }

    @ApiOperation(value="获取用户详细信息", notes="根据id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/queryUserById/{id}", method = RequestMethod.GET)
    public User queryUserById(@PathVariable(value = "id") Long id){
        return userService.queryUserById(id);
    }

    @ApiOperation(value="删除用户", notes="根据id来删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.GET)
    public void deleteById(@PathVariable(value = "id") Long id){
        userService.deleteById(id);
    }

    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
}
