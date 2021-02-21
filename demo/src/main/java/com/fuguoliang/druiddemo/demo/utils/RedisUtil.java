package com.fuguoliang.druiddemo.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author FGL_S
 */
@Component
@EnableCaching
public class RedisUtil {
    private static final String LockScript = "local lock_key = KEYS[1]\n" +
            "local lock_val = ARGV[1]\n" +
            "\n" +
            "if redis.call('set', lock_key, lock_val, 'NX PX 10000') == OK\n" +
            "then\n" +
            "    return 1\n" +
            "else\n" +
            "    return 0\n" +
            "end";
    private static final String UnlockScript = "local lock_key = KEYS[1]\n" +
            "local lock_val = ARGV[1]\n" +
            "\n" +
            "if redis.call('get', lock_key) == lock_val\n" +
            "then\n" +
            "    return redis.call(\"del\", lock_key)\n" +
            "else\n" +
            "    return 0\n" +
            "end\n";
    private static final DefaultRedisScript<Long> RedisLockScript = new DefaultRedisScript<>(LockScript.toString(), Long.class);
    private static final DefaultRedisScript<Long> RedisUnlockScript = new DefaultRedisScript<>(UnlockScript.toString(), Long.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public Long incr(String lockKey) {
        if (hasKey(lockKey)) {
            return redisTemplate.opsForValue().increment(lockKey);
        }
        set(lockKey, 1L);
        return 1L;
    }
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key,long time){
        try {
            if(time>0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean tryLock(String lockKey, String lockVal) {
        try {
           // Long result  = redisTemplate.execute(RedisLockScript, Collections.singletonList(lockKey), lockVal);
            return redisTemplate.opsForValue().setIfAbsent(lockKey, lockVal, Duration.ofSeconds(10));

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return false;
    }

    public boolean releaseLock(String lockKey, String lockVal) {
        Long result  = redisTemplate.execute(RedisUnlockScript, Collections.singletonList(lockKey), lockVal);
        if (null !=  result && 1 == result) {
            return true;
        }
        return false;
    }
    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key,Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key,Object value,long time){
        try {
            if(time>0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }else{
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
