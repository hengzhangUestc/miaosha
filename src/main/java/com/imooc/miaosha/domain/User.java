package com.imooc.miaosha.domain;

public class User {
    private int key;
    private String name;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "key=" + key +
                ", name='" + name + '\'' +
                '}';
    }
}
