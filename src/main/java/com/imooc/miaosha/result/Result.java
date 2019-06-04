package com.imooc.miaosha.result;

public class Result<T> {


    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(Codemsg cm) {

        if (cm == null) {
            return;
        }

        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public static <T>  Result<T> success(T data) {

        return new Result<T>(data);
    }

    public static <T>  Result<T> erro(Codemsg  cm) {
        return new Result<T>(cm);

    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
