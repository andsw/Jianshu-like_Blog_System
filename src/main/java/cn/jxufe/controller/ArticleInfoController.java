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
        List<Article> publicArticleList = articleInfoService.getPublicArticlesInfoByUserNo(userNo, publicArticleNum);
        return publicArticleList == null ? Result.fail("获取文章信息列表失败！") : Result.successWithDataOnly(publicArticleList);
    }

    @RequestMapping(value = "/users/article_info/priv", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Article>> getPrivateArticleByUserNo(@RequestParam Integer privateArticleNum) {
        Integer currentUserNo = getUserNoFromSession();
        if (currentUserNo == null) {
            return Result.fail("找不到session！");
        }
        List<Article> privateArticleList = articleInfoService.getPrivateArticlesInfoByUserNo(currentUserNo, privateArticleNum);
        return privateArticleList == null ? Result.fail("获取私有文章信息列表失败！") : Result.successWithDataOnly(privateArticleList);
    }
}
