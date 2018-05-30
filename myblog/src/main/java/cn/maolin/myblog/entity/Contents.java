package cn.maolin.myblog.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

import static cn.maolin.myblog.util.BlogConstant.MAX_TEXT_COUNT;
import static cn.maolin.myblog.util.BlogConstant.MAX_TITLE_COUNT;

@Data
public class Contents implements Serializable {
    /**
     * 主键
     * t_contents.cid
     */
    private Integer cid;

    /**
     * 标题
     * t_contents.title
     */
    @NotEmpty(message = "标题不能为空")
    @Length(max = MAX_TITLE_COUNT, message = "文章标题最多可以输入%d个字符")
    private String title;

    /**
     * 伪页面路径
     * t_contents.slug
     */
    private String slug;

    /**
     * 文章缩略图
     * t_contents.thumb_img
     */
    private String thumbImg;

    /**
     * 创建日期
     * t_contents.created
     */
    private Integer created;

    /**
     * 修改日期
     * t_contents.modified
     */
    private Integer modified;

    /**
     * 作者id
     * t_contents.author_id
     */
    private Integer authorId;

    /**
     * 类型
     * t_contents.type
     */
    private String type;

    /**
     * 状态
     * t_contents.status
     */
    private String status;

    /**
     * 解析类型
     * t_contents.fmt_type
     */
    private String fmtType;

    /**
     * 标签
     * t_contents.tags
     */
    private String tags;

    /**
     * 分类列表
     * t_contents.categories
     */
    private String categories;

    /**
     * 点击率
     * t_contents.hits
     */
    private Integer hits;

    /**
     * 评论数
     * t_contents.comments_num
     */
    private Integer commentsNum;

    /**
     * 是否允许评论
     * t_contents.allow_comment
     */
    private Boolean allowComment;

    /**
     * 是否可见
     * t_contents.allow_see
     */
    private Boolean allowSee;

    /**
     * 是否允许ping
     * t_contents.allow_ping
     */
    private Boolean allowPing;

    /**
     * 是否允许出现在聚合中
     * t_contents.allow_feed
     */
    private Boolean allowFeed;

    /**
     * 内容
     * t_contents.content
     */
    @NotEmpty(message = "内容不能为空")
    @Length(max = MAX_TEXT_COUNT, message = "文章内容最多可以输入%d个字符")
    private String content;
    private static final long serialVersionUID = 1L;

}