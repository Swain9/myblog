package cn.maolin.myblog.controller;

import cn.maolin.myblog.entity.Contents;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.ContentsService;
import cn.maolin.myblog.util.BlogConstant;
import cn.maolin.myblog.util.BlogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 文章页
     */
    @GetMapping(value = {"article/{cid}", "article/{cid}.html"})
    public String post(@PathVariable String cid, @RequestParam(defaultValue = "1") int cp, Model model) {
        Optional<Contents> contentsOptional = contentsService.getContents(cid);
        if (!contentsOptional.isPresent()) {
            return BlogConstant.RENDER_404;
        }
        Contents contents = contentsOptional.get();
        if (Types.DRAFT.equals(contents.getStatus())) {
            return BlogConstant.RENDER_404;
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
