package cn.jxufe.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName: Article
 * @author: hsw
 * @date: 2019/4/2 15:30
 * @Description: 文章内容对象
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArticleContent implements Serializable {
    private static final long serialVersionUID = 6981398832660039328L;
    private Integer articleNo;
    private String content;
}
