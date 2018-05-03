package cn.maolin.myblog.extension;

import cn.maolin.myblog.entity.Comments;
import cn.maolin.myblog.entity.Contents;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.SiteService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/26 10:45
 */
@Component(value = "site")
public class SiteUtil {

    private final SiteService siteService;

    public SiteUtil(SiteService siteService) {
        this.siteService = siteService;
    }


    /**
     * 返回文章链接地址
     *
     * @param cid
     * @param slug
     * @return
     */
    public String permalink(Integer cid, String slug) {
        return "/article/" + (StringUtils.isEmpty(slug) ? cid.toString() : slug);
    }

    /**
     * 返回文章链接地址
     *
     * @param contents
     * @return
     */
    public String permalink(Contents contents) {
        return permalink(contents.getCid(), contents.getSlug());
    }
    /**
     * 当前文章的上一篇文章链接
     *
     * @return
     */
    public String thePrev(Contents contents) {
        if (contents == null) {
            return "";
        }

        Contents prev = siteService.getNhContent(Types.PREV, contents.getCreated());

        if (prev == null) {
            return "";
        }

        return "<a href=\"" + permalink(prev) + "\" title=\"" + prev.getTitle() + "\">←</a>";
    }

    /**
     * 当前文章的下一篇文章链接
     *
     * @return
     */
    public String theNext(Contents contents) {
        if (contents == null) {
            return "";
        }
        Contents next = siteService.getNhContent(Types.NEXT, contents.getCreated());

        if (next == null) {
            return "";
        }

        return "<a href=\"" + permalink(next) + "\" title=\"" + next.getTitle() + "\">→</a>";
    }

    /**
     * 最新文章
     *
     * @param limit
     * @return
     */
    public List<Contents> recentArticles(int limit) {
        if (null == siteService) {
            return null;
        }
        return siteService.getContents(Types.RECENT_ARTICLE, limit);
    }

    /**
     * 最新评论
     *
     * @param limit
     * @return
     */
    public List<Comments> recentComments(int limit) {
        if (null == siteService) {
            return null;
        }
        return siteService.recentComments(limit);
    }

    /**
     * 获取当前文章/页面的评论
     *
     * @param limit
     * @return
     */
    public PageInfo<Comments> comments(Contents contents, int limit, Integer cp) {
        if (null == contents) {
            return null;
        }

        int page = 1;
        if (null != cp) {
            page = cp.intValue();
        }
        List<Comments> list =  siteService.getComments(contents.getCid(), page, limit);

        return new PageInfo<>(list);
    }

    /**
     * 获取评论at信息
     *
     * @param coid
     * @return
     */
    public String commentAt(Integer coid) {
        Comments comments = siteService.getComment(coid);
        if (null != comments) {
            return "<a href=\"#comment-" + coid + "\">@" + comments.getAuthor() + "</a>";
        }
        return "";
    }

}
