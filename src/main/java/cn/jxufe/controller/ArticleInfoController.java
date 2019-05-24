package cn.jxufe.controller;

import cn.jxufe.bean.Article;
import cn.jxufe.dto.Result;
import cn.jxufe.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName: ArticleInfoController
 * @author: hsw
 * @date: 2019/5/23 14:41
 * @Description: TODO
 */
@Controller
public class ArticleInfoController {

    private static final boolean ARTICLE_IS_PRIVATE = true;
    private static final boolean ARTICLE_IS_PUBLIC = false;

    private final HttpSession session;
    private final ArticleInfoService articleInfoService;

    @Autowired
    public ArticleInfoController(HttpSession session, ArticleInfoService articleInfoService) {
        this.session = session;
        this.articleInfoService = articleInfoService;
    }

    private Integer getUserNoFromSession() {
        return (Integer) session.getAttribute("userNo");
    }

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

    @RequestMapping(value = "/users/corpus/{articleCorpusName}/article_info/{articlePrivate}", method = RequestMethod.POST)
    @ResponseBody
    public Result<?> addArticleInfo(@PathVariable String articleCorpusName, @PathVariable Boolean articlePrivate) {
        Integer userNo = getUserNoFromSession();
        if (userNo == null) {
            return Result.fail("找不到session！");
        }
        Article article = new Article()
                                .setUserNo(userNo)
                                .setArticleCorpusName(articleCorpusName)
                                .setArticlePrivate(articlePrivate);
        int generatedArticleNo = articleInfoService.insertArticleInfo(article);
        return generatedArticleNo == -1 ?
                Result.fail("新建文章发生错误！") : Result.successWithDataOnly(generatedArticleNo);
    }

    @RequestMapping(value = "/users/article_info/{articleNo}", method = RequestMethod.PUT)
    @ResponseBody
    public Result<?> updateArticleInfo(@PathVariable Integer articleNo, @RequestBody Article article) {
        article.setArticleNo(articleNo);
        return articleInfoService.updateArticleInfoAfterWriting(article) ?
                Result.success("更新成功！"):Result.fail("更新失败！");
    }
}
