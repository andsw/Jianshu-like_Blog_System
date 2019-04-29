package cn.jxufe.bean;

import java.io.Serializable;

/**
 * @ClassName: Follow
 * @author: hsw
 * @date: 2019/4/29 11:15
 * @Description: TODO
 */
public class Follow implements Serializable {
    private static final long serialVersionUID = -4675113176798390400L;
    private Integer followedUserNo;
    private Integer followerUserNo;

    public Follow() {
    }

    public Follow(Integer followedUserNo, Integer followerUserNo) {
        this.followedUserNo = followedUserNo;
        this.followerUserNo = followerUserNo;
    }

    public Integer getFollowedUserNo() {
        return followedUserNo;
    }

    public void setFollowedUserNo(Integer followedUserNo) {
        this.followedUserNo = followedUserNo;
    }

    public Integer getFollowerUserNo() {
        return followerUserNo;
    }

    public void setFollowerUserNo(Integer followerUserNo) {
        this.followerUserNo = followerUserNo;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "followedUserNo=" + followedUserNo +
                ", followerUserNo=" + followerUserNo +
                '}';
    }
}
