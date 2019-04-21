package cn.jxufe.bean;

/**
 * @ClassName: User
 * @author: hsw
 * @date: 2019/3/30 19:59
 * @Description: 用户账号信息
 */
public class User {
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
     * email为盐值加密后的密码
     */
    private String passwordEmail;
    /**
     * tel为盐值加密后的密码
     */
    private String passwordTel;

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

    public User() {
    }

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
     */
    public User(Integer userNo, String username, String email, String tel, String avatar, String selfSummary, Byte gender, Integer followNum, Integer followerNum, Integer blogNum, Long wordNum, Integer likeNum) {
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
     * @param passwordEmail
     * @param passwordTel
     */
    public User(String username, String email, String tel, String avatar, String passwordEmail, String passwordTel) {
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.avatar = avatar;
        this.passwordEmail = passwordEmail;
        this.passwordTel = passwordTel;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSelfSummary() {
        return selfSummary;
    }

    public void setSelfSummary(String selfSummary) {
        this.selfSummary = selfSummary;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public Integer getFollowerNum() {
        return followerNum;
    }

    public void setFollowerNum(Integer followerNum) {
        this.followerNum = followerNum;
    }

    public Integer getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(Integer blogNum) {
        this.blogNum = blogNum;
    }

    public Long getWordNum() {
        return wordNum;
    }

    public void setWordNum(Long wordNum) {
        this.wordNum = wordNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public String getPasswordEmail() {
        return passwordEmail;
    }

    public void setPasswordEmail(String passwordEmail) {
        this.passwordEmail = passwordEmail;
    }

    public String getPasswordTel() {
        return passwordTel;
    }

    public void setPasswordTel(String passwordTel) {
        this.passwordTel = passwordTel;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", avatar='" + avatar + '\'' +
                ", selfSummary='" + selfSummary + '\'' +
                ", gender=" + gender +
                ", followNum=" + followNum +
                ", followerNum=" + followerNum +
                ", blogNum=" + blogNum +
                ", wordNum=" + wordNum +
                ", likeNum=" + likeNum +
                ", passwordEmail='" + passwordEmail + '\'' +
                ", passwordTel='" + passwordTel + '\'' +
                '}';
    }
}
