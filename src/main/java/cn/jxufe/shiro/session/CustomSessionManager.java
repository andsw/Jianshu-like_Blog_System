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

    /**
     * 获取session（此方法做了些许优化，因为可能会重复多次访问redis，
     * 所以读取出session就把他放在request中，这样反复读取也不怕会有性能问题了！
     * @param sessionKey session的名称
     * @return 返回的是相应的session！
     * @throws UnknownSessionException 没有session异常
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = getSessionId(sessionKey);
        // 获取request
        HttpServletRequest request = (HttpServletRequest) ((WebSessionKey) sessionKey).getServletRequest();

        // 如果request中获取的相应session不为空，则就从request中取！
        Session session;
        if (request != null && sessionId != null) {
            session = (Session) request.getAttribute(sessionId.toString());
            if (session != null) {
                return session;
            }
        }
        System.out.println("get session from redis!");
        // 从redis中取出后放入request中！
        session = super.retrieveSession(sessionKey);
        if (request != null && session != null) {
            assert sessionId != null;
            request.setAttribute(sessionId.toString(), session);
        }

        return session;
    }
}
