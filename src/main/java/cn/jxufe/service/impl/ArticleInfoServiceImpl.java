package cn.jxufe.service.impl;

import cn.jxufe.bean.Article;
import cn.jxufe.dao.ArticleDao;
import cn.jxufe.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

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
    public List<Article> getArticlesInfoByUserNo(int userNo, int articleNum, byte articlePrivate) {
        return articleDao.getArticleByUserNo(userNo, articlePrivate, articleNum);
    }

    @Override
    @Cacheable(key = "'users-' + #userNo + '-corpusName-' + #articleCorpusName + '-private-' + #articlePrivate")
    public List<Article> getArticlesInfoByUserNoAndCorpusNum(int userNo, String articleCorpusName, byte articlePrivate) {
        return articleDao.getArticleByUserNoAndCorpusName(userNo, articleCorpusName, articlePrivate);
    }
}
