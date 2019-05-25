package cn.jxufe.service.impl;

import cn.jxufe.bean.Article;
import cn.jxufe.dao.ArticleInfoDao;
import cn.jxufe.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;

import java.util.List;

/**
 * @ClassName: ArticleInfoServiceImpl
 * @author: hsw
 * @date: 2019/5/23 14:59
 * @Description: 纯粹作用于文章基本信息部分
 */
@CacheConfig(cacheNames = "redisCache")
public class ArticleInfoServiceImpl implements ArticleInfoService {

    @Autowired
    private ArticleInfoDao articleInfoDao;

    @Override
    @Cacheable(key = "'users-' + #userNo + '-private-' + #articlePrivate")
    public List<Article> getArticlesInfoByUserNo(int userNo, int articleNum, boolean articlePrivate) {
        return articleInfoDao.getArticleByUserNo(userNo, articlePrivate, articleNum);
    }

    @Override
    @Cacheable(key = "'users-' + #userNo + '-corpusName-' + #articleCorpusName + '-private-' + #articlePrivate")
    public List<Article> getArticlesInfoByUserNoAndCorpusNum(int userNo, String articleCorpusName, boolean articlePrivate) {
        return articleInfoDao.getArticleByUserNoAndCorpusName(userNo, articleCorpusName, articlePrivate);
    }

    /**
     * 这里修改的信息不会显示到文章信息列表中所以不用更新前面的缓存！
     * 但别忘了要更新文章显示区的缓存！
     * @param article
     * @return
     */
    @Override
    public boolean updateArticleInfoAfterWriting(Article article) {
        return articleInfoDao.updateOriginInfoByArticleNo(article) == 1;
    }
}
