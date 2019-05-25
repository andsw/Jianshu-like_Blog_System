package cn.jxufe.controller;

import cn.jxufe.bean.Article;
import cn.jxufe.dto.Result;
import cn.jxufe.exception.InsertDbException;
import cn.jxufe.service.ArticleContentService;
import cn.jxufe.service.ArticleInfoService;
import cn.jxufe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName: ArticleInfoController
 * @author: hsw
 * @date: 2019/5/23 14:41
 * @Description: 因为文章信息和文章内容还是有很多联系，所以controller放在一起操作！
 */
@Controller
public class ArticleController {

    private static final boolean ARTICLE_IS_PRIVATE = true;
    private static final boolean ARTICLE_IS_PUBLIC = false;

    private final HttpSession session;
    private final ArticleInfoService articleInfoService;
    private final ArticleContentService articleContentService;
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleContentService articleContentService, HttpSession session, ArticleInfoService articleInfoService, ArticleService articleService) {
        this.articleContentService = articleContentService;
        this.session = session;
        this.articleInfoService = articleInfoService;
        this.articleService = articleService;
    }

    private Integer getUserNoFromSession() {
        return (Integer) session.getAttribute("userNo");
    }

    /**
     * 上面部分是文章信息相关
     * @param userNo
     * @param publicArticleNum
     * @return
     */
    @RequestMapping(value = "/users/{userNo}/article_info/pub", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Article>> getPublicArticleByUserNo(@PathVariable Integer userNo, @RequestParam Integer publicArticleNum) {
        List<Article> publicArticleList = articleInfoService.getArticlesInfoByUserNo(userNo, publicArticleNum, ARTICLE_IS_PUBLIC);
        return publicArticleList == null ?
                Result.fail("获取文章信息列表失败！") : Result.successWithDataOnly(publicArticleList);
    }

    @RequestMapping(value = "/users/article_info/priv", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Article>> getPrivateArticleByUserNo(@RequestParam Integer privateArticleNum) {
        Integer currentUserNo = getUserNoFromSession();
        if (currentUserNo == null) {
            return Result.fail("找不到session！");
        }
        List<Article> privateArticleList = articleInfoService.getArticlesInfoByUserNo(currentUserNo, privateArticleNum, ARTICLE_IS_PRIVATE);
        return privateArticleList == null ? Result.fail("获取私有文章信息列表失败！") : Result.successWithDataOnly(privateArticleList);
    }

    @RequestMapping(value = "/users/{userNo}/corpus/{corpusName}/article_info/pub", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Article>> getPublicArticleByUserNoAndCorpusName(@PathVariable Integer userNo, @PathVariable String corpusName) {
        List<Article> publicCorpusArticles = articleInfoService.getArticlesInfoByUserNoAndCorpusNum(userNo, corpusName, ARTICLE_IS_PUBLIC);
        return publicCorpusArticles == null ? Result.fail("获取文章信息列表失败！") : Result.successWithDataOnly(publicCorpusArticles);
    }

    @RequestMapping(value = "/users/corpus/{articleCorpusName}/article_info/priv", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Article>> getPrivateArticleByUserNoAndCorpusName(@PathVariable String articleCorpusName) {
        Integer currentUserNo = getUserNoFromSession();
        if (currentUserNo == null) {
            return Result.fail("找不到session！");
        }
        List<Article> privateCorpusArticles = articleInfoService.getArticlesInfoByUserNoAndCorpusNum(currentUserNo, articleCorpusName,
                                                                                                                    ARTICLE_IS_PRIVATE);
        return privateCorpusArticles == null ? Result.fail("获取文集文章列表失败！") : Result.successWithDataOnly(privateCorpusArticles);
    }

    @RequestMapping(value = "/users/article_info/{articleNo}", method = RequestMethod.PUT)
    @ResponseBody
    public Result<?> updateArticleInfo(@PathVariable Integer articleNo, @RequestBody Article article) {
        article.setArticleNo(articleNo);
        return articleInfoService.updateArticleInfoAfterWriting(article) ?
                Result.success("更新成功！"):Result.fail("更新失败！");
    }

    /**
     * 下面一部分就全是关于文章内容的部分
     * @param articleNo
     * @return
     */
    @RequestMapping(value = "/article_content/{articleNo}", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> getArticleContent(@PathVariable int articleNo) {
        long start = System.currentTimeMillis();
        String articleContent = articleContentService.getContentByArticleNo(articleNo);
        long end = System.currentTimeMillis();
        System.out.println("获取内容加上写缓存时间：" + (end - start) + "ms");
        return StringUtils.isEmpty(articleContent) ?
                Result.fail("文章加载失败！") : Result.success(articleContent, "文章加载完成！");
    }

    @RequestMapping(value = "/article_content/{articleNo}", method = RequestMethod.PUT)
    @ResponseBody
    public Result<?> updateContentByArticleNo(@PathVariable Integer articleNo, @RequestBody String content) {
        Integer currentUserNo = getUserNoFromSession();
        if (currentUserNo == 0) {
            return Result.fail("找不到session！");
        }

        long start = System.currentTimeMillis();
        boolean result = articleContentService.updateContentByArticleNo(content, articleNo, currentUserNo);
        long end = System.currentTimeMillis();
        System.out.println("更新文字内容时间为：" + (end - start) + "ms");
        return result ? Result.success("更新成功！"):Result.fail("更新失败！");
    }

    /**
     * 下面部分信息内容皆有
     * 即添加文章同时也新建暂时没有文章内容的内容记录
     * @param articleCorpusName
     * @param articlePrivate
     * @return
     */
    @RequestMapping(value = "/users/corpus/{articleCorpusName}/article/{articlePrivate}", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> addArticle(@PathVariable String articleCorpusName, @PathVariable Boolean articlePrivate) {
        Integer userNo = getUserNoFromSession();
        if (userNo == null) {
            return Result.fail("找不到session！");
        }
        Article article = new Article()
                .setUserNo(userNo)
                .setArticleCorpusName(articleCorpusName)
                .setArticlePrivate(articlePrivate);
        int generatedArticleNo;
        try {
            generatedArticleNo = articleService.createArticle(article);
        } catch (InsertDbException e) {
            return Result.fail("新建文章发生错误！");
        }
        return Result.successWithDataOnly(generatedArticleNo);
    }

    /**
     *
     * @param articleNo
     * @return
     */
    @RequestMapping(value = "/article/{articleNo}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<?> deleteArticle(@PathVariable int articleNo) {
        Integer currentUser = getUserNoFromSession();
        if (currentUser == null) {
            return Result.fail("找不到session！");
        }

        return articleService.deleteArticle(articleNo, currentUser) ?
                Result.success("删除成功！") : Result.fail("删除失败");
    }
}

