package cn.jxufe.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @ClassName: RedisCacheTransfer
 * @author: hsw
 * @date: 2019/4/29 14:44
 * @Description: TODO
 */
public class RedisCacheTransfer {
    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory factory) {
        RedisCache.factory = factory;
    }
}
