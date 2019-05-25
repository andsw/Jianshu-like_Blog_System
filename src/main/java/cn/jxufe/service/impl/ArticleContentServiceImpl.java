package cn.jxufe.service.impl;

import cn.jxufe.dao.ArticleContentDao;
import cn.jxufe.service.ArticleContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;

/**
 * @ClassName: ArticleContentServiceImpl
 * @author: hsw
 * @date: 2019/5/24 21:34
 * @Description: 纯粹作用于文章内容
 */
@CacheConfig(cacheNames = "redisCache")
public class ArticleContentServiceImpl implements ArticleContentService {
    @Autowired
    private ArticleContentDao articleContentDao;

    @Override
    @Cacheable(key = "'articleContent-'+#articleNo")
    public String getContentByArticleNo(int articleNo) {
        try {
            return articleContentDao.getContentByArticleNo(articleNo);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    @CacheEvict(key = "'articleContent-'+#articleNo")
    public boolean updateContentByArticleNo(String content, int articleNo, int currentUserNo) {
        return articleContentDao.updateContentByArticleNo(content, articleNo, currentUserNo) == 1;
    }

}
