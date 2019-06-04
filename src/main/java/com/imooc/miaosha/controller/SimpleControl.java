package com.imooc.miaosha.controller;

import com.imooc.miaosha.dao.UserDao;
import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisServer;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.Codemsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SimpleControl {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisServer redisServer;
    @RequestMapping("/thymeleaf")
    public  String thymeleaf (Model model)  {
        model.addAttribute("name", "jushua");
        return "hello";
    }

    @RequestMapping("/print")
    @ResponseBody
    String print (){
        return "hello print";
    }

    @RequestMapping("/hello")
    @ResponseBody
    Result<String>  hello() {
        return Result.success("hello");
    }
    @RequestMapping("/erro")
    @ResponseBody
    Result<String>  erro() {
        return Result.erro(Codemsg.SERVER_ERRO);
    }

    @RequestMapping("/dbg")
    @ResponseBody
    Result<User>  dbGet() {

        User user = userService.getById(1);
        return Result.success(user);

    }

 /*   @RequestMapping("/redis/get")
    @ResponseBody
    public  Result<String> redisGet() {

        String s =  redisServer.getKey("k1");

        return Result.success(s);

    }*/

    @RequestMapping("/redis/prefixget")
    @ResponseBody
    public  Result<Boolean> redisGet() {
        User user = new User();
        user.setKey(1);
        user.setName("zhangsan");
        boolean s =  redisServer.setKey(UserKey.getById,"k2", user);

        return Result.success(true);

    }


}
