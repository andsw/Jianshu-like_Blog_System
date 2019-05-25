package cn.jxufe.dao;

import cn.jxufe.bean.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: ArticleInfoDao
 * @author: hsw
 * @date: 2019/5/19 21:51
 * @Description: 文章信息（不包含文章内容）
 */
public interface ArticleInfoDao {
    /**
     * 获取用户所有文章
     * @param currentUserNo
     * @param articlePrivate
     * @param articleNum
     * @return
     */
    List<Article> getArticleByUserNo(@Param("userNo") int currentUserNo,
                                     @Param("articlePrivate") boolean articlePrivate,
                                     @Param("articleNum") int articleNum);

    /**
     * 获取文集所有文章
     *
     * @param userNo
     * @param articleCorpusName
     * @param articlePrivate
     * @return
     */
    List<Article> getArticleByUserNoAndCorpusName(@Param("userNo") int userNo,
                                                  @Param("articleCorpusName") String articleCorpusName,
                                                  @Param("articlePrivate") boolean articlePrivate);


    /**
     * 添加文章信息,注意此操作是在点击新建文章选项后就触发的，所以现在预先填入的
     * 属性只有：所属文集名称，用户号，其他默认就好，待编辑文章时再一一修改
     * @param article
     * @return
     */
    int insertArticleInfo(@Param("article") Article article);

    /**
     * 添加文章结束后更新信息，包括字段：article_title， summary，img， type， commentable。
     * 当然现在还不确定是不是分步进行，比如更新标题、生成summary和提取img是否可以分成单步进行。后面再说
     * @param article
     * @return
     */
    int updateOriginInfoByArticleNo(@Param("article") Article article);

    /**
     * 删除文章信息
     * @param articleNo
     * @param currentUserNo
     * @return
     */
    int deleteInfoByArticleNo(@Param("articleNo") int articleNo, @Param("currentUserNo") int currentUserNo);
}
