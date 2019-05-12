package cn.jxufe.bean;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName: Comment
 * @author: hsw
 * @date: 2019/4/29 11:17
 * @Description: 联系集评论对应的bean
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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
}
