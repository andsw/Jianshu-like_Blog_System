package cn.jxufe.redis;

import cn.jxufe.util.SerializingUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: RedisCache
 * @author: hsw
 * @date: 2019/4/29 14:46
 * @Description: TODO
 */
public class RedisCache implements Cache {

    static JedisConnectionFactory factory;

    private String id;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public RedisCache(String id) {
        this.id = id;
    }

    private void printException(Exception e) {
        for (int i = 0; i < 11; i++) {
            System.out.println("-----------------------------------------------------");
            if (i == 5) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisConnection redisConnection = null;

        try {
            redisConnection = factory.getConnection();

            // 这里不使用JdkSerializationRedisSerializer是因为其序列化后的数据过于庞大，是json格式的五倍
            //而且这里不会碰到像shiro会话存储一样的问题，因为获取的数据都是有set的（Jackson2JsonRedisSerializer反序列化后的属性如果没有set会报错！）
            RedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

            redisConnection.set(redisSerializer.serialize(key), redisSerializer.serialize(value));
        } catch (SerializationException | JedisConnectionException se) {
            printException(se);
        } finally {
            if (redisConnection != null) {
                redisConnection.close();
            }
        }
    }

    @Override
    public Object getObject(Object key) {
        RedisConnection redisConnection = null;
        Object result = null;
        try {
            redisConnection = factory.getConnection();
            RedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

            result = redisSerializer.deserialize(redisConnection.get(redisSerializer.serialize(key)));
        } catch (SerializationException | JedisConnectionException se) {
            printException(se);
        } finally {
            if (redisConnection != null) {
                redisConnection.close();
            }
        }
        return result;
    }

    @Override
    public Object removeObject(Object o) {
        RedisConnection redisConnection = null;
        Object result = null;
        try {
            redisConnection = factory.getConnection();
            RedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

            result = redisConnection.expire(redisSerializer.serialize(o), 0);
        } catch (SerializationException | JedisConnectionException se) {
            printException(se);
        } finally {
            if (redisConnection != null) {
                redisConnection.close();
            }
        }
        return result;
    }

    @Override
    public void clear() {
        RedisConnection redisConnection = null;
        try {
            redisConnection = factory.getConnection();

            redisConnection.flushDb();
            redisConnection.flushAll();
        } catch (SerializationException | JedisConnectionException se) {
            printException(se);
        } finally {
            if (redisConnection != null) {
                redisConnection.close();
            }
        }
    }

    @Override
    public int getSize() {
        RedisConnection redisConnection = null;
        int size = 0;
        try {
            redisConnection = factory.getConnection();

            size = Integer.parseInt(redisConnection.dbSize().toString());
        } catch (SerializationException | JedisConnectionException se) {
            printException(se);
        } finally {
            if (redisConnection != null) {
                redisConnection.close();
            }
        }
        return size;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return lock;
    }

    @Override
    public String getId() {
        return id;
    }

}
