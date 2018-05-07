package cn.maolin.myblog.util;

import cn.maolin.myblog.model.dto.PluginMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/15 15:33
 */
public class BlogConstant {

    /**
     * 重定向路径
     */
    public static final String NO_LOGIN_URL = "/admin/login";
    /**
     * 请求头参数:是否为ajax请求
     */
    public static final String X_REQUESTED_WITH = "X-Requested-With";
    /**
     * 请求头参数值:是否为ajax请求
     */
    public static final String XML_HTTPR_EQUEST = "XMLHttpRequest";
    /**
     * 允许错误次数
     */
    public static final Integer ERROR_COUNT = 3;
    /**
     * 登录session_key
     */
    public static final String USER_SESSION_KEY = "user";
    /**
     * 显示的最大评论数
     */
    public static final int COMMENTS_COUNT_MAX = 10;
    /**
     * 显示的最大文章数
     */
    public static final int CONTENT_COUNT_MAX = 20;
    /**
     * 显示最大的日志数
     */
    public static final int LOGS_COUNT_MAX = 50;
    /**
     * 插件菜单 Attribute Name
     */
    public static final String PLUGINS_MENU_NAME = "plugin_menus";
    /**
     * 插件菜单
     */
    public static final List<PluginMenu> PLUGIN_MENUS = new ArrayList<>();
    /**
     * 后台URI前缀
     */
    public static final String ADMIN_URI = "/admin";
    /**
     * 后台登录地址
     */
    public static final String LOGIN_URI = "/admin/login";

    /**
     * 文章标题最多可以输入的文字个数
     */
    public static final int MAX_TITLE_COUNT = 200;
    /**
     * 文章最多可以输入的文字数
     */
    public static final int MAX_TEXT_COUNT = 200000;
    /**
     * 最大获取文章条数
     */
    public static final int MAX_POSTS = 9999;
    /**
     * 上传文件最大20M
     */
    public static final int MAX_FILE_SIZE = 204800;

    public static final String RENDER_404 = "comm/error_404";

    public static final String ENV_SUPPORT_163_MUSIC = "app.support_163_music";
    public static final String ENV_SUPPORT_GIST = "app.support_gist";
    public static final String MP3_PREFIX = "[mp3:";
    public static final String MUSIC_IFRAME = "<iframe frameborder=\"no\" border=\"0\" marginwidth=\"0\" marginheight=\"0\" width=350 height=106 src=\"//music.163.com/outchain/player?type=2&id=$1&auto=0&height=88\"></iframe>";
    public static final String MUSIC_REG_PATTERN = "\\[mp3:(\\d+)\\]";
    public static final String GIST_PREFIX_URL = "https://gist.github.com/";
    public static final String GIST_REG_PATTERN = "&lt;script src=\"https://gist.github.com/(\\w+)/(\\w+)\\.js\">&lt;/script>";
    public static final String GIST_REPLATE_PATTERN = "<script src=\"https://gist.github.com/$1/$2\\.js\"></script>";

}
