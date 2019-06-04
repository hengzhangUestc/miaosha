package com.imooc.miaosha.result;

public class Codemsg {

    private int code;
    private String msg;

    public static Codemsg SUCCESS = new Codemsg(0, "success");
    public static Codemsg SERVER_ERRO = new Codemsg(50010, "服务端异常");
    public static Codemsg PASSWORD_EMPTY = new Codemsg(50011, "密码为空");
    public static Codemsg MOBILE_EMPTY = new Codemsg(50012, "手机号为空");
    public static Codemsg MOBILE_ERRO = new Codemsg(50013, "手机号格式错误");
    public static Codemsg MOBILE_NOT_EXIT = new Codemsg(50014, "用户不存在");
    public static Codemsg PASSWORD_ERRO = new Codemsg(50015, "密码错误");


    private Codemsg(int s, String success) {
        this.code = s;
        this.msg = success;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
