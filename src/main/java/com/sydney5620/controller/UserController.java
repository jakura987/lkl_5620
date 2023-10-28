package com.sydney5620.controller;

import com.sydney5620.common.Result;
import com.sydney5620.pojo.AIAssistant;
import com.sydney5620.pojo.User;
import com.sydney5620.service.AIAssistanceService;
import com.sydney5620.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/admin/user")
@Api(tags = "user interface")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AIAssistanceService aiAssistanceService;

    @ApiOperation("UserLogin")
    @PostMapping("/login")
    public Result<User> userLogin(@RequestBody User user) {
        User userLogin = userService.userLogin(user);
        return Result.success(userLogin);

    }

    @ApiOperation("getAllUser")
    @GetMapping("/getAllUsers")
    public Result<List<User>> getAllUsers() {
        System.out.println("111");
        List<User> userList = userService.getAllUsers();
        return Result.success(userList);


    }

    @ApiOperation("getUserByName")
    @GetMapping("/getUserByName")
    public Result<User> searchUserByUserName(@RequestParam String userName) {
        User userByUserName = userService.getUserByUserName(userName);
        return Result.success(userByUserName);
    }


    @ApiOperation("addUser")
    @PostMapping("/addUser")
    public Result<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        AIAssistant assistant = new AIAssistant();
        assistant.setAiName("AI Assistant");
        assistant.setCommand("You are a smart English-teaching AI Assistant");
        assistant.setCreativity(0.5);
        assistant.setContextCount(100);
        assistant.setReplyLength(200);
        assistant.setUserId(user.getId());
        aiAssistanceService.addAIAssistance(assistant);
        return Result.success("add successfully");

    }

    @ApiOperation("updateUser")
    @PutMapping("/updateUser")
    public Result<String> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return Result.success("update successfully");
    }

    @ApiOperation("deleteUser")
    @DeleteMapping("/deleteUser")
    public Result<String> deleteUser(@RequestBody User user){
        userService.deleteUserByUserName(user);
        return Result.success("delete successfully");
    }


}

