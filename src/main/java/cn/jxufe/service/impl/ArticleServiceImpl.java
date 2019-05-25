package cn.jxufe.service.impl;

import cn.jxufe.bean.Article;
import cn.jxufe.dao.ArticleContentDao;
import cn.jxufe.dao.ArticleInfoDao;
import cn.jxufe.exception.InsertDbException;
import cn.jxufe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: ArticleServiceImpl
 * @author: hsw
 * @date: 2019/5/25 14:17
 * @Description: 同属作用于文章信息和内容的服务类
 */
@CacheConfig(cacheNames = "redisCache")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleContentDao articleContentDao;
    @Autowired
    private ArticleInfoDao articleInfoDao;

    /**
     * 先不管缓存
     * @param article
     * @return
     * @throws InsertDbException
     */
    @Override
//    @Caching(evict = {@CacheEvict("'users-' + #article.userNo + '-private-' + #article.articlePrivate"),
//            @CacheEvict(key = "'users-' + #article.userNo + '-corpusName-' + #article.articleCorpusName " +
//                    "+ '-private-' + #article.articlePrivate")})
    @Transactional(rollbackFor = Exception.class)
    public Integer createArticle(Article article) throws InsertDbException {
        int result1 = articleInfoDao.insertArticleInfo(article);
        int result2 = articleContentDao.insertEmptyContent(article.getArticleNo());
        if (result1 != 1 || result2 != 1 || article.getArticleNo() == null) {
            throw new InsertDbException();
        }
        return article.getArticleNo();
    }

    /**
     * 先不管缓存一致性，后面临近上线再处理
     * @param articleNo
     * @param currentUserNo
     * @return
     */
    @Override
    public boolean deleteArticle(int articleNo, int currentUserNo) {
        return articleContentDao.deleteContentByArticleNo(articleNo, currentUserNo)
                + articleInfoDao.deleteInfoByArticleNo(articleNo, currentUserNo) == 2;
    }
}
