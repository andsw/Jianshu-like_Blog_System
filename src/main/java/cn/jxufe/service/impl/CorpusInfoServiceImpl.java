package cn.jxufe.service.impl;

import cn.jxufe.bean.Corpus;
import cn.jxufe.dao.CorpusDao;
import cn.jxufe.exception.UpdateDbException;
import cn.jxufe.service.CorpusInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName: CorpusInfoServiceImpl
 * @author: hsw
 * @date: 2019/5/14 17:14
 * @Description: TODO
 */
public class CorpusInfoServiceImpl implements CorpusInfoService {

    @Autowired
    private CorpusDao corpusDao;

    @Override
    public List<Corpus> getAllCorpusByUserNo(int userNo) {
        return corpusDao.getAllCorpusByUserNo(userNo);
    }

    @Override
    public boolean deleteCorpusByUserNoAndCorpusName(int userNo,String corpusName) {
        return corpusDao.deleteCorpus(userNo, corpusName) == 1;
    }

    @Override
    public boolean renameCorpus(int userNo, String corpusName, String newName) {
        return corpusDao.updateCorpusNameByCorpusNo(userNo, corpusName, newName) == 1;
    }

    /**
     * required 如果调用此方法的方法是事务，则加入该事务，否则新建一个事务！
     * @param userNo
     * @param fromCorpusName
     * @param toCorpusName
     * @return
     * @throws UpdateDbException 自定义更新异常
     */
    @Transactional(rollbackFor = UpdateDbException.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean updateBlogNumWhenMoveBlog(int userNo, String fromCorpusName, String toCorpusName) throws UpdateDbException {
        if (updateBlogNumWhenDeleteBlog(userNo, fromCorpusName)) {
            throw new UpdateDbException();
        }
        if (updateBlogNumWhenCreateBlog(userNo, toCorpusName)) {
            throw new UpdateDbException();
        }
        return true;
    }

    @Override
    public boolean updateBlogNumWhenDeleteBlog(int userNo, String fromCorpusName) {
        return corpusDao.descBlogNumByCorpusNo(userNo, fromCorpusName) == 1;
    }

    @Override
    public boolean updateBlogNumWhenCreateBlog(int userNo, String toCorpusName) {
        return corpusDao.incrBlogNumByCorpusNo(userNo, toCorpusName) == 1;
    }

}
