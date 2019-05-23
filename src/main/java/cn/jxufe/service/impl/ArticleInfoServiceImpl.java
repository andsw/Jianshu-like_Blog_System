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
    @Cacheable(key = "'public-' + #userNo")
    public List<Article> getPublicArticlesInfoByUserNo(int userNo, int publicArticleNum) {
        return articleDao.getPublicArticleByUserNo(userNo, publicArticleNum);
    }

    @Override
    @Cacheable(value = "redisCache", key = "'private-' + #currentUserNo")
    public List<Article> getPrivateArticlesInfoByUserNo(int currentUserNo, int privateArticleNum) {
        return articleDao.getPrivateArticleByUserNo(currentUserNo, privateArticleNum);
    }

}
