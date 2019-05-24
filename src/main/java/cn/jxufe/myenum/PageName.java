package cn.jxufe.myenum;

import lombok.Getter;

/**
 * 表示某些页面对应的码和描述信息
 * @author hsw
 * @create 2019-05-13  15:08
 */
@Getter
public enum PageName {

    /**
     * 个人主页、 登录页面、注册页面
     */
    HOME_PAGE("/front_end/index.html"), LOGIN_PAGE("/front_end/loginAndRegister.html");

    private static final String SERVER_DOMAIN_PORT = "http://localhost:8080";

    private String pageUrl;

    PageName(String pageUrl) {
        this.pageUrl = SERVER_DOMAIN_PORT + pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
