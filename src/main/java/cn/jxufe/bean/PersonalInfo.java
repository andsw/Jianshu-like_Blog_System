package cn.jxufe.bean;

import java.io.Serializable;

/**
 * @ClassName: PersonalInfo
 * @author: hsw
 * @date: 2019/4/2 11:56
 * @Description: TODO
 */
public class PersonalInfo implements Serializable {
    private static final long serialVersionUID = 5695563660997682977L;
    private Integer userNo;
    private String name;
    private String wechatQRLink;
    private String github;

    public PersonalInfo() {
    }

    public PersonalInfo(Integer userNo, String name, String wechatQRLink, String github) {
        this.userNo = userNo;
        this.name = name;
        this.wechatQRLink = wechatQRLink;
        this.github = github;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWechatQRLink() {
        return wechatQRLink;
    }

    public void setWechatQRLink(String wechatQRLink) {
        this.wechatQRLink = wechatQRLink;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "userNo=" + userNo +
                ", name='" + name + '\'' +
                ", wechatQRLink='" + wechatQRLink + '\'' +
                ", github='" + github + '\'' +
                '}';
    }
}
