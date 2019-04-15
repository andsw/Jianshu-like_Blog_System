package cn.jxufe.shiro.session;

import cn.jxufe.util.SerializingUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;

/**
 * @ClassName: RedisSessionDAO
 * @author: hsw
 * @date: 2019/4/12 11:14
 * @Description: TODO
 */

public class RedisSessionDAO extends AbstractSessionDAO {

    @Autowired
    private SerializingUtil serializingUtil;

    @Autowired
    private RedisTemplate<String, Byte[]> redisTemplate;

    private Serializable saveSession(String sessionId, Session session) {
        System.out.println("session in json format : \n"+ JSON.toJSON(session));

        assignSessionId(session, sessionId);
        redisTemplate.opsForValue().set(sessionId, serializingUtil.serialized((SimpleSession)session));
        return sessionId;
    }

    @Override
    protected Serializable doCreate(Session session) {
        System.out.println("\n\n----------------doCreate method in sessionDAO----------------");

        String sessionId = (String) generateSessionId(session);
        return saveSession(sessionId, session);
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        System.out.println("\n\n----------------doRead method in sessionDAO----------------");
        if (serializable == null) {
            return null;
        }
        SimpleSession simpleSession = null;
        Byte[] sessionBytes = redisTemplate.opsForValue().get(serializable.toString());
        if (sessionBytes != null) {
             simpleSession = (SimpleSession) serializingUtil.unSerialized(sessionBytes);
        }
        if (simpleSession != null) {
            assignSessionId(simpleSession, simpleSession.getId());
        }
        return simpleSession;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        System.out.println("\n\n----------------update method in sessionDAO!----------------");
        String sessionId = (String) session.getId();
        saveSession(sessionId, session);
    }

    @Override
    public void delete(Session session) {

    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }
}