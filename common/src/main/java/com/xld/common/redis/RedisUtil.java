package com.xld.common.redis;

import com.xld.common.other.LogUtil;
import com.xld.common.other.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类
 *
 * @author xld
 */
@Component
public class RedisUtil {

    @Autowired
    @Qualifier("protoStuffTemplate")
    private RedisTemplate protoStuffTemplate;

    /**
     * 设置过期时间，单位秒
     *
     * @param key     键的名称
     * @param timeout 过期时间
     * @return 成功：true，失败：false
     */
    public boolean setExpireTime(String key, long timeout) {
        return protoStuffTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 通过键删除一个值
     *
     * @param key 键的名称
     */
    public void delete(String key) {
        protoStuffTemplate.delete(key);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键的名称
     * @return 存在：true，不存在：false
     */
    public boolean hasKey(String key) {
        return protoStuffTemplate.hasKey(key);
    }

    /**
     * 数据存储
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        protoStuffTemplate.boundValueOps(key).set(value);
    }

    /**
     * 数据存储的同时设置过期时间
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间
     */
    public void set(String key, Object value, Long expireTime) {
        protoStuffTemplate.boundValueOps(key).set(value, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 数据取值
     *
     * @param key 键
     * @return 查询成功：值，查询失败，null
     */
    public Object get(String key) {
        return protoStuffTemplate.boundValueOps(key).get();
    }

    /**
     * 新增list并设置失效时间
     * @param redisKey redis键，不能为空
     * @param value 值
     * @param time 时间(秒) time>0
     * @return 操作成功：返回true，操作失败：返回false（redisKey为空或time<=0：返回false）
     */
    public boolean addList(String redisKey, Object value, long time) {
        if (!StrUtil.isNotEmpty(redisKey) || time <= 0) {
            return false;
        }

        try {
            protoStuffTemplate.opsForList().rightPush(redisKey, value);
            return setExpire(redisKey, time);
        } catch (Exception e) {
            LogUtil.printErrorLog(e);
        }
        return false;
    }

    /**
     * 设置redisKey失效时间
     * @param redisKey redis键，不能为空
     * @param time 时间(秒) time>0
     * @return 操作成功：返回true，操作失败：返回false（redisKey为空或time<=0：返回false）
     */
    public boolean setExpire(String redisKey, long time) {
        if (!StrUtil.isNotEmpty(redisKey) || time <= 0) {
            return false;
        }

        try {
            protoStuffTemplate.expire(redisKey, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            LogUtil.printErrorLog(e);
        }
        return false;
    }

    /**
     * 获取list
     * @param redisKey redis键，不能为空
     * @return 查询成功：返回list，查询失败：返回null（redisKey为空则返回null）
     */
    public List<Object> getList(String redisKey){
        if (!StrUtil.isNotEmpty(redisKey)) {
            return null;
        }

        try {
            return protoStuffTemplate.opsForList().range(redisKey, 0, getListSize(redisKey));
        } catch (Exception e) {
            LogUtil.printErrorLog(e);
        }
        return null;
    }

    /**
     * 获取list长度大小
     * @param redisKey redis键，不能为空
     * @return 查询成功：返回list长度大小，查询失败：返回0（redisKey为空则返回0）
     */
    public long getListSize(String redisKey){
        if (!StrUtil.isNotEmpty(redisKey)) {
            return 0;
        }

        try {
            return protoStuffTemplate.opsForList().size(redisKey);
        } catch (Exception e) {
            LogUtil.printErrorLog(e);
        }
        return 0;
    }
}
