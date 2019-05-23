package cn.jxufe.dao;

import cn.jxufe.bean.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: ArticleDao
 * @author: hsw
 * @date: 2019/5/19 21:51
 * @Description: 文章信息（不包含文章内容）
 */
public interface ArticleDao {

    /**
     * 获取私密文章
     * @param currentUserNo
     * @param privateArticleNum
     * @return
     */
    List<Article> getPrivateArticleByUserNo(@Param("currentUserNo") int currentUserNo, @Param("privateArticleNum") int privateArticleNum);

    /**
     * 只获取某用户的所有公开文章
     * @param userNo
     * @param publicArticleNum
     * @return
     */
    List<Article> getPublicArticleByUserNo(@Param("userNo") int userNo, @Param("publicArticleNum") int publicArticleNum);

    /**
     * 添加文章信息,注意此操作是在点击新建文章选项后就触发的，所以现在预先填入的
     * 属性只有：所属文集名称，用户号，其他默认就好，待编辑文章时再一一修改
     * @param article
     * @return
     */
    int insertArticleInfo(@Param("article") Article article);
}
