package cn.maolin.myblog.controller;

import cn.maolin.myblog.annotation.Login;
import cn.maolin.myblog.entity.Metas;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.MetasService;
import cn.maolin.myblog.service.SiteService;
import cn.maolin.myblog.util.BlogConstant;
import cn.maolin.myblog.util.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/28 16:10
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    private final MetasService metasService;
    private final SiteService siteService;

    public CategoryController(MetasService metasService, SiteService siteService) {
        this.metasService = metasService;
        this.siteService = siteService;
    }

    @Login
    @GetMapping(value = "")
    public String index(Model model) {
        List<Metas> categories = siteService.getMetas(Types.RECENT_META, Types.CATEGORY, BlogConstant.MAX_POSTS);
        List<Metas> tags = siteService.getMetas(Types.RECENT_META, Types.TAG, BlogConstant.MAX_POSTS);
        model.addAttribute("categories", categories);
        model.addAttribute("tags", tags);
        return "admin/category";
    }

    @Login
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean saveCategory(String cname, Integer mid) {

        metasService.saveMeta(Types.CATEGORY, cname, mid);
        siteService.cleanCache(Types.C_STATISTICS);

        return ResultBean.ok();
    }

    @Login
    @RequestMapping(value = "delete")
    @ResponseBody
    public ResultBean delete(int mid) {

        metasService.delete(mid);
        siteService.cleanCache(Types.C_STATISTICS);

        return ResultBean.ok();
    }

}
