package cn.jxufe.service;

/**
 * @ClassName: ArticleContentService
 * @author: hsw
 * @date: 2019/5/24 21:34
 * @Description: 纯粹作用于文章内容
 */
public interface ArticleContentService {

    /**
     * 获取内容
     * @param articleNo
     * @return
     */
    String getContentByArticleNo(int articleNo);

    /**
     * 更新文章内容
     * 后期优化，可在编辑途中分步进行！或者先保存在本地浏览器缓存中！
     * @param content
     * @param articleNo
     * @param currentUserNo
     * @return
     */
    boolean updateContentByArticleNo(String content, int articleNo, int currentUserNo);
}
