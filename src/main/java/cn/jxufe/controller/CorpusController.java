package cn.jxufe.controller;

import cn.jxufe.bean.Corpus;
import cn.jxufe.dto.Result;
import cn.jxufe.service.CorpusInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName: CorpusController
 * @author: hsw
 * @date: 2019/5/14 22:00
 * @Description: TODO
 */
@Controller
public class CorpusController {

    private final HttpSession session;

    private final CorpusInfoService corpusInfoService;

    @Autowired
    public CorpusController(CorpusInfoService corpusInfoService, HttpSession session) {
        this.corpusInfoService = corpusInfoService;
        this.session = session;
    }

    private Integer getUserNoFromSession() {
        return (Integer) session.getAttribute("userNo");
    }

    /**
     * 获取某个用户所有文集嘛！前台判空
     * @param userNo
     * @return
     */
    @RequestMapping(value = "/users/{userNo}/corpuses", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Corpus>> getAllCorpusInfoByUserNo(@PathVariable int userNo) {
        List<Corpus> corpuses = corpusInfoService.getAllCorpusByUserNo(userNo);
        return Result.successWithDataOnly(corpuses);
    }

    @RequestMapping(value = "/users/corpuses/{corpusName}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<?> deleteCorpusByCorpusNo(@PathVariable String corpusName) {

        Integer userNo = getUserNoFromSession();

        if (userNo == null) {
            return Result.fail("找不到session！");
        }

        return corpusInfoService.deleteCorpusByUserNoAndCorpusName(userNo, corpusName) ?
                Result.success("删除成功！") : Result.fail("删除失败！");
    }

    /**
     * 重命名
     * @param corpusName
     * @param newName
     * @return
     */
    @RequestMapping(value = "/users/corpuses/{corpusName}", method = RequestMethod.PUT)
    public Result<?> renameCorpus(@PathVariable String corpusName, @RequestBody String newName) {
        Integer userNo = getUserNoFromSession();

        if (userNo == null) {
            return Result.fail("找不到session！");
        }

        return corpusInfoService.renameCorpus(userNo, corpusName, newName) ?
                Result.success("修改成功！") : Result.fail("修改失败！");
    }

}
