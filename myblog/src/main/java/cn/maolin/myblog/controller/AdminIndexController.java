package cn.maolin.myblog.controller;

import cn.maolin.myblog.annotation.Login;
import cn.maolin.myblog.entity.Comments;
import cn.maolin.myblog.entity.Contents;
import cn.maolin.myblog.entity.Logs;
import cn.maolin.myblog.model.dto.Statistics;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.SiteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/16 10:41
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    private final SiteService siteService;

    public AdminIndexController(SiteService siteService) {
        this.siteService = siteService;
    }

    /**
     * 仪表盘
     *
     * @return
     */
    @Login
    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        //获取最新的5条评论
        List<Comments> comments = siteService.recentComments(5);
        //获取最新的5条文章
        List<Contents> contents = siteService.getContents(Types.RECENT_ARTICLE, 5);
        //统计对象
        Statistics statistics = siteService.getStatistics();
        // 取最新的20条日志
        List<Logs> logs = siteService.getLogs(20);

        model.addAttribute("comments", comments);
        model.addAttribute("articles", contents);
        model.addAttribute("statistics", statistics);
        model.addAttribute("logs", logs);
        return "admin/index";
    }
}
