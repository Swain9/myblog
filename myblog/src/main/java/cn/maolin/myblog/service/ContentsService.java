package cn.maolin.myblog.service;

import cn.maolin.myblog.entity.Contents;

import java.util.List;
import java.util.Optional;

/**
 * @author 张茂林
 * @since 2018/4/19 11:38
 */
public interface ContentsService {
    /**
     * 发布文章
     *
     * @param contents 文章
     */
    void publish(Contents contents);

    /**
     * 更新文章
     *
     * @param contents 文章
     */
    void updateArticle(Contents contents);

    /**
     * 删除文章
     *
     * @param cid 主键id
     */
    void delete(int cid);

    /**
     * 分页查询列表
     *
     * @param page  当前页
     * @param limit 当前页条数
     * @return List<Contents>
     */
    List<Contents> selectContentsByPage(int page, int limit);

    /**
     * 根据主键id或者slug查询文章
     *
     * @param cid 主键或者slug
     * @return Optional<Contents>
     */
    Optional<Contents> getContents(String cid);

    /**
     * 更新点击率
     * @param temp
     */
    void updateArticleHits(Contents temp);
}
