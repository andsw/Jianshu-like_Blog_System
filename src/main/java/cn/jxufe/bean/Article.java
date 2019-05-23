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
    private String articleCorpusName;
    /**
     * 默认原创
     * 0原创，1转载，2翻译
     */
    private Byte articleType;
    private Boolean articleCommentable;
    private Boolean articlePrivate;
    private Integer articleReadNum;
    private Integer articleLikeNum;
    private Integer articleCommentNum;
    private Integer articleCollectNum;
    private Integer articleWordNum;
    private Integer articleTagNum;
}

