package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.MiaoshaUserDao;
import com.imooc.miaosha.domain.Miaosha_user;
import com.imooc.miaosha.result.Codemsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.util.MD5util;
import com.imooc.miaosha.vo.LoginVo;
import org.springframework.stereotype.Service;

@Service

public class MiaoshaUserService implements MiaoshaUserDao  {

    MiaoshaUserDao miaoshaUserDao;

    @Override
    public Miaosha_user getUserById(Long id) {

        return miaoshaUserDao.getUserById(id);
    }

    public Codemsg  login (LoginVo loginVo) {

        if (loginVo == null) {
            return Codemsg.SERVER_ERRO;
        }

        String mobile = loginVo.getMobile();
        String fromPass = loginVo.getPassword();
        Miaosha_user user = getUserById(Long.parseLong(mobile));

        if (user == null) {
            return Codemsg.MOBILE_NOT_EXIT;
        }

        String dbPass = user.getPassword();
        String dbSalt = user.getSalt();

        String calcPass = MD5util.fromPassToDBPass(fromPass, dbSalt);
        if (!calcPass.equals(dbPass)) {
            return Codemsg.PASSWORD_ERRO;
        }

        return Codemsg.SUCCESS;
    }
}
