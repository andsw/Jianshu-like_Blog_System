package cn.jxufe.service.impl;

import cn.jxufe.bean.Corpus;
import cn.jxufe.dao.CorpusDao;
import cn.jxufe.exception.UpdateDbException;
import cn.jxufe.service.CorpusInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
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
    @CacheEvict(value = "redisCache", key = "'corpus-' + #userNo")
    public boolean deleteCorpusByUserNoAndCorpusName(int userNo,String corpusName) {
        return corpusDao.deleteCorpus(userNo, corpusName) == 1;
    }

    /**
     * 注意想想，其实根本不用判断重名问题！
     * @param userNo
     * @param corpusName
     * @param newName
     * @return
     */
    @Override
    @CacheEvict(value = "redisCache", key = "'corpus-' + #userNo")
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
