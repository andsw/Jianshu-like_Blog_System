package cn.jxufe.service.impl;

import cn.jxufe.bean.Article;
import cn.jxufe.dao.ArticleDao;
import cn.jxufe.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;

import java.util.List;

/**
 * @ClassName: ArticleInfoServiceImpl
 * @author: hsw
 * @date: 2019/5/23 14:59
 * @Description: TODO
 */
@CacheConfig(cacheNames = "redisCache")
public class ArticleInfoServiceImpl implements ArticleInfoService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    @Cacheable(key = "'users-' + #userNo + '-private-' + #articlePrivate")
    public List<Article> getArticlesInfoByUserNo(int userNo, int articleNum, boolean articlePrivate) {
        return articleDao.getArticleByUserNo(userNo, articlePrivate, articleNum);
    }

    @Override
    @Cacheable(key = "'users-' + #userNo + '-corpusName-' + #articleCorpusName + '-private-' + #articlePrivate")
    public List<Article> getArticlesInfoByUserNoAndCorpusNum(int userNo, String articleCorpusName, boolean articlePrivate) {
        return articleDao.getArticleByUserNoAndCorpusName(userNo, articleCorpusName, articlePrivate);
    }

    /**
     * 添加文章基本信息
     * 缓存清除的两个键的值，一个是用户全部文章的信息，一个是新增文章所在文集的所有文章列表的缓存！
     * @param article
     * @return 返回的是生成的主键，如果发生错误返回-1
     */
    @Override
    @Caching(evict = {@CacheEvict("'users-' + #article.userNo + '-private-' + #article.articlePrivate"),
                      @CacheEvict(key = "'users-' + #article.userNo + '-corpusName-' + #article.articleCorpusName " +
                              "+ '-private-' + #article.articlePrivate")})
    public Integer insertArticleInfo(Article article) {
        return articleDao.insertArticleInfo(article) == 1 ?
                article.getArticleNo() : -1;
    }

    /**
     * 这里修改的信息不会显示到文章信息列表中所以不用更新前面的缓存！
     * 但别忘了要更新文章显示区的缓存！
     * @param article
     * @return
     */
    @Override
    public boolean updateArticleInfoAfterWriting(Article article) {
        return articleDao.updateOriginInfoByArticleNo(article) == 1;
    }
}
