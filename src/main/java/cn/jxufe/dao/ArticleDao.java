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
     * 进入用户主页时，根据userNo和文章数获取所有本用户文章列表！
     * （注意这里是获取所有，也分为私密文章和公开文章，所以在服务类和controller类中可以分开！）
     * @param userNO
     * @param articleNum
     * @return
     */
    List<Article> getArticleByUserNo(@Param("userNo") int userNO, @Param("article") int articleNum);

    int insertArticleInfo(Article article);
}
