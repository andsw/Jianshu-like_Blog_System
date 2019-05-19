package cn.jxufe.service;

import cn.jxufe.bean.Corpus;
import cn.jxufe.exception.UpdateDbException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @ClassName: CorpusInfoService
 * @author: hsw
 * @date: 2019/5/14 17:12
 * @Description: TODO
 */
public interface CorpusInfoService {
    /**
     * 1
     * @param userNo
     * @return
     */
    List<Corpus> getAllCorpusByUserNo(int userNo);

    /**
     * 添加文集
     * @param corpus
     * @return
     */
    boolean insertCorpus(Corpus corpus);

    /**
     * 1
     * @param userNo
     * @param corpusName
     * @return
     */
    boolean deleteCorpusByUserNoAndCorpusName(int userNo,String corpusName);

    /**
     * 1
     * @param userNo
     * @param corpusName
     * @param newName
     * @return
     */
    boolean renameCorpus(int userNo, String corpusName, String newName);

    /**
     * 1
     * @param userNo
     * @param fromCorpusName
     * @param toCorpusName
     * @return
     * @throws UpdateDbException
     */
    boolean updateBlogNumWhenMoveBlog(int userNo, String fromCorpusName, String toCorpusName) throws UpdateDbException;

    /**
     * 1
     * @param userNo
     * @param fromCorpusName
     * @return
     */
    boolean updateBlogNumWhenDeleteBlog(int userNo, String fromCorpusName);

    /**
     * 1
     * @param userNo
     * @param toCorpusName
     * @return
     */
    boolean updateBlogNumWhenCreateBlog(int userNo, String toCorpusName);
}
