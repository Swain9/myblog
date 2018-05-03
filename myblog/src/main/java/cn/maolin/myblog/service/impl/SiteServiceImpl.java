package cn.maolin.myblog.service.impl;

import cn.maolin.myblog.entity.Comments;
import cn.maolin.myblog.entity.Contents;
import cn.maolin.myblog.entity.Logs;
import cn.maolin.myblog.entity.Metas;
import cn.maolin.myblog.mapper.*;
import cn.maolin.myblog.model.dto.Statistics;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.SiteService;
import cn.maolin.myblog.util.BlogConstant;
import cn.maolin.myblog.util.MapCache;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/16 11:50
 */
@Service
public class SiteServiceImpl implements SiteService {

    private final CommentsMapper commentsMapper;
    private final ContentsMapper contentsMapper;
    private final AttachMapper attachMapper;
    private final MetasMapper metasMapper;
    private final LogsMapper logsMapper;

    private MapCache mapCache = new MapCache();


    public SiteServiceImpl(CommentsMapper commentsMapper, ContentsMapper contentsMapper, AttachMapper attachMapper, MetasMapper metasMapper, LogsMapper logsMapper) {
        this.commentsMapper = commentsMapper;
        this.contentsMapper = contentsMapper;
        this.attachMapper = attachMapper;
        this.metasMapper = metasMapper;
        this.logsMapper = logsMapper;
    }

    /**
     * 最新收到的评论
     *
     * @param count 评论数
     * @return List<Comments>
     */
    @Override
    public List<Comments> recentComments(int count) {
        if (count < 0 || count > BlogConstant.COMMENTS_COUNT_MAX) {
            count = BlogConstant.COMMENTS_COUNT_MAX;
        }
        PageHelper.startPage(0, count);
        List<Comments> list = commentsMapper.selectComments();
        return list;
    }

    /**
     * 根据获取方式和数量获取文章列表
     *
     * @param type  获取方式(最新,随机)
     * @param count 数量
     * @return List<Contents>
     */
    @Override
    public List<Contents> getContents(String type, int count) {
        if (count < 0 || count > BlogConstant.CONTENT_COUNT_MAX) {
            count = BlogConstant.CONTENT_COUNT_MAX;
        }
        // 最新文章
        if (Types.RECENT_ARTICLE.equals(type)) {
            PageHelper.startPage(0, count);
            //
            List<Contents> list = contentsMapper.selectContentsByStatusAndType(Types.PUBLISH, Types.ARTICLE);

            return list;
        }

        // 随机文章
        if (Types.RANDOM_ARTICLE.equals(type)) {
            PageHelper.startPage(0, count);
            List<Contents> list = contentsMapper.selectRandomContentsByStatusAndType(Types.PUBLISH, Types.ARTICLE);

            return list;
        }
        return null;

    }

    /**
     * 系统统计数据
     *
     * @return Statistics
     */
    @Override
    public Statistics getStatistics() {
        //读取站点缓存
        Statistics statistics = mapCache.get(Types.C_STATISTICS);
        if (null != statistics) {
            return statistics;
        }

        statistics = new Statistics();

        long articles = contentsMapper.countContentsByStatusAndType(Types.PUBLISH, Types.ARTICLE);
        long pages = contentsMapper.countContentsByStatusAndType(Types.PUBLISH, Types.PAGE);
        long comments = commentsMapper.countComments();

        long attachs = attachMapper.countAttachs();
        long tags = metasMapper.countMetas(Types.TAG);
        long categories = metasMapper.countMetas(Types.CATEGORY);

        statistics.setArticles(articles);
        statistics.setPages(pages);
        statistics.setComments(comments);
        statistics.setAttachs(attachs);
        statistics.setTags(tags);
        statistics.setCategories(categories);

        mapCache.set(Types.C_STATISTICS, statistics);
        return statistics;
    }

    /**
     * 获取count数量的日志信息
     *
     * @param count 日志数量
     * @return List<Logs>
     */
    @Override
    public List<Logs> getLogs(int count) {
        if (count < 0 || count > BlogConstant.LOGS_COUNT_MAX) {
            count = BlogConstant.LOGS_COUNT_MAX;
        }
        PageHelper.startPage(0, count);
        return logsMapper.selectLogs();
    }

    /**
     * 清除缓存
     *
     * @param key
     */
    @Override
    public void cleanCache(String key) {
        if (!StringUtils.isEmpty(key)) {
            if ("*".equals(key)) {
                mapCache.clean();
            } else {
                mapCache.del(key);
            }
        }
    }

    /**
     * 获取相邻的文章
     *
     * @param type    上一篇:prev | 下一篇:next
     * @param created 当前文章创建时间
     * @return Contents
     */
    @Override
    public Contents getNhContent(String type, Integer created) {
        Contents contents = new Contents();
        contents.setType(Types.ARTICLE);
        contents.setStatus(Types.PUBLISH);
        contents.setCreated(created);

        if (Types.NEXT.equals(type)) {
            return contentsMapper.selectNextContent(contents);
        }
        if (Types.PREV.equals(type)) {
            return contentsMapper.selectPrevContent(contents);
        }
        return null;
    }

    /**
     * 获取评论
     *
     * @param cid   文章主键
     * @param page  当前页
     * @param limit 条数
     * @return List<Comments>
     */
    @Override
    public List<Comments> getComments(Integer cid, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Comments> list = commentsMapper.selectComments();
        return list;
    }

    /**
     * 查询一条评论
     *
     * @param coid
     * @return
     */
    @Override
    public Comments getComment(Integer coid) {
        return commentsMapper.selectCommentsByCoid(coid);
    }

    /**
     * 获取分类/标签列表
     *
     * @param searchType 获取(最新/随机)的目录
     * @param type       类型
     * @param limit      条数
     * @return List<Metas>
     */
    @Override
    public List<Metas> getMetas(String searchType, String type, int limit) {
        if (StringUtils.isEmpty(searchType) || StringUtils.isEmpty(type)) {
            return null;
        }
        if (limit < 1 || limit > BlogConstant.MAX_POSTS) {
            limit = 10;
        }
        // 获取最新的项目
        if (Types.RECENT_META.equals(searchType)) {
            return metasMapper.selectRecentMetasWithCount(type, limit);
        }

        // 随机获取项目
        if (Types.RANDOM_META.equals(searchType)) {
            return metasMapper.selectRandomMetasWithCount(type, limit);
        }

        return null;
    }
}
