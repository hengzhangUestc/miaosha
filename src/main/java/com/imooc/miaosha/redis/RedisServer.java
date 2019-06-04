package com.imooc.miaosha.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisServer {
    @Autowired
    JedisPool jedisPool;
    /*
    获取单个对象
     */
    public  <T>  T getKey(KeyPrefix prefix , String key ) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String s = jedis.get(realKey);
            T t = StringToBean(s);
            return t;
        }finally {
            returnToPool(jedis);
        }

    }


    /*
   设置对象
    */
    public  <T> boolean setKey(KeyPrefix prefix ,String key , T  value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() == 0) {
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            int second = prefix.expireSeconds();
            if (second <= 0) {

                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, second, str);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }

    }

    /*
      判断key是否存在
       */
    private <T> Boolean exist(KeyPrefix prefix , String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }

    }

    /*
     增加值
   */
    private <T> Long incr(KeyPrefix prefix , String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }

    }

    /*
        减少值
   */
    private <T> Long decr(KeyPrefix prefix , String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }

    }


    private void returnToPool(Jedis jedis) {
        if (jedis == null) {
            jedis.close();
        }
    }

    private <T> T StringToBean(String s ) {
        if (s ==null || s.length() == 0 ) {
            return null;
        }

        Class<?> clazz = s.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(s);
        } else if (clazz == String.class) {
            return (T) String.valueOf(s);
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(s);
        } else {

            return (T) JSON.toJavaObject(JSON.parseObject(s),clazz);
        }
    }

    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {

            return JSON.toJSONString(value);
        }
    }
}
