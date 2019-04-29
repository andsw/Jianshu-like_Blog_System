package cn.jxufe.bean;

import java.io.Serializable;

/**
 * @ClassName: Article
 * @author: hsw
 * @date: 2019/4/2 15:30
 * @Description: 文章内容对象
 */
public class ArticleContent implements Serializable {

    private static final long serialVersionUID = 6981398832660039328L;
    private Integer articleNo;
    private String content;

    public ArticleContent() {
    }

    public ArticleContent(Integer articleNo, String content) {
    this.articleNo = articleNo;
    this.content = content;
    }

    public Integer getArticleNo() {
    return articleNo;
    }

    public void setArticleNo(Integer articleNo) {
    this.articleNo = articleNo;
    }

    public String getContent() {
    return content;
    }

    public void setContent(String content) {
    this.content = content;
    }

}
