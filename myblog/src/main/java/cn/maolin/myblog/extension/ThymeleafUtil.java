package cn.maolin.myblog.extension;

import cn.maolin.myblog.entity.Comments;
import cn.maolin.myblog.entity.Contents;
import cn.maolin.myblog.entity.Metas;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.SiteService;
import cn.maolin.myblog.util.BlogUtil;
import cn.maolin.myblog.util.DateUtil;
import cn.maolin.myblog.util.HashUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/18 14:36
 */
public class ThymeleafUtil extends AbstractDialect implements IExpressionObjectDialect {

    public static final String NAME = "ThymeleafUtil";

    public ThymeleafUtil() {
        this(NAME);
    }

    protected ThymeleafUtil(String name) {
        super(name);
    }

    private final IExpressionObjectFactory EXPRESSION_OBJECTS_FACTORY = new ThymeleafUtilExpressionFactory();

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return this.EXPRESSION_OBJECTS_FACTORY;
    }

    /**
     * 返回gravatar头像地址
     *
     * @param email
     * @return
     */
    public String gravatar(String email) {
        String avatarUrl = "https://cn.gravatar.com/avatar";
        if (StringUtils.isEmpty(email)) {
            return avatarUrl;
        }
        String hash = HashUtil.getStringHash(email.trim().toLowerCase(), "MD5");
        return avatarUrl + "/" + hash;
    }

    /**
     * 判断category和cat的交集
     *
     * @param category
     * @param cats
     * @return
     */
    public boolean existCat(Metas category, String cats) {
        String[] arr = null != cats ? cats.split(",") : null;
        if (null != arr && arr.length > 0) {
            for (String c : arr) {
                if (c.trim().equals(category.getName())) {
                    return true;
                }
            }
        }
        return false;
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
     * 格式化unix时间戳为日期
     *
     * @param unixTime
     * @param patten
     * @return
     */
    public String fmtdate(Integer unixTime, String patten) {
        if (null != unixTime && !StringUtils.isEmpty(patten)) {
            return DateUtil.toString(unixTime, patten);
        }
        return "";
    }

    /**
     * 返回主题下的文件路径
     *
     * @param sub
     * @return
     */
    public String theme_url(String sub) {
        return  BlogUtil.THEME + sub;
    }

    /**
     * 显示分类
     *
     * @param categories
     * @return
     */
    public String showCategories(String categories) throws UnsupportedEncodingException {
        if (!StringUtils.isEmpty(categories)) {
            String[] arr = categories.split(",");
            StringBuffer sbuf = new StringBuffer();
            for (String c : arr) {
                sbuf.append("<a href=\"/category/" + URLEncoder.encode(c, "UTF-8") + "\">" + c + "</a>");
            }
            return sbuf.toString();
        }
        return showCategories("默认分类");
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
     * 显示标签
     *
     * @param split 每个标签之间的分隔符
     * @return
     */
    public String showTags(Contents contents) throws UnsupportedEncodingException {
        String split = "";
        if (!StringUtils.isEmpty(contents.getTags())) {
            String[]     arr  = contents.getTags().split(",");
            StringBuffer sbuf = new StringBuffer();
            for (String c : arr) {
                sbuf.append(split).append("<a href=\"/tag/" + URLEncoder.encode(c, "UTF-8") + "\">" + c + "</a>");
            }
            return split.length() > 0 ? sbuf.substring(split.length() - 1) : sbuf.toString();
        }
        return "";
    }

    /**
     * 截取字符串
     *
     * @param str
     * @param len
     * @return
     */
    public String substr(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        }
        return str;
    }
}
