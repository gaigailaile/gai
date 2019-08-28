package com.login.gai.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 有失效时间的普通缓存，一般用于String
     * @param key
     * @param value
     * @param time (秒) 大于0设置时间，小于等于0永久
     * @return
     */
    public boolean set(String key, Object value, long time){
        try {
            if(time > 0) {
                redisTemplate.opsForValue().set(key, value, time);
            }else {
                set(key, value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 累加方法参数为long型
     * @param key
     * @param delta (必须大于0)
     * @return
     */
    public long increment(String key, long delta){
        if(delta < 0){
            new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 累加方法参数为double型
     * @param key
     * @param delta double (必须大于0)
     * @return
     */
    public double increment(String key, double delta){
        if(delta < 0){
            new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 累减方法参数为long型
     * @param key
     * @param delta
     * @return
     */
    public long decrement(String key, long delta){
        if(delta < 0){
            new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    //==============Map==================
    /**
     * HashGet 获取缓存中 Hash格式中的值
     * @param key redis中对应的键
     * @param hashKey hash中相应的键
     * @return
     */
    public Object hashGet(String key, String hashKey){
        return redisTemplate.opsForHash().get(key,hashKey);
    }

    /**
     * 具体不明  待测
     * @param key
     * @return
     */
    public Map<Object,Object> hashEntries(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 一次存储多个键值对，没有失效时间
     * @param key
     * @param map
     * @return
     */
    public boolean hashPutAll(String key, Map<Object,Object> map){
        try{
            redisTemplate.opsForHash().putAll(key,map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 一次存储多个键值对，有失效时间
     * @param key
     * @param map
     * @param time（秒） 大于0时设置失效时间，小于等于0时永久有效
     * @return
     */
    public boolean hashPutAll(String key, Map<Object,Object> map, long time){
        try {
            redisTemplate.opsForHash().putAll(key,map);
            if(time > 0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向hash表中存放数据，key值不存在时创建hash表
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public boolean hashPut(String key, String hashKey, Object value){
        try{
            redisTemplate.opsForHash().put(key,hashKey,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向hash表中存放数据，key值不存在时创建hash表
     * @param key
     * @param hashKey
     * @param value
     * @param time (秒) 会覆盖原有效时间
     * @return
     */
    public boolean hashPut(String key, String hashKey, Object value, long time){
        try{
            redisTemplate.opsForHash().put(key,hashKey,value);
            if(time > 0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的数据
     * @param key
     * @param hashKey
     * @return
     */
    public long hashDelete(String key, Object... hashKey){
        return redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hashHasKey(String key, String hashKey){
        return redisTemplate.opsForHash().hasKey(key,hashKey);
    }

    /**
     * hash递增
     * @param key
     * @param hashKey
     * @param by
     * @return
     */
    public double hashIncrement(String key, String hashKey, double by){
        return redisTemplate.opsForHash().increment(key,hashKey,by);
    }

    /**
     * hash递减
     * @param key
     * @param hashKey
     * @param by
     * @return
     */
    public double hashdecr(String key, String hashKey, double by){
        return redisTemplate.opsForHash().increment(key,hashKey,-by);
    }

    //==============Set==================
    /**
     * 根据key值获取set中的所有值
     * @param key
     * @return
     */
    public Set<Object> members(String key){
        try{
            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断set中是否存在某个值
     * @param key
     * @param value
     * @return true存在 false不存在
     */
    public boolean isMember(String key, Object value){
        try {
            return redisTemplate.opsForSet().isMember(key,value);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     * @param key
     * @param value  一个或多个
     * @return 成功个数
     */
    public long add(String key, Object... value){
        try{
            return redisTemplate.opsForSet().add(key,value);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将数据放入set缓存,并设置失效时间
     * @param key
     * @param time（秒） 覆盖原有的时间
     * @param value
     * @return 成功个数
     */
    public long add(String key, long time, Object... value){
        try {
            long count = redisTemplate.opsForSet().add(key,value);
            if(time > 0){
                expire(key,time);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取size缓存长度
     * @param key
     * @return
     */
    public long getSetSize(String key){
        try{
            return redisTemplate.opsForSet().size(key);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除set中的value值
     * @param key
     * @param value
     * @return 移除的个数
     */
    public long remove(String key, Object... value){
        try{
            return redisTemplate.opsForSet().remove(key, value);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    //==============List==================

    /**
     * 获取list缓存
     * @param key
     * @param start 开始位置
     * @param end 结束位置 0 -1 表示全部
     * @return
     */
    public List<Object> range(String key,long start,long end){
        try{
            return redisTemplate.opsForList().range(key,start,end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存长度
     * @param key
     * @return
     */
    public long getListSize(String key){
        try{
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key
     * @param index index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object index(String key,long index){
        try{
            return redisTemplate.opsForList().index(key,index);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将数据放入list缓存
     * @param key
     * @param value
     * @return
     */
    public boolean rightPush(String key, Object value){
        try{
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入list缓存
     * @param key
     * @param value
     * @param time(秒)
     * @return
     */
    public boolean rightPush(String key, Object value, long time){
        try{
            redisTemplate.opsForList().rightPush(key, value);
            if(time > 0){
                expire(key,time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将集合存入list缓存
     * @param key
     * @param value
     * @return
     */
    public boolean rightPushAll(String key,List<Object> value){
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数组存入list缓存
     * @param key
     * @param value
     * @return
     */
    public boolean rightPushAll(String key,Object... value){
        try {
            redisTemplate.opsForList().rightPushAll(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key
     * @param index
     * @param value
     * @return
     */
    public boolean setList(String key, long index, Object value){
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 具体情况不明待测
     * @param key
     * @param count
     * @param value
     * @return
     */
    public long remove(String key, long count , Object value){
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
