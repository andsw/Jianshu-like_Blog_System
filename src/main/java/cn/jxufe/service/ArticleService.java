package cn.jxufe.service;

import cn.jxufe.bean.Article;
import cn.jxufe.exception.InsertDbException;

/**
 * @ClassName: ArticleService
 * @author: hsw
 * @date: 2019/5/25 14:17
 * @Description: 同属作用于文章信息和内容的服务类
 */
public interface ArticleService {

    /**
     * 添加文章基本信息，然后用生成的主键往文章内容表中添加一个文章内容记录！
     * @param article
     * @return 返回的是生成的主键
     * @throws InsertDbException 自定义异常，抛出事务即回滚
     */
    Integer createArticle(Article article) throws InsertDbException;

    /**
     * 删除基本信息和内容记录
     *
     * @param articleNo
     * @param currentUserNo
     * @return 返回删除结果！
     */
    boolean deleteArticle(int articleNo, int currentUserNo);
}
