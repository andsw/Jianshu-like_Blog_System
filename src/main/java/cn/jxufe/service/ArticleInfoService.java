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
    List<Article> getArticlesInfoByUserNo(int userNo, int publicArticleNum, boolean articlePrivate);

    /**
     * 获取文集中文章列表.
     *
     * @param userNo
     * @param articleCorpusName
     * @param articlePrivate
     * @return
     */
    List<Article> getArticlesInfoByUserNoAndCorpusNum(int userNo, String articleCorpusName, boolean articlePrivate);

    /**
     * 添加文章基本信息
     * @param article
     * @return 返回的是生成的主键，如果发生错误返回-1
     */
    Integer insertArticleInfo(Article article);

    /**
     * 在编辑文章后将文章具体信息更新至数据库！
     * @param article
     * @return
     */
    boolean updateArticleInfoAfterWriting(Article article);
}
