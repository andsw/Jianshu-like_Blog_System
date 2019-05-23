package cn.jxufe.service;

import cn.jxufe.bean.Article;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @ClassName: ArticleInfoService
 * @author: hsw
 * @date: 2019/5/23 14:59
 * @Description: TODO
 */
public interface ArticleInfoService {

    /**
     * 获取公开文章
     * @param userNo
     * @param publicArticleNum
     * @return
     */
    List<Article> getPublicArticlesInfoByUserNo(int userNo, int publicArticleNum);

    /**
     * 获取私密文章
     * @param currentUserNo
     * @param privateArticleNum
     * @return
     */
    List<Article> getPrivateArticlesInfoByUserNo(int currentUserNo, int privateArticleNum);
}
