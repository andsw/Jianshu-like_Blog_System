package cn.jxufe.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

/**
 * @ClassName: ArticleContentDao
 * @author: hsw
 * @date: 2019/5/24 21:25
 * @Description: TODO
 */
public interface ArticleContentDao {
    /**
     * 获取文章内容
     * @param articleNo
     * @return
     * @throws DataAccessException
     */
    String getContentByArticleNo(int articleNo) throws DataAccessException;

    /**
     * 新建了一个博客后，在文章基本信息表中添加信息的同时新建一个空内容的内容记录
     * @param articleNo
     * @return
     */
    int insertEmptyContent(int articleNo);

    /**
     * 更新文章内容，后面优化可以部分更新，追加等方式使其更快！
     * sql中判断当前用户是否拥有这篇需要更新的文章
     * @param articleContent
     * @param articleNo
     * @param currentUserNo
     * @return
     */
    int updateContentByArticleNo(@Param("articleContent") String articleContent, @Param("articleNo") int articleNo,
                                 @Param("currentUserNo") int currentUserNo);

    /**
     * 删除文章时删除内容!
     * @param articleNo
     * @param currentUserNo
     * @return
     */
    int deleteContentByArticleNo(@Param("articleNo") int articleNo, @Param("currentUserNo") int currentUserNo);
}
