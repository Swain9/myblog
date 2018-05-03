package cn.maolin.myblog.controller;

import cn.maolin.myblog.annotation.Log;
import cn.maolin.myblog.annotation.Login;
import cn.maolin.myblog.annotation.LoginUser;
import cn.maolin.myblog.entity.Contents;
import cn.maolin.myblog.entity.Metas;
import cn.maolin.myblog.entity.Users;
import cn.maolin.myblog.model.dto.LogActions;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.ContentsService;
import cn.maolin.myblog.service.MetasService;
import cn.maolin.myblog.service.SiteService;
import cn.maolin.myblog.util.BlogConstant;
import cn.maolin.myblog.util.ResultBean;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author 张茂林
 * @since 2018/4/18 17:12
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {


    private final MetasService metasService;
    private final ContentsService contentsService;
    private final SiteService siteService;

    public ArticleController(MetasService metasService, ContentsService contentsService, SiteService siteService) {
        this.metasService = metasService;
        this.contentsService = contentsService;
        this.siteService = siteService;
    }

    /**
     * 文章管理首页
     *
     * @param page
     * @param limit
     * @param model
     * @return
     */
    @Login
    @GetMapping(value = "")
    public String index(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int limit,
                        Model model) {
        List<Contents> list = contentsService.selectContentsByPage(page, limit);
        PageInfo<Contents> articles = new PageInfo<>(list);

        model.addAttribute("articles", articles);
        return "admin/article_list";
    }

    /**
     * 文章发布页面
     *
     * @param model
     * @return
     */
    @Login
    @GetMapping("/publish")
    public String newArticle(Model model) {
        List<Metas> categories = metasService.getMetas(Types.CATEGORY);
        model.addAttribute("categories", categories);
        //model.addAttribute(Types.ATTACH_URL, Commons.site_option(Types.ATTACH_URL, Commons.site_url()));
        return "admin/article_edit";
    }

    /**
     * 文章编辑页面
     *
     * @param cid
     * @param model
     * @return
     */
    @Login
    @GetMapping(value = "/{cid}")
    public String editArticle(@PathVariable String cid, Model model) {
        Optional<Contents> contents = contentsService.getContents(cid);
        if (!contents.isPresent()) {
            return BlogConstant.RENDER_404;
        }
        model.addAttribute("contents", contents.get());
        List<Metas> categories = metasService.getMetas(Types.CATEGORY);
        model.addAttribute("categories", categories);
        model.addAttribute("active", "article");
        //model.addAttribute(Types.ATTACH_URL, Commons.site_option(Types.ATTACH_URL, Commons.site_url()));
        return "admin/article_edit";
    }


    /**
     * 发布文章操作
     *
     * @return
     */
    @Login
    @PostMapping(value = "/publish")
    @ResponseBody
    public ResultBean publishArticle(@Valid Contents contents, BindingResult bindingResult, @LoginUser Users users) {

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                return ResultBean.error(error.getDefaultMessage());
            }
        }

        contents.setType(Types.ARTICLE);
        contents.setAuthorId(users.getUid());
        if (StringUtils.isEmpty(contents.getCategories())) {
            contents.setCategories("默认分类");
        }

        contentsService.publish(contents);
        siteService.cleanCache(Types.C_STATISTICS);
        return ResultBean.ok(contents.getCid());
    }

    /**
     * 修改文章操作
     *
     * @return
     */
    @Login
    @PostMapping(value = "/modify")
    @ResponseBody
    public ResultBean modifyArticle(@Valid Contents contents) {
        if (null == contents || null == contents.getCid()) {
            return ResultBean.error("缺少参数，请重试");
        }
        Integer cid = contents.getCid();
        contentsService.updateArticle(contents);
        return ResultBean.ok(cid);

    }

    /**
     * 删除文章操作
     *
     * @param cid
     * @return
     */
    @Log(LogActions.DEL_ARTICLE)
    @Login
    @RequestMapping("/delete")
    @ResponseBody
    public ResultBean delete(int cid) {

        contentsService.delete(cid);
        siteService.cleanCache(Types.C_STATISTICS);

        return ResultBean.ok();
    }

}
