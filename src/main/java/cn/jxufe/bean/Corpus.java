package cn.jxufe.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName: Corpus
 * @author: hsw
 * @date: 2019/4/29 10:32
 * @Description: 文集bean，属性分别是序号，文集名，所属用户序号，包含文章数！
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Corpus implements Serializable {
    private static final long serialVersionUID = 5179560096777477623L;
    private Integer corpusNo;
    private String corpusName;
    private Integer userNO;
    private Integer blogNum;
}
