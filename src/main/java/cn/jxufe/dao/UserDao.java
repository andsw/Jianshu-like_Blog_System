package cn.jxufe.dao;

import cn.jxufe.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.sql.SQLIntegrityConstraintViolationException;


/**
 * @ClassName: UserDao
 * @author: hsw
 * @date: 2019/3/30 20:06
 * @Description: 一些未完成需求：各种数量的存在和增加问题！
 *               比如一些数据比如文章数，是获取时统计还是留一个字段记录？
 *               比如是每次操作更新还是定时更新？
 *
 *               因为文集是一次性加载的，所以加载完文集后可以在前台计算所以user列文集数没有存在的意义
 *               TODO
 */
public interface UserDao {

    /**
     * 注册时插入用户信息,前面一直想着多条语句先检查唯一性再插入，还不如直接在一条sql语句中检查和插入
     * @param user 用户对象
     * @return 添加成功返回用户添加的条数，user中包括自增的userNo！
     */
    int insertUser(@Param("user") User user);

    /**
     * 单独用来做某个不唯一字段是否存在的检测
     * @param username
     * @return
     */
    boolean isUsernameExisted(String username);

    /**
     * email是否已存在，在注册时插入失败后的判断
     * @param email
     * @return
     */
    boolean isEmailExisted(String email);

    /**
     * tel是否已存在，在注册时插入失败后的判断
     * @param tel
     * @return
     */
    boolean isTelExisted(String tel);

    /**
     * 电话获取userNo，然后再用userNo获取user信息
     * @param tel 电话
     * @return userNO
     */
    Integer getUserNoByTel(String tel);

    /**
     * email获取userNo，然后再用userNo获取user信息
     * @param email
     * @return userNO
     */
    Integer getUserNoByEmail(String email);

    /**
     * 先获取userNo，再根据userNo获取密码
     * @param userNo
     * @return
     */
    String getPasswordByUserNo(int userNo);

    /**
     * 下面就开始不是关于注册登录的操作方法了！
     * 用户序号获取用户
     * @param userNo 用户
     * @return 用户信息
     */
    User getUserByUserNo(int userNo);

    /**
     * 获取email，目前用在修改密码时加密上
     * @param userNo
     * @return
     */
    String getEmailByUserNo(int userNo);

    /**
     * 获取电话，目前用在修改密码时加密上！
     * @param userNo
     * @return
     */
    String getTelByUserNo(int userNo);

    /**
     * 修改密码
     * @param password
     * @param userNo
     * @return 返回修改行数，当然这里只有0和1
     */
    int updatePasswordByUserNo(@Param("password") String password,
                               @Param("userNo") int userNo);

    /**
     * 获取个人简介，因为个人简介内容较多，和其他个人信息一起获取可能会
     * 很慢，使得登录时显示主页慢，所以分步获取，先显示界面再一步步获取
     * @param userNo
     * @return
     */
    String getSelfSummaryByUserNo(int userNo);

    /**
     * 修改个人简介
     * @param selfSummary
     * @param userNo
     * @return 返回的修改条数,只有0和1，因为service是只能获取当前userNo，上同！
     */
    int updateSelfSummaryByUserNo(@Param("selfSummary") String selfSummary, @Param("userNo") int userNo);

    /**
     * 因为一个设置页面可以选择部分信息修改，而没有修
     * 改的信息就没有从前台传递过来的需要了，所以虽然
     * 放一个方法中，但有些值没有修改就直接设置为空，sql中再判断！
     * @param userNo
     * @param avatar
     * @param username
     * @param email
     * @param tel
     * @return
     * @throws DataAccessException mybatis不提倡捕捉异常，如果真要，mybatis将所有sql操作异常封装成DataAccessException，普通的是无法捕捉的！
     */
    int updateAccountInfoByUserNo(@Param("userNo") int userNo,
                             @Param("avatar") String avatar,
                             @Param("username") String username,
                             @Param("email") String email,
                             @Param("tel") String tel) throws DataAccessException;

    /**
     * 修改个人信息
     *
     * @param userNo
     * @param gender          同样 0表示女，1表示男，2表示保密，这里用来Byte是因为要用null来表示不用修改性别！
     * @param github
     * @param wechatQrImgLink
     * @return
     */
    int updatePersonalInfoByUserNo(@Param("userNo") int userNo,
                                   @Param("gender") Byte gender,
                                   @Param("github") String github,
                                   @Param("wechatQrImgLink") String wechatQrImgLink);
}
