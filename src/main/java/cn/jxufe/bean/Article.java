package cn.jxufe.bean;

import java.sql.Timestamp;

/**
 * @ClassName: Article
 * @author: hsw
 * @date: 2019/4/2 15:25
 * @Description: 文章类型
 */
public class Article {

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

    public Article() {
    }

    public Article(Integer articleNo, Integer userNo, String articleTitle,
                   String articleSummary, String articleImg, Timestamp articleReleaseTime,
                   Integer articleCorpusId, String articleType, String articleCommentable,
                   String articlePrivate, Integer articleReadNum, Integer articleLikeNum,
                   Integer articleCommentNum, Integer articleCollectNum,
                   Integer articleWordNum, Integer articleTagNum) {
        this.articleNo = articleNo;
        this.userNo = userNo;
        this.articleTitle = articleTitle;
        this.articleSummary = articleSummary;
        this.articleImg = articleImg;
        this.articleReleaseTime = articleReleaseTime;
        this.articleCorpusId = articleCorpusId;
        this.articleType = articleType;
        this.articleCommentable = articleCommentable;
        this.articlePrivate = articlePrivate;
        this.articleReadNum = articleReadNum;
        this.articleLikeNum = articleLikeNum;
        this.articleCommentNum = articleCommentNum;
        this.articleCollectNum = articleCollectNum;
        this.articleWordNum = articleWordNum;
        this.articleTagNum = articleTagNum;
    }

    public Integer getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(Integer articleNo) {
        this.articleNo = articleNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public Timestamp getArticleReleaseTime() {
        return articleReleaseTime;
    }

    public void setArticleReleaseTime(Timestamp articleReleaseTime) {
        this.articleReleaseTime = articleReleaseTime;
    }

    public Integer getArticleCorpusId() {
        return articleCorpusId;
    }

    public void setArticleCorpusId(Integer articleCorpusId) {
        this.articleCorpusId = articleCorpusId;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleCommentable() {
        return articleCommentable;
    }

    public void setArticleCommentable(String articleCommentable) {
        this.articleCommentable = articleCommentable;
    }

    public String getArticlePrivate() {
        return articlePrivate;
    }

    public void setArticlePrivate(String articlePrivate) {
        this.articlePrivate = articlePrivate;
    }

    public Integer getArticleReadNum() {
        return articleReadNum;
    }

    public void setArticleReadNum(Integer articleReadNum) {
        this.articleReadNum = articleReadNum;
    }

    public Integer getArticleLikeNum() {
        return articleLikeNum;
    }

    public void setArticleLikeNum(Integer articleLikeNum) {
        this.articleLikeNum = articleLikeNum;
    }

    public Integer getArticleCommentNum() {
        return articleCommentNum;
    }

    public void setArticleCommentNum(Integer articleCommentNum) {
        this.articleCommentNum = articleCommentNum;
    }

    public Integer getArticleCollectNum() {
        return articleCollectNum;
    }

    public void setArticleCollectNum(Integer articleCollectNum) {
        this.articleCollectNum = articleCollectNum;
    }

    public Integer getArticleWordNum() {
        return articleWordNum;
    }

    public void setArticleWordNum(Integer articleWordNum) {
        this.articleWordNum = articleWordNum;
    }

    public Integer getArticleTagNum() {
        return articleTagNum;
    }

    public void setArticleTagNum(Integer articleTagNum) {
        this.articleTagNum = articleTagNum;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleNo=" + articleNo +
                ", userNo=" + userNo +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleSummary='" + articleSummary + '\'' +
                ", articleImg='" + articleImg + '\'' +
                ", articleReleaseTime=" + articleReleaseTime +
                ", articleCorpusId=" + articleCorpusId +
                ", articleType='" + articleType + '\'' +
                ", articleCommentable='" + articleCommentable + '\'' +
                ", articlePrivate='" + articlePrivate + '\'' +
                ", articleReadNum=" + articleReadNum +
                ", articleLikeNum=" + articleLikeNum +
                ", articleCommentNum=" + articleCommentNum +
                ", articleCollectNum=" + articleCollectNum +
                ", articleWordNum=" + articleWordNum +
                ", articleTagNum=" + articleTagNum +
                '}';
    }
}

