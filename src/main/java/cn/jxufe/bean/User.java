package cn.jxufe.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: User
 * @author: hsw
 * @date: 2019/3/30 19:59
 * @Description: 用户账号信息
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -8986314833392281499L;
    /**
     * 自动生成的主键
     */
    private Integer userNo;
    /**
     * 用户名必须unique，插入用户时注意查重，而且注意发生幻读问题，要用好事务！
     */
    private String username;
    /**
     * 同样需要查重
     */
    private String email;
    /**
     * 查重
     */
    private String tel;
    /**
     * 头像图片域名和path以及前缀，所以这个记录图片后缀及其图片类型就行了
     * 如： 1.png
     */
    private String avatar;

    /**
     * userNo为盐值加密后的密码
     */
    private String password;

    /**
     * 下面所有属性注册时不包括，全部为默认值！
     */
    private String selfSummary;
    private Byte gender;
    private Integer followNum;
    private Integer followerNum;
    private Integer blogNum;
    private Long wordNum;
    private Integer likeNum;

    private String github;
    private String wechatQrImgLink;

    /**
     * 除去密码后的属性值，作为从数据库中获取用户信息的封装构造方法
     * @param userNo
     * @param username
     * @param email
     * @param tel
     * @param avatar
     * @param selfSummary
     * @param gender
     * @param followNum
     * @param followerNum
     * @param blogNum
     * @param wordNum
     * @param likeNum
     * @param github
     * @param wechatQrImgLink
     */
    public User(Integer userNo, String username, String email, String tel, String avatar, String selfSummary, Byte gender, Integer followNum, Integer followerNum, Integer blogNum, Long wordNum, Integer likeNum, String github, String wechatQrImgLink) {
        this.userNo = userNo;
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.avatar = avatar;
        this.selfSummary = selfSummary;
        this.gender = gender;
        this.followNum = followNum;
        this.followerNum = followerNum;
        this.blogNum = blogNum;
        this.wordNum = wordNum;
        this.likeNum = likeNum;
        this.github = github;
        this.wechatQrImgLink = wechatQrImgLink;
    }

    /**
     * insert时封装信息用的构造方法，其他属性（自我简介，性别以及一些数据默认为0）保持默认值,
     * 后面再自行设置
     * 头像随机设置，即从服务器中随机获取一张图片，此业务交给代码，而不是数据库。
     * email和tel注册时只需填一个就行了。不过不知道用户用哪个注册，所以两个都放上去，然后另一个没有值的用空字符串就行了！
     * 两个密码在代码中加密，然后封装
     * @param username
     * @param email
     * @param tel
     * @param avatar
     * @param password
     */
    public User(String username, String email, String tel, String avatar, String password) {
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.avatar = avatar;
        this.password = password;
    }
}
