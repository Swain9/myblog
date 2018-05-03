package cn.maolin.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 张茂林
 * @since 2018/4/14 10:09
 */
@Controller
@RequestMapping("/install")
public class InstallController {

    @RequestMapping
    public String index(){
        return "install";
    }

}
