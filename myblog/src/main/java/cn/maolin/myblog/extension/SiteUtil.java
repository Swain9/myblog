package cn.maolin.myblog.extension;

import cn.maolin.myblog.entity.Comments;
import cn.maolin.myblog.entity.Contents;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.SiteService;
import cn.maolin.myblog.util.BlogUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
     * 获取文章摘要
     *
     * @param contents 文章
     * @param len      长度
     * @return
     */
    public String intro(Contents contents, int len) {
        if (null != contents) {
            return intro(contents.getContent(), len);
        }
        return "";
    }

    /**
     * 截取文章摘要
     *
     * @param value 文章内容
     * @param len   要截取文字的个数
     * @return
     */
    public String intro(String value, int len) {
        int pos = value.indexOf("<!--more-->");
        if (pos != -1) {
            String html = value.substring(0, pos);
            return BlogUtil.htmlToText(BlogUtil.mdToHtml(html));
        } else {
            String text = BlogUtil.htmlToText(BlogUtil.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len);
            }
            return text;
        }
    }

    /**
     * 文章标题
     *
     * @param contents
     * @return
     */
    public String title(Contents contents) {
        //site_title
        return null != contents ? contents.getTitle() : "site_title";
    }

    /**
     * 显示文章图标
     *
     * @return
     */
    public String show_icon(Contents contents) {
        if (null != contents) {
            return show_icon(contents.getCid());
        }
        return show_icon(1);
    }

    /**
     * 显示文章图标
     *
     * @param cid
     * @return
     */
    public String show_icon(int cid) {
        return ICONS[cid % ICONS.length];
    }

    private final String[] ICONS = {"bg-ico-book", "bg-ico-game", "bg-ico-note", "bg-ico-chat", "bg-ico-code", "bg-ico-image", "bg-ico-web", "bg-ico-link", "bg-ico-design", "bg-ico-lock"};

    /**
     * 显示文章缩略图，顺序为：文章第一张图 -> 随机获取
     *
     * @return
     */
    public String show_thumb(Contents contents) {
        if (null == contents) {
            return "";
        }
        if (!StringUtils.isEmpty(contents.getThumbImg())) {
            return contents.getThumbImg();
        }
        String content = article(contents.getContent());
        String img = BlogUtil.show_thumb(content);
        if (!StringUtils.isEmpty(img)) {
            return img;
        }
        int cid = contents.getCid();
        int size = cid % 20;
        size = size == 0 ? 1 : size;
        return "/themes/default/img/rand/" + size + ".jpg";
    }

    /**
     * 显示文章内容，转换markdown为html
     *
     * @param value
     * @return
     */
    public String article(String value) {
        if (!StringUtils.isEmpty(value)) {
            value = value.replace("<!--more-->", "\r\n");
            return BlogUtil.mdToHtml(value);
        }
        return "";
    }

    /**
     * 显示分类
     *
     * @return
     */
    public String show_categories(Contents contents) throws UnsupportedEncodingException {
        if (null != contents) {
            return show_categories(contents.getCategories());
        }
        return "";
    }

    /**
     * 显示分类
     *
     * @param categories
     * @return
     */
    public String show_categories(String categories) throws UnsupportedEncodingException {
        if (!StringUtils.isEmpty(categories)) {
            String[] arr = categories.split(",");
            StringBuffer sbuf = new StringBuffer();
            for (String c : arr) {
                sbuf.append("<a href=\"/category/" + URLEncoder.encode(c, "UTF-8") + "\">" + c + "</a>");
            }
            return sbuf.toString();
        }
        return show_categories("默认分类");
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
        List<Comments> list = siteService.getComments(contents.getCid(), page, limit);

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
