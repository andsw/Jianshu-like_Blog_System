package cn.jxufe.dao;

import cn.jxufe.bean.User;
import org.apache.ibatis.annotations.Param;


/**
 * @ClassName: UserDao
 * @author: hsw
 * @date: 2019/3/30 20:06
 * @Description: 一些需求：通过
 */
public interface UserDao {
    /**
     * 先获取密码，成功后再用邮箱或电话获取用户全部信息，防止每次都传递整个用户信息，造成浪费缓存等影响。
     * @param email 用户注册邮箱
     * @return 通过邮箱密码
     */
    String getPasswordByEmail(String email);

    /**
     * 电话获取密码
     * @param tel 注册号码
     * @return .
     */
    String getPasswordByTel(String tel);

    /**
     * 注册时插入用户信息,前面一直想着多条语句先检查唯一性再插入，还不如直接在一条sql语句中检查和插入
     * @param user 用户对象
     * @return 添加成功返回用户user_no
     */
    int insertUser(User user);

    /**
     * 单独用来做某个不唯一字段是否存在的检测
     * @param username
     * @return
     */
    boolean doseUsernameExisted(String username);

    /**
     * email是否已存在，在注册时插入失败后的判断
     * @param email
     * @return
     */
    boolean doseEmailExisted(String email);

    /**
     * tel是否已存在，在注册时插入失败后的判断
     * @param tel
     * @return
     */
    boolean doseTelExisted(String tel);

    /**
     * 电话获取userNo，然后再用userNo获取user信息
     * @param tel 电话
     * @return userNO
     */
    int getUserNoByTel(String tel);

    /**
     * email获取userNo，然后再用userNo获取user信息
     * @param email
     * @return userNO
     */
    int getUserNoByEmail(String email);

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
     * @param passwordEmail
     * @param passwordTel
     * @param userNo
     * @return 返回修改行数，当然这里只有0和1
     */
    int updatePasswordByUserNo(@Param("passwordEmail") String passwordEmail,
                               @Param("passwordTel") String passwordTel,
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
}
