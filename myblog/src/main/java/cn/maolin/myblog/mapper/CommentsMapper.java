package cn.maolin.myblog.mapper;

import cn.maolin.myblog.entity.Comments;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/17 10:19
 */
@Repository
public interface CommentsMapper {
    /**
     * 查找所有的评论
     *
     * @return List<Comments>
     */
    List<Comments> selectComments();

    /**
     * 评论数统计
     *
     * @return long:数量
     */
    long countComments();

    /**
     * 根据文章主键删除评论
     *
     * @param cid 文章主键
     * @return
     */
    int deleteByCid(int cid);

    /**
     * 查询一条评论
     * @param coid
     * @return
     */
    Comments selectCommentsByCoid(Integer coid);
}
