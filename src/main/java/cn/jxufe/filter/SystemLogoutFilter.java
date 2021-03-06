package cn.jxufe.filter;

import cn.jxufe.myenum.PageName;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ClassName: SystemLogoutFilter
 * @author: hsw
 * @date: 2019/5/13 14:11
 * @Description: TODO
 */
public class SystemLogoutFilter extends LogoutFilter {

    private static final String LOGIN_PAGE_URL = PageName.LOGIN_PAGE.getPageUrl();

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        Subject currentUser = SecurityUtils.getSubject();
        //logout会在登出时删除session！
        currentUser.logout();

        return false;
    }
}
