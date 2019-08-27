package com.login.gai.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Created by gaidongxu on 2019/8/27.
 */
@Component
public final class RedisUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //==============common==================
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, Long time){
        try{
            if(time > 0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询缓存失效时间
     * @param key 键
     * @return 时间（秒）
     */
    public long getExpire(String key){
        if(key == null){
            return -1;
        }else {
            return redisTemplate.getExpire(key,TimeUnit.SECONDS);
        }
    }

    /**
     * 查询key值是否存在
     * @param key 键
     * @return true存在 false不存在
     */
    public boolean hasKey(String key){
        try{
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 数组
     * @return 成功删除的个数 -1为参数不合法
     */
    public long del(String... key){
        if(key != null && key.length > 0){
            if(key.length == 1){
                if(redisTemplate.delete(key[0])){
                    return 1;
                }else {
                    return 0;
                }
            }else {
                return redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
        return -1;
    }

    //==============String==================
    /**
     * 普通获取value
     * @param key
     * @return 值
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通设置value
     * @param key
     * @return true成功  false失败
     */
    public boolean set(String key,String value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
