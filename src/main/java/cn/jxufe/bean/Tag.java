package cn.jxufe.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName: Tag
 * @author: hsw
 * @date: 2019/4/29 10:35
 * @Description: TODO
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Tag implements Serializable {
    private static final long serialVersionUID = 8777206905503249700L;
    private String tagName;
    private String articleNo;
}
