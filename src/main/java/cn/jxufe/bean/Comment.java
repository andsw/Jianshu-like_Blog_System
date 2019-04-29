package cn.jxufe.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName: Comment
 * @author: hsw
 * @date: 2019/4/29 11:17
 * @Description: 联系集评论对应的bean
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = -4786247565308510209L;
    private Integer commentNo;
    private String commentContent;
    private Integer likeNum;
    private Integer userNo;
    private Integer articleNO;
    private Timestamp commentTime;
    private Integer replyCommentNo;
    private Integer commentLevel;

    public Comment() {
    }

    public Comment(Integer commentNo, String commentContent,
                   Integer likeNum, Integer userNo, Integer articleNO,
                   Timestamp commentTime, Integer replyCommentNo, Integer commentLevel) {
        this.commentNo = commentNo;
        this.commentContent = commentContent;
        this.likeNum = likeNum;
        this.userNo = userNo;
        this.articleNO = articleNO;
        this.commentTime = commentTime;
        this.replyCommentNo = replyCommentNo;
        this.commentLevel = commentLevel;
    }

    public Integer getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Integer commentNo) {
        this.commentNo = commentNo;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getArticleNO() {
        return articleNO;
    }

    public void setArticleNO(Integer articleNO) {
        this.articleNO = articleNO;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getReplyCommentNo() {
        return replyCommentNo;
    }

    public void setReplyCommentNo(Integer replyCommentNo) {
        this.replyCommentNo = replyCommentNo;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentNo=" + commentNo +
                ", commentContent='" + commentContent + '\'' +
                ", likeNum=" + likeNum +
                ", userNo=" + userNo +
                ", articleNO=" + articleNO +
                ", commentTime=" + commentTime +
                ", replyCommentNo=" + replyCommentNo +
                ", commentLevel=" + commentLevel +
                '}';
    }
}
