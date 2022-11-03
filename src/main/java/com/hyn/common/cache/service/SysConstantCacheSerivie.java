package com.hyn.common.cache.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyn.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wuzg
 * @version 1.0.0
 * @description 常量缓存服务
 * @date 2022/4/29
 */
@Component
public class SysConstantCacheSerivie {

    private static final JSONObject DEFAULT_JSON = new JSONObject();
    private static final String CONSTANT_CACHE_KEY = "ConstantsCache";
    private static final String CONSTANT_CACHE_CHILD_KEY = "childList";
    @Autowired
    private RedisService redisService;


    /**
     * @description 获取所有的常量缓存
     * @author wuzg
     * @date 2022/4/29 13:48
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @throws
     */
    public Map<String,String> getMapCache() {
        return redisService.getCacheMap(CONSTANT_CACHE_KEY);
    }

    /**
     * @description 通过一级缓存key获取一级缓存数据
     * @author wuzg
     * @param: firstLevelCacheKey
     * @date 2022/4/29 11:53
     * @return: 返回指定一级节点的数据
     * @throws
     */
    public JSONObject getFirstLevelCacheNode(String firstLevelCacheKey) {
        Map<String,String> resMap = this.getMapCache();
        if (resMap == null || resMap.isEmpty()) {
            return null;
        }
        //根据缓存key获取一级节点的缓存数据
        String resStr = resMap.get(firstLevelCacheKey);
        if (StringUtils.isEmpty(resStr)) {
            return null;
        }
        return JSON.parseObject(resStr);
    }

    /**
     * @description 通过一级缓存key和二级缓存key获取二级缓存数据
     * @author wuzg
     * @param: firstLevelCacheKey
     * @param: secondLevelCacheKey
     * @date 2022/4/29 14:15
     * @return: com.alibaba.fastjson.JSONObject
     * @throws
     */
    public JSONObject getSecondLevelCacheNode(String firstLevelCacheKey, String secondLevelCacheKey) {
        return getCacheNode(this.getFirstLevelCacheNode(firstLevelCacheKey),secondLevelCacheKey);
    }

    /**
     * @description 通过一级缓存key、二级缓存key和三级缓存key获取三级级缓存数据
     * @author wuzg
     * @param: firstLevelCacheKey
     * @param: secondLevelCacheKey
     * @param: threeLevelCacheKey
     * @date 2022/4/29 14:16
     * @return: com.alibaba.fastjson.JSONObject
     * @throws
     */
    public JSONObject getThreeLevelCacheNode(String firstLevelCacheKey, String secondLevelCacheKey, String threeLevelCacheKey) {
        return getCacheNode(this.getSecondLevelCacheNode(firstLevelCacheKey, secondLevelCacheKey),threeLevelCacheKey);
    }

    /**
     * @description 获取缓存节点
     * @author wuzg
     * @param: json
     * @param: cacheKey
     * @date 2022/4/29 14:17
     * @return: com.alibaba.fastjson.JSONObject
     * @throws
     */
    private JSONObject getCacheNode(JSONObject json, String cacheKey) {
        if (json == null || json.isEmpty()) {
            return  DEFAULT_JSON;
        }
        JSONArray nodes = this.getJSONArrayFromJSON(json);
        if (nodes == null || nodes.isEmpty() || nodes.size() == 0) {
            return null;
        }
        return nodes.stream().map(i -> JSONObject.parseObject(i.toString()))
                .filter(item -> item.containsValue(cacheKey))
                .findFirst().orElse(null);
    }

    /**
     * @description 提取JSON中子集
     * @author wuzg
     * @param: json
     * @date 2022/4/29 14:10
     * @return: com.alibaba.fastjson.JSONArray
     * @throws
     */
    private JSONArray getJSONArrayFromJSON(JSONObject json) {
        return json.getJSONArray(CONSTANT_CACHE_CHILD_KEY);
    }
}
