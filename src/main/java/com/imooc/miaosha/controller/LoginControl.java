package com.imooc.miaosha.controller;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisServer;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.Codemsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.service.UserService;
import com.imooc.miaosha.util.ValidatorUtil;
import com.imooc.miaosha.vo.LoginVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/login")
public class LoginControl {


    public static Logger log = LoggerFactory.getLogger(LoginControl.class);

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @RequestMapping("/to_login")
    public  String login ()  {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin (LoginVo loginVo){
        log.info(loginVo.toString());

        //  参数校验

        String passInput = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if (StringUtils.isEmpty(passInput)) {
            return Result.erro(Codemsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isEmpty(mobile)) {
            return Result.erro(Codemsg.MOBILE_EMPTY);
        }

        if (!ValidatorUtil.isMobile(mobile)) {
            return Result.erro(Codemsg.MOBILE_ERRO);
        }
        //  登录
        Codemsg cm = miaoshaUserService.login(loginVo);
        if (cm.getCode() == 0) {
            return Result.success(true);
        } else {
            return Result.erro(cm);
        }

    }
        //  是否提交到远程仓库

}
