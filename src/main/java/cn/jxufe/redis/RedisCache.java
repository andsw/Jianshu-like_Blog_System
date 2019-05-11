package cn.jxufe.redis;


import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisCache
 * @author: hsw
 * @date: 2019/4/29 14:46
 * @Description: 键为String 值为json格式！
 */
public class RedisCache implements Cache {

    private GenericJackson2JsonRedisSerializer redisSerializer;

    private RedisTemplate<String, Object> redisTemplate;

    private String name;

    private Integer timeOut;

    /**
     * Return the underlying native cache provider.
     * @return 返回底层数据提供对象
     */
    @Override
    public Object getNativeCache() {
        return redisTemplate;
    }

    /**
     * 获取key的字符串值
     * @param key
     * @return
     */
    private String getFinalKey(Object key) {
        return key instanceof String ? (String) key : key.toString();
    }

    @Override
    public ValueWrapper get(Object key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        Object value = redisTemplate.opsForValue().get(getFinalKey(key));
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        // 千万记住啰！！！ getBytes的默认编码是ISO_8859_1, 所以进入redis后在获取会出现乱码甚至反序列化失败问题！
        return new SimpleValueWrapper(redisSerializer.deserialize(value.toString().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public <T> T get(Object key, Class<T> aClass) {
        if (StringUtils.isEmpty(key) || aClass == null) {
            return null;
        }
        String finalKey = getFinalKey(key);
        Object value = redisTemplate.opsForValue().get(finalKey);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        System.out.println(value);
        System.out.println(aClass.getName());
        System.out.println("---------------------------------------");
        return redisSerializer.deserialize(value.toString().getBytes(StandardCharsets.UTF_8), aClass);
    }

    /**
     * 不清楚callable类的具体含义，所以就直接返回null了！
     * @param key
     * @param callable
     * @param <T>
     * @return
     */
    @Override
    public <T> T get(Object key, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        if (StringUtils.isEmpty(key) || value == null) {
            return;
        }
        // 设置超时时间！
        System.out.println(new String(redisSerializer.serialize(value), StandardCharsets.UTF_8));

        // 注意第三个参数指的是offset即偏移量用来更精准更新子字符串，之前以为是保存时间，设置为600，然后值前面就出现600个空格字符！
        String finalKey = getFinalKey(key);
        redisTemplate.opsForValue().set(finalKey, new String(redisSerializer.serialize(value), StandardCharsets.UTF_8));

        //设置此键过期时间！redis自动删除哦！
        redisTemplate.expire(finalKey, timeOut, TimeUnit.SECONDS);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return null;
        }
        //setIfAbsent返回的是是否添加成功的boolean值，不会返回已存在的值，所以如果返回失败，则查询再返回！
        ValueOperations<String, Object> stringStringValueOperations = redisTemplate.opsForValue();

        String finalKey = getFinalKey(key);

        if (stringStringValueOperations.setIfAbsent(finalKey, new String(redisSerializer.serialize(value), StandardCharsets.UTF_8))) {
            //插入成功设置过期时间并返回null
            redisTemplate.expire(finalKey, timeOut, TimeUnit.SECONDS);
            return null;
        }
        //插入失败返回已存在的值！
        return new SimpleValueWrapper(redisSerializer.deserialize((byte[]) stringStringValueOperations.get(finalKey)));
    }

    @Override
    public void evict(Object key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        redisTemplate.delete(getFinalKey(key));
    }

    @Override
    public void clear() {
        redisTemplate.delete(redisTemplate.keys("*"));
    }

    public GenericJackson2JsonRedisSerializer getRedisSerializer() {
        return redisSerializer;
    }

    public void setRedisSerializer(GenericJackson2JsonRedisSerializer redisSerializer) {
        this.redisSerializer = redisSerializer;
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        //不指定的话前面会出现很多乱码
        this.redisTemplate.setValueSerializer(new StringRedisSerializer());
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }
}
