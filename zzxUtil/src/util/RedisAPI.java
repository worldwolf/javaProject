package com.fid.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import java.util.Set;

import com.ytwl.cms.robots.common.domain.RbsTags;
import com.ytwl.cms.robots.common.domain.RbsWords;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisAPI {  
    private static JedisPool pool = null;  
      
    /** 
     * 构建redis连接池 
     *  
     * @param ip 
     * @param port 
     * @return JedisPool 
     */  
    public static JedisPool getPool() {  
        if (pool == null) {  
            JedisPoolConfig config = new JedisPoolConfig();  
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；  
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。  
            config.setMaxActive(100);  
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
            config.setMaxIdle(5);  
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
            config.setMaxWait(1000 * 100);  
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
            config.setTestOnBorrow(true);  
            pool = new JedisPool(config, "120.76.76.152", 6379);  
        }  
        return pool;  
    }  
      
    /** 
     * 返还到连接池 
     *  
     * @param pool  
     * @param redis 
     */  
    public static void returnResource(JedisPool pool, Jedis redis) {  
        if (redis != null) {  
            pool.returnResource(redis);  
        }  
    }  
      
    /** 
     * 获取数据 
     *  
     * @param key 
     * @return 
     */  
    public static String getString(String key){  
        String value = null;  
          
        JedisPool pool = null;  
        Jedis jedis = null;  
        try {  
            pool = getPool();  
            jedis = pool.getResource();  
            value = jedis.get(key);  
        } catch (Exception e) {  
            //释放redis对象  
            pool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        }  
          
        return value;  
    }  
    
    /** 
     * 获取数据 
     *  
     * @param key 
     * @return 
     */  
    public static Boolean get(String key,String value){  
    	Boolean isExists = false;  
          
        JedisPool pool = null;  
        Jedis jedis = null;  
        try {  
            pool = getPool();  
            jedis = pool.getResource();  
//            value = jedis.get(key);  
            isExists = jedis.sismember(key,value);
        } catch (Exception e) {  
            //释放redis对象  
            pool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        }  
          
        return isExists;  
    }  

    
    
    /**
     * 字符串
     * @param key
     * @param value
     */
    public static void set(String key,String value){  
          
        JedisPool pool = null;  
        Jedis jedis = null;  
        try {  
            pool = getPool();  
            jedis = pool.getResource();  
//            value = jedis.get(key);  
            jedis.sadd(key,value);
        } catch (Exception e) {  
            //释放redis对象  
            pool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        }  
          
    }  
    
    /**
     * set集合
     * @param setKey
     * @return
     */
    public static Set<String> getSet(String setKey){
    	
	  Set<String>  value = null;  
        
        JedisPool pool = null;  
        Jedis jedis = null;  
        try {  
            pool = getPool();  
            jedis = pool.getResource();  
            value = jedis.smembers(setKey);
        } catch (Exception e) {  
            //释放redis对象  
            pool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        } 
        return value;
    }
    
    
    
    /**set Object*/
    public static String set(Object object,String key)
	   {
	    	
	    	  JedisPool pool = null;  
	          Jedis jedis = null;  
	          String value = null;
			try {  
	              pool = getPool();  
	              jedis = pool.getResource();  
	              value = jedis.set(key.getBytes(), SerializeUtil.serialize(object));
	          } catch (Exception e) {  
	              //释放redis对象  
	              pool.returnBrokenResource(jedis);  
	              e.printStackTrace();  
	          } finally {  
	              //返还到连接池  
	              returnResource(pool, jedis);  
	          } 
	          return value;
	          
	   }
    
    /**get Object*/
    public  static Object get(String key)
    {
    	
  	  JedisPool pool = null;  
        Jedis jedis = null;  
        Object result = null;
		try {  
            pool = getPool();  
            jedis = pool.getResource();  
            byte[] value = jedis.get(key.getBytes());
            result = SerializeUtil. unserialize(value);
        } catch (Exception e) {  
            //释放redis对象  
            pool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        } 
        return result;
        
 }
    
    
    /**
     * 设值map  中一个值
     * @param key
     * @param data
     */
    public static void setMapValue(String mapName,String key,String value){
    	JedisPool pool = null;  
        Jedis jedis = null;  
		try {  
            pool = getPool();  
            jedis = pool.getResource(); 
            jedis.hset(mapName, key, value);
        } catch (Exception e) {  
            //释放redis对象  
            pool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        } 
    }
    
    /**
     * 去map  中一个值
     * @param key
     * @param data
     */
    public static String getMapValue(String mapName,String key){
    	String value = null;;
    	JedisPool pool = null;  
        Jedis jedis = null;  
		try {  
            pool = getPool();  
            jedis = pool.getResource(); 
            value = jedis.hget(mapName, key);
        } catch (Exception e) {  
            //释放redis对象  
            pool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        }
		return value;
    }
    
    /**
     * 设值map
     * @param key
     * @param data
     */
    public static void setMap(String key,Map<String, String> data){
    	Map<String,String> mapResult = new HashMap<>();
    	JedisPool pool = null;  
        Jedis jedis = null;  
		try {  
            pool = getPool();  
            jedis = pool.getResource(); 
            mapResult = jedis.hgetAll(key);
            mapResult.putAll(data);
            jedis.hmset(key,mapResult);
        } catch (Exception e) {  
            //释放redis对象  
            pool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        } 
    }
    
    /**
     * 取值map
     * @param key
     * @return
     */
    public static Map<String,String>  getMap(String key){
    	Map<String,String> mapResult = new HashMap<>();
    	JedisPool pool = null;  
        Jedis jedis = null;  
		try {  
            pool = getPool();  
            jedis = pool.getResource();  
            mapResult = jedis.hgetAll(key);
        } catch (Exception e) {  
            //释放redis对象  
            pool.returnBrokenResource(jedis);  
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        } 
		return mapResult;
    }
    
    public static void main(String[] args) {
    	System.out.println(getMap(RbsTags.class.getSimpleName()));
	}
}  