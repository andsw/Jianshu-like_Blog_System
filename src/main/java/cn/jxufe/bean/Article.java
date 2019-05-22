package cn.jxufe.bean;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName: Article
 * @author: hsw
 * @date: 2019/4/2 15:25
 * @Description: 文章类型
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Article implements Serializable {

    private static final long serialVersionUID = -683719417348335062L;
    private Integer articleNo;
    private Integer userNo;
    private String articleTitle;
    private String articleSummary;
    private String articleImg;
    private java.sql.Timestamp articleReleaseTime;
    private Integer articleCorpusId;
    private String articleType;
    private String articleCommentable;
    private String articlePrivate;
    private Integer articleReadNum;
    private Integer articleLikeNum;
    private Integer articleCommentNum;
    private Integer articleCollectNum;
    private Integer articleWordNum;
    private Integer articleTagNum;

    public Article(String articleTitle, String articleSummary, String articleImg,
                   Timestamp articleReleaseTime, String articlePrivate, Integer articleReadNum,
                   Integer articleLikeNum, Integer articleCommentNum) {
        this.articleTitle = articleTitle;
        this.articleSummary = articleSummary;
        this.articleImg = articleImg;
        this.articleReleaseTime = articleReleaseTime;
        this.articlePrivate = articlePrivate;
        this.articleReadNum = articleReadNum;
        this.articleLikeNum = articleLikeNum;
        this.articleCommentNum = articleCommentNum;
    }


}

