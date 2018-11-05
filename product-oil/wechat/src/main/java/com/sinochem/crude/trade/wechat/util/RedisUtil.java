package com.sinochem.crude.trade.wechat.util;

import com.sinochem.crude.trade.wechat.helper.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;


/**
 * @Author: fengzk
 * @CreateDate: 2018/5/10 16:18
 * @Version: [v1.0]
 */
@Component
public class RedisUtil {
    @Value("${redis.host}")
    private String host;
    static final int timeout=240;

    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.timeout}")
    private long redistimeout;
    @Value("${redis.db}")
    private int redisdb;
    private Logger logger= LoggerFactory.getLogger(RedisUtil.class);
    private Jedis jedis;// 非切片额客户端连接
    private JedisPool jedisPool;// 非切片连接池
    /**
     * 初始化非切片池
     */
    private void initialPool() {
        // 池基本配置
        logger.info("redis配置:"+host+"|"+port+"|"+password+"|"+redisdb);
        JedisPoolConfig config = new JedisPoolConfig();
        // config.setmax.setMaxActive(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(redistimeout);
        config.setTestOnBorrow(false);
        jedisPool = new JedisPool(config, host, port);
//        JedisPool pool = new JedisPool(jredisConfig, server, Integer.valueOf(port));
        //jedisPool.close();
    }

    public void  RedisHelperInt() {
        try {
            initialPool();
            jedis = jedisPool.getResource();
            if(!StringHelper.strisnull(password)){
                jedis.auth(password);
            }
            jedis.select(redisdb);
        }
        catch (Exception ex)
        {
            logger.error("redis连接失败",ex);
        }
    }


    public String getMemberToken(String openid)
    {
        try {
            if(jedis==null)
            {
                RedisHelperInt();
            }
            if(jedis==null)
            {
                logger.error("redis连接失败");
                return null;
            }
            String key = "wechat_membertoken_" + openid;

            String token=jedis.get(key);
            if(!StringHelper.strisnull(token)){
                jedis.setex(key, timeout, token);
                logger.info("redis 获取token:"+openid+"||"+token);
            }
            return token;

        }
        catch (Exception ex)
        {
            logger.error("redis error:",ex);
        }
        return  null;
    }

    public void setMemberToken(String openid,String token)
    {
        try {
            if(jedis==null)
            {
                RedisHelperInt();
            }
            if(jedis==null)
            {
                logger.error("redis连接失败");
                return ;
            }
            String key = "wechat_membertoken_" + openid;
            jedis.setex(key, timeout, token);
            logger.info("redis 设置token:"+openid+"||"+token);
        }
        catch (Exception ex)
        {
            logger.error("redis error:",ex);
        }
    }


}
