package cn.jxufe.bean;

/**
 * @ClassName: User
 * @author: hsw
 * @date: 2019/3/30 19:59
 * @Description: TODO
 */
public class User {
    private int uid;
    private String username;
    private String password;
    private String tel;
    private String avatar;

    public User() {
    }

    public User(int uid, String username, String password, String tel, String avatar) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.avatar = avatar;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
