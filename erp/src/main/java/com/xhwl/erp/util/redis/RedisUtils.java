package com.xhwl.erp.util.redis;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Redis工具类
 * @author jiayiwu
 * @date 2017年12月25日
 */
public class RedisUtils {

	  private static RedisTemplate redisTemplate;
	  
	  /**
	   * 批量删除对应的value
	   * @author jiayiwu
	   * @date 2017年12月25日
	   * @param keys
	   */
	   public static void remove(final String... keys) {
	       for (String key : keys) {
	           remove(key);
	       }
	   }
	   
	   /**
	    * 批量删除key
	    * @author jiayiwu
	    * @date 2017年12月25日
	    * @param pattern
	    */
	    public static void removePattern(final String pattern) {
	        Set<Serializable> keys = redisTemplate.keys(pattern);
	        if (keys.size() > 0)
	            redisTemplate.delete(keys);
	    }
	    
	    /**
	     * 删除对应的value
	     * @author jiayiwu
	     * @date 2017年12月25日
	     * @param key
	     */
	    public static void remove(final String key) {
	        if (exists(key)) {
	            redisTemplate.delete(key);
	        }
	    }
	    
	    /**
	     * 判断缓存中是否有对应的value
	     * @author jiayiwu
	     * @date 2017年12月25日
	     * @param key
	     * @return
	     */
	    public static  boolean exists(final String key) {
	        return redisTemplate.hasKey(key);
	    }
	    
	    /**
	     * 读取缓存
	     * @author jiayiwu
	     * @date 2017年12月25日
	     * @param key
	     * @return
	     */
	    public static Object get(final String key) {
	        Object result = null;
	        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
	        result = operations.get(key);
	        return result;
	    }
	    
	    /**
	     * 写入缓存
	     * @author jiayiwu
	     * @date 2017年12月25日
	     * @param key
	     * @param value
	     * @return
	     */
	    public static boolean set(final String key, Object value) {
	        boolean result = false;
	        try {
	            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
	            operations.set(key, value);
	            result = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	    
	    /**
	     * 写入缓存
	     * @author jiayiwu
	     * @date 2017年12月25日
	     * @param key
	     * @param value
	     * @param expireTime
	     * @return
	     */
	    public static boolean set(final String key, Object value, Long expireTime) {
	        boolean result = false;
	        try {
	            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
	            operations.set(key, value);
	            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	            result = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	    
	    /**
	     * 缓存设置
	     * @param redisTemplate
	     */
	    public static void setRedisTemplate(RedisTemplate redisTemplate){
	        RedisUtils.redisTemplate = redisTemplate;
	    }
}
