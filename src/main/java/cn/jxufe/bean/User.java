package cn.jxufe.bean;

/**
 * @ClassName: User
 * @author: hsw
 * @date: 2019/3/30 19:59
 * @Description: 用户账号信息
 */
public class User {
    private Integer userNo;
    private String username;
    private String email;
    private String tel;
    private String password;
    private String avatar;
    private String selfSummary;
    private Byte gender;
    private Integer followNum;
    private Integer followerNum;
    private Integer blogNum;
    private Long wordNum;
    private Integer likeNum;

    public User() {
    }

    public User(Integer userNo, String username, String email, String tel, String password, String avatar, String selfSummary, Byte gender, Integer followNum, Integer followerNum, Integer blogNum, Long wordNum, Integer likeNum) {
        this.userNo = userNo;
        this.username = username;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.avatar = avatar;
        this.selfSummary = selfSummary;
        this.gender = gender;
        this.followNum = followNum;
        this.followerNum = followerNum;
        this.blogNum = blogNum;
        this.wordNum = wordNum;
        this.likeNum = likeNum;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", selfSummary='" + selfSummary + '\'' +
                ", gender=" + gender +
                ", followNum=" + followNum +
                ", followerNum=" + followerNum +
                ", blogNum=" + blogNum +
                ", wordNum=" + wordNum +
                ", likeNum=" + likeNum +
                '}';
    }
}
