package cn.jxufe.service.impl;

import cn.jxufe.bean.Corpus;
import cn.jxufe.dao.CorpusDao;
import cn.jxufe.exception.UpdateDbException;
import cn.jxufe.service.CorpusInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Cacheable(value = "redisCache", key = "'corpus-' + #userNo")
    public List<Corpus> getAllCorpusByUserNo(int userNo) {
        return corpusDao.getAllCorpusByUserNo(userNo);
    }

    /**
     * 判重还是在前端哦
     * @param corpus
     * @return
     */
    @Override
    @CacheEvict(value = "redisCache", key = "'corpus-' + #corpus.userNo")
    public boolean insertCorpus(Corpus corpus) {
        return corpusDao.insertCorpus(corpus) == 1;
    }

    @Override
    @CacheEvict(value = "redisCache", key = "'corpus-' + #currentUserNo")
    public boolean deleteCorpusByUserNoAndCorpusName(int currentUserNo, String corpusName) {
        return corpusDao.deleteCorpus(currentUserNo, corpusName) == 1;
    }

    /**
     * 注意想想，其实根本不用判断重名问题！
     * @param currentUserNo
     * @param corpusName
     * @param newName
     * @return
     */
    @Override
    @CacheEvict(value = "redisCache", key = "'corpus-' + #currentUserNo")
    public boolean renameCorpus(int currentUserNo, String corpusName, String newName) throws DataAccessException {
        return corpusDao.updateCorpusNameByCorpusNo(currentUserNo, corpusName, newName) == 1;
    }

    /**
     * required 如果调用此方法的方法是事务，则加入该事务，否则新建一个事务！
     * @param currentUserNo
     * @param fromCorpusName
     * @param toCorpusName
     * @return
     * @throws UpdateDbException 自定义更新异常
     */
    @Transactional(rollbackFor = UpdateDbException.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean updateBlogNumWhenMoveBlog(int currentUserNo, String fromCorpusName, String toCorpusName) throws UpdateDbException {
        if (updateBlogNumWhenDeleteBlog(currentUserNo, fromCorpusName)) {
            throw new UpdateDbException();
        }
        if (updateBlogNumWhenCreateBlog(currentUserNo, toCorpusName)) {
            throw new UpdateDbException();
        }
        return true;
    }

    @Override
    public boolean updateBlogNumWhenDeleteBlog(int currentUserNo, String fromCorpusName) {
        return corpusDao.descBlogNumByCorpusNo(currentUserNo, fromCorpusName) == 1;
    }

    @Override
    public boolean updateBlogNumWhenCreateBlog(int currentUserNo, String toCorpusName) {
        return corpusDao.incrBlogNumByCorpusNo(currentUserNo, toCorpusName) == 1;
    }

}
