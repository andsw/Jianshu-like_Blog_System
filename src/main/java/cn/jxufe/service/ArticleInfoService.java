package cn.jxufe.service;

import cn.jxufe.bean.Article;

import java.util.List;

/**
 * @ClassName: ArticleInfoService
 * @author: hsw
 * @date: 2019/5/23 14:59
 * @Description: 纯粹作用于文章基本信息部分
 */
public interface ArticleInfoService {
    /**
     * 分页获取本用户所有文章信息
     * 因为每页数量默认6.所以不用缓存这个信息
     * @param currentUserNo
     * @param articleNumPerPage
     * @param offset
     * @return
     */
    List<Article> getOwnArticle(int currentUserNo, int articleNumPerPage, int offset);

    /**
     * 获取文章信息列表
     * 分页获取
     * @param userNo
     * @param articleNumPerPage
     * @param articlePrivate
     * @param offset
     * @return
     */
    List<Article> getArticlesInfoByUserNo(int userNo, int articleNumPerPage, boolean articlePrivate, int offset);

    /**
     * 获取文集中文章列表.
     * 因为文集中文章一般较少，所以先不分页！
     * @param userNo
     * @param articleCorpusName
     * @param articlePrivate
     * @return
     */
    List<Article> getArticlesInfoByUserNoAndCorpusNum(int userNo, String articleCorpusName, boolean articlePrivate);

    /**
     * 在编辑文章后将文章具体信息更新至数据库！
     * @param article
     * @return
     */
    boolean updateArticleInfoAfterWriting(Article article);
}
