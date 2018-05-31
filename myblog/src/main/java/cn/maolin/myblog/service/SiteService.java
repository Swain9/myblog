package cn.maolin.myblog.service;

import cn.maolin.myblog.entity.Comments;
import cn.maolin.myblog.entity.Contents;
import cn.maolin.myblog.entity.Logs;
import cn.maolin.myblog.entity.Metas;
import cn.maolin.myblog.model.dto.Statistics;
import cn.maolin.myblog.model.vo.Archive;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/16 11:50
 */
public interface SiteService {
    /**
     * 最新收到的评论
     *
     * @param count 评论数
     * @return List<Comments>
     */
    List<Comments> recentComments(int count);

    /**
     * 根据获取方式和数量获取文章列表
     *
     * @param type  获取方式(最新,随机)
     * @param count 数量
     * @return List<Contents>
     */
    List<Contents> getContents(String type, int count);

    /**
     * 系统统计数据
     *
     * @return Statistics
     */
    Statistics getStatistics();

    /**
     * 获取count数量的日志信息
     *
     * @param count 日志数量
     * @return List<Logs>
     */
    List<Logs> getLogs(int count);

    /**
     * 清理站点缓存
     *
     * @param cStatistics
     */
    void cleanCache(String cStatistics);

    /**
     * 获取相邻的文章
     *
     * @param type    上一篇:prev | 下一篇:next
     * @param created 当前文章创建时间
     * @return Contents
     */
    Contents getNhContent(String type, Integer created);

    /**
     * 获取评论
     *
     * @param cid   文章主键
     * @param page  当前页
     * @param limit 条数
     * @return List<Comments>
     */
    List<Comments> getComments(Integer cid, int page, int limit);

    /**
     * 查询一条评论
     *
     * @param coid
     * @return
     */
    Comments getComment(Integer coid);

    /**
     * 获取分类/标签列表
     *
     * @param searchType 获取(最新/随机)的目录
     * @param type       类型
     * @param limit      条数
     * @return List<Metas>
     */
    List<Metas> getMetas(String searchType, String type, int limit);

    /**
     * 文章归档
     *
     * @return List<Archive>
     */
    List<Archive> getArchives();

}
