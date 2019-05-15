package cn.jxufe.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: FollowDao
 * @author: hsw
 * @date: 2019/5/15 8:59
 * @Description: TODO
 */
public interface FollowDao {
    /**
     * 查找某用户所有粉丝的userNo
     * @param userNo userNo
     * @param followerNum 粉丝数，用来限制查询条数即limit num
     * @return
     */
    List<Integer> getAllFollowersNoByUserNo(@Param("userNo") int userNo, @Param("followerNum") int followerNum);

    /**
     * 获取某用户所有关注用户userNo
     * @param userNo
     * @param followNum
     * @return
     */
    List<Integer> getAllFollowsNoByUserNO(@Param("userNo") int userNo, @Param("followNum") int followNum);
}
