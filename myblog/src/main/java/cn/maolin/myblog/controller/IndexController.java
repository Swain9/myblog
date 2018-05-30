package cn.maolin.myblog.controller;

import cn.maolin.myblog.annotation.Login;
import cn.maolin.myblog.annotation.LoginUser;
import cn.maolin.myblog.entity.Contents;
import cn.maolin.myblog.entity.Users;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.ContentsService;
import cn.maolin.myblog.util.BlogConstant;
import cn.maolin.myblog.util.BlogUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * @author 张茂林
 * @since 2018/4/24 17:03
 */
@Controller
public class IndexController {


    private final ContentsService contentsService;

    public IndexController(ContentsService contentsService) {
        this.contentsService = contentsService;
    }


    /**
     * 首页
     *
     * @param user  是否登陆
     * @param model 数据
     * @param limit 分页
     * @return
     */
    @GetMapping(value = {"/", ""})
    public String index(@LoginUser Users user, Model model, @RequestParam(defaultValue = "12") int limit) {
        return this.index(user, model, 1, limit);
    }

    /**
     * 首页分页
     *
     * @param model 是否登陆
     * @param page  数据
     * @param limit 分页
     * @return
     */
    @GetMapping(value = {"/page/{page}", "/page/{page}.html"})
    public String index(@LoginUser Users user, Model model, @PathVariable int page, @RequestParam(defaultValue = "12") int limit) {
        page = page < 0 || page > BlogConstant.MAX_PAGE ? 1 : page;
        if (page > 1) {
            model.addAttribute("title", "第" + page + "页");
        }

        List<Contents> list = contentsService.selectIndexContentsByPageWithLoginType(page, limit, user);
        PageInfo<Contents> articles = new PageInfo<>(list);

        model.addAttribute("articles", articles);
        model.addAttribute("page_num", page);
        model.addAttribute("limit", limit);
        model.addAttribute("is_home", true);
        model.addAttribute("page_prefix", "/page");
        return "themes/default/index";
        //return BlogUtil.render("index");
    }

    /**
     * 文章页
     */
    @GetMapping(value = {"/article/{cid}", "/article/{cid}.html"})
    public String post(@LoginUser Users users, @PathVariable String cid, @RequestParam(defaultValue = "1") int cp, Model model) {
        Optional<Contents> contentsOptional = contentsService.getContents(cid);
        if (!contentsOptional.isPresent()) {
            return BlogConstant.RENDER_404;
        }
        Contents contents = contentsOptional.get();
        if (Types.DRAFT.equals(contents.getStatus())) {
            return BlogConstant.RENDER_404;
        }
        if (!contents.getAllowSee()) {
            if (users == null) {
                return BlogConstant.RENDER_404;
            }
            if (users.getUid() != 1 && users.getUid().intValue() != contents.getAuthorId().intValue()) {
                return BlogConstant.RENDER_404;
            }
        }
        model.addAttribute("article", contents);
        model.addAttribute("is_post", true);
        if (contents.getAllowComment()) {
            model.addAttribute("cp", cp);
        }
        Contents temp = new Contents();
        temp.setCid(contents.getCid());
        temp.setHits(contents.getHits() + 1);
        contentsService.updateArticleHits(temp);


        return BlogUtil.render("post");
    }

}
