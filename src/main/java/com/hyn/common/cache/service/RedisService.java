package com.hyn.common.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wuzg
 * @version 1.0.0
 * @description redis 工具类
 * @date 2022/1/27
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisService
{
    @Autowired
    public RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @description 缓存基本的对象，Integer、String、实体类等
     * @author wuzg
     * @param: key      缓存的键值
     * @param: value    缓存的值
     * @date 2022/1/28 10:42
     * @return: 缓存的对象
     * @throws
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }

    /**
     * @description 缓存基本的对象，Integer、String、实体类等
     * @author wuzg
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     * @return: 缓存的对象
     * @throws
     */
    public <T> ValueOperations<String, T> setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, timeout, timeUnit);
        return operation;
    }

    /**
     * @description 获得缓存的基本对象
     * @author wuzg
     * @param: key  缓存键值
     * @date 2022/1/28 10:40
     * @return: 缓存键值对应的数据
     * @throws
     */
    public <T> T getCacheObject(String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * @description 删除单个对象
     * @author wuzg
     * @param: key
     * @date 2022/1/28 10:40
     * @return: java.lang.Boolean
     * @throws
     */
    public Boolean deleteObject(String key)
    {
        return redisTemplate.delete(key);
    }
    /**
     * @description 删除集合对象
     * @author wuzg
     * @param: collection
     * @date 2022/1/28 10:40
     * @throws
     */
    public void deleteObject(Collection collection)
    {
        redisTemplate.delete(collection);
    }
    /**
     * @description 缓存List数据
     * @author wuzg
     * @param: key  缓存的键值
     * @param: dataList 待缓存的List数据
     * @date 2022/1/28 10:39
     * @return: org.springframework.data.redis.core.ListOperations<java.lang.String,T> 缓存的对象
     * @throws
     */
    public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList)
    {
        ListOperations listOperation = redisTemplate.opsForList();
        if (null != dataList)
        {
            int size = dataList.size();
            for (int i = 0; i < size; i++)
            {
                listOperation.leftPush(key, dataList.get(i));
            }
        }
        return listOperation;
    }

    /**
     * @description 获得缓存的list对象
     * @author wuzg
     * @param: key  缓存的键值
     * @date 2022/1/28 10:39
     * @return: java.util.List<T>   缓存键值对应的数据
     * @throws
     */
    public <T> List<T> getCacheList(String key)
    {
        List<T> dataList = new ArrayList<T>();
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        Long size = listOperation.size(key);

        for (int i = 0; i < size; i++)
        {
            dataList.add(listOperation.index(key, i));
        }
        return dataList;
    }

    /**
     * @description 缓存Set
     * @author wuzg
     * @param: key      缓存键值
     * @param: dataSet  缓存的数据
     * @date 2022/1/28 10:39
     * @return: org.springframework.data.redis.core.BoundSetOperations<java.lang.String,T> 缓存数据的对象
     * @throws
     */
    public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet)
    {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext())
        {
            setOperation.add(it.next());
        }
        return setOperation;
    }
    /**
     * @description 获得缓存的set
     * @author wuzg
     * @param: key
     * @date 2022/1/28 10:38
     * @return: java.util.Set<T>
     * @throws
     */
    public <T> Set<T> getCacheSet(String key)
    {
        Set<T> dataSet = new HashSet<T>();
        BoundSetOperations<String, T> operation = redisTemplate.boundSetOps(key);
        dataSet = operation.members();
        return dataSet;
    }

    /**
     * @description 缓存Map
     * @author wuzg
     * @param: key
     * @param: dataMap
     * @date 2022/1/28 10:38
     * @return: org.springframework.data.redis.core.HashOperations<java.lang.String,java.lang.String,T>
     * @throws
     */
    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap)
    {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap)
        {
            for (Map.Entry<String, T> entry : dataMap.entrySet())
            {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    /**
     * @description 获得缓存的Map
     * @author wuzg
     * @param: key
     * @date 2022/1/28 10:38
     * @return: java.util.Map<java.lang.String,T>
     * @throws
     */
    public <T> Map<String, T> getCacheMap(String key)
    {
        Map<String, T> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    /**
     * @description 获得缓存的基本对象列表
     * @author wuzg
     * @param: pattern  字符串前缀
     * @date 2022/1/28 10:38
     * @return: java.util.Collection<java.lang.String> 列表
     * @throws
     */
    public Collection<String> keys(String pattern)
    {
        return redisTemplate.keys(pattern);
    }

    /**
     * @description 移除列表的最后一个元素
     * @author wuzg
     * @param: key  列表键值
     * @date 2022/1/28 10:37
     * @return: T 键值对应的数据
     * @throws
     */
    public <T> T rPop(String key) {
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        return listOperation.rightPop(key);
    }

    /**
     * @description 在列表中添加一个或多个值
     * @author wuzg
     * @param: key          键值
     * @param: dataList     键值对应的数据
     * @date 2022/1/28 10:37
     * @throws
     */
    public <T> void rPush(String key, List<T> dataList) {
        ListOperations<String, T> listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            for (T t : dataList) {
                listOperation.rightPush(key, t);
            }
        }
    }

    /**
     * @description 存储缓存数据
     * @author wuzg
     * @param: key      缓存key
     * @param: value    缓存值
     * @param: timeout  过期时间
     * @param: timeUnit 时间单位
     * @date 2022/1/28 10:49
     * @throws
     */
    public void set(String key, String value,Long timeout) {
        set(key,value,timeout,null);
    }
    /**
     * @description 存储缓存数据
     * @author wuzg
     * @param: key      缓存key
     * @param: value    缓存值
     * @param: timeout  过期时间
     * @param: timeUnit 时间单位
     * @date 2022/1/28 10:49
     * @throws
     */
    public void set(String key, String value,Long timeout, TimeUnit timeUnit) {
        if (timeout == null) {
            stringRedisTemplate.opsForValue().set(key, value);
        } else {
            if (timeUnit == null) {
                timeUnit = TimeUnit.SECONDS;
            }
            stringRedisTemplate.opsForValue().set(key, value,timeout,timeUnit);
        }
    }

    /**
     * @description 存储数据
     * @author wuzg
     * @param: key
     * @param: value
     * @date 2022/1/28 10:35
     * @throws
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * @description 获取数据
     * @author wuzg
     * @param: key
     * @date 2022/1/28 10:36
     * @return: java.lang.String
     * @throws
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
    /**
     * @description 获取缓存key过期时间
     * @author wuzg
     * @param: key  缓存键
     * @date 2022/1/28 11:28
     * @return: java.lang.Long
     * @throws
     */
    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }
    /**
     * @description 设置缓存过期时间
     * @author wuzg
     * @param: key      缓存key
     * @param: expire   过期时间
     * @param: timeUnit 时间单位
     * @date 2022/1/28 10:45
     * @return: boolean
     * @throws
     */
    public boolean expire(String key, long expire,TimeUnit timeUnit) {
        if (timeUnit == null) {
            return expire(key,expire);
        }
        return stringRedisTemplate.expire(key, expire, timeUnit);
    }

    /**
     * @description 设置超期时间
     * @author wuzg
     * @param: key
     * @param: expire
     * @date 2022/1/28 10:36
     * @return: boolean
     * @throws
     */
    public boolean expire(String key, long expire) {
        return stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * @description 删除数据
     * @author wuzg
     * @param: key
     * @date 2022/1/28 10:36
     * @throws
     */
    public void remove(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * @description 自增操作
     * @author wuzg
     * @param: key
     * @param: delta    自增步长
     * @date 2022/1/28 10:36
     * @return: java.lang.Long
     * @throws
     */
    public Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * @description 当前缓存key是否存在
     * @author wuzg
     * @param: key
     * @date 2022/1/28 10:36
     * @return: boolean
     * @throws
     */
    public boolean isExist(String key) {
        return stringRedisTemplate.hasKey(key);
    }
}
