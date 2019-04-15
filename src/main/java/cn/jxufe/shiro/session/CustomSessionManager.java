package cn.jxufe.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @ClassName: CustomSessionManager
 * @author: hsw
 * @date: 2019/4/12 11:14
 * @Description: TODO
 */
public class CustomSessionManager extends DefaultWebSessionManager {



    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        System.out.println("\n\n----------------进入retrieveSessionMethod!----------------");
        Serializable sessionId = getSessionId(sessionKey);
        HttpServletRequest request = (HttpServletRequest) ((WebSessionKey) sessionKey).getServletRequest();

        System.out.println("sessionId is ： " + sessionId);

        Session session;
        if (request != null && sessionId != null) {
            session = (Session) request.getAttribute(sessionId.toString());
            if (session != null) {
                System.out.println("get session from request!");
                return session;
            }
        }
        System.out.println("get session from redis!");
        session = super.retrieveSession(sessionKey);
        if (request != null && session != null) {
            assert sessionId != null;
            request.setAttribute(sessionId.toString(), session);
        }

        return session;
    }
}
