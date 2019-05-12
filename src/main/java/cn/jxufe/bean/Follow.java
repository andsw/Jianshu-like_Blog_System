package cn.jxufe.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName: Follow
 * @author: hsw
 * @date: 2019/4/29 11:15
 * @Description: TODO
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Follow implements Serializable {
    private static final long serialVersionUID = -4675113176798390400L;
    private Integer followedUserNo;
    private Integer followerUserNo;
}
