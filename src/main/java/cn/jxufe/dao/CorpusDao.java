package cn.jxufe.dao;

import cn.jxufe.bean.Corpus;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

/**
 * @ClassName: CorpusDao
 * @author: hsw
 * @date: 2019/5/14 10:51
 * @Description: 文集数据操作类
 */
public interface CorpusDao {

    /**
     * 查询某一用户的所有文集
     * @param userNo
     * @return
     */
    List<Corpus> getAllCorpusByUserNo(int userNo);

    /**
     * 新建文集！
     * @param corpus
     * @return
     * @throws SQLIntegrityConstraintViolationException
     */
    int insertCorpus(@Param("corpus") Corpus corpus);

    /**
     * 根据corpusNo删除文集,别忘了
     * @param userNo
     * @param corpusName
     * @return
     */
    int deleteCorpus(@Param("userNo") int userNo, @Param("corpusName") String corpusName);


    /**
     * 重命名文集
     * @param userNo
     * @param corpusName
     * @param newCorpusName
     * @return
     * @throws DataAccessException 有重复的就报错,mybatis的异常封装
     */
    int updateCorpusNameByCorpusNo(@Param("userNo") int userNo, @Param("corpusName") String corpusName,
                                   @Param("newCorpusName") String newCorpusName) throws DataAccessException;


    /**
     * 本来打算使用存储过程，那样更节省流量，运行也快，但是网上很多人说这样很难维护，代码不清晰
     * 推荐使用代码完成逻辑
     * 项目流量不会太大，这样也可以，养成习惯最重要！
     * 这个方法是用来在文集中新建文章、移动文章到此文集操作增加文集文章数的一个操作，每次加1
     * @param userNo
     * @param corpusName
     * @return
     */
    int incrBlogNumByCorpusNo(@Param("userNo") int userNo, @Param("corpusName") String corpusName);

    /**
     * 相应的，删除文章和移走文章也会带来文集文章数的减1操作
     * @param userNo
     * @param corpusName
     * @return
     */
    int descBlogNumByCorpusNo(@Param("userNo") int userNo, @Param("corpusName") String corpusName);
}
