package cn.jxufe.service;

import cn.jxufe.bean.Article;

import java.util.List;

/**
 * @ClassName: ArticleInfoService
 * @author: hsw
 * @date: 2019/5/23 14:59
 * @Description: TODO
 */
public interface ArticleInfoService {
    /**
     * 获取文章
     * @param userNo
     * @param publicArticleNum
     * @param articlePrivate
     * @return
     */
    List<Article> getArticlesInfoByUserNo(int userNo, int publicArticleNum, byte articlePrivate);

    /**
     * 获取文集中文章列表.
     * @param userNo
     * @param articleCorpusName
     * @param articlePrivate
     * @return
     */
    List<Article> getArticlesInfoByUserNoAndCorpusNum(int userNo, String articleCorpusName, byte articlePrivate);
}
