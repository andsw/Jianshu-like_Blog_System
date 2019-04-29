package cn.jxufe.bean;

import java.io.Serializable;

/**
 * @ClassName: Corpus
 * @author: hsw
 * @date: 2019/4/29 10:32
 * @Description: 文集bean，属性分别是序号，文集名，所属用户序号，包含文章数！
 */
public class Corpus implements Serializable {
    private static final long serialVersionUID = 5179560096777477623L;
    private Integer corpusNo;
    private String corpusName;
    private Integer userNO;
    private Integer blogNum;

    public Corpus() {
    }

    public Corpus(int corpusNo, String corpusName, int userNO, int blogNum) {
        this.corpusNo = corpusNo;
        this.corpusName = corpusName;
        this.userNO = userNO;
        this.blogNum = blogNum;
    }

    public int getCorpusNo() {
        return corpusNo;
    }

    public void setCorpusNo(Integer corpusNo) {
        this.corpusNo = corpusNo;
    }

    public String getCorpusName() {
        return corpusName;
    }

    public void setCorpusName(String corpusName) {
        this.corpusName = corpusName;
    }

    public Integer getUserNO() {
        return userNO;
    }

    public void setUserNO(Integer userNO) {
        this.userNO = userNO;
    }

    public Integer getBlogNum() {
        return blogNum;
    }

    public void setBlogNum(Integer blogNum) {
        this.blogNum = blogNum;
    }

    @Override
    public String toString() {
        return "Corpus{" +
                "corpusNo=" + corpusNo +
                ", corpusName='" + corpusName + '\'' +
                ", userNO=" + userNO +
                ", blogNum=" + blogNum +
                '}';
    }
}
