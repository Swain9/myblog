package cn.maolin.myblog.controller;

import cn.maolin.myblog.annotation.Log;
import cn.maolin.myblog.annotation.LoginUser;
import cn.maolin.myblog.entity.Users;
import cn.maolin.myblog.model.dto.LogActions;
import cn.maolin.myblog.model.param.LoginParam;
import cn.maolin.myblog.service.UserService;
import cn.maolin.myblog.util.BlogConstant;
import cn.maolin.myblog.util.MapCache;
import cn.maolin.myblog.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 张茂林
 * @since 2018/4/15 10:36
 */
@Controller
public class LoginController {

    @Autowired
    private MapCache cache;

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String login(@LoginUser Users users) {
        if (null != users) {
            return "redirect:/admin/index";
        }
        return "admin/login";
    }

    @Log(LogActions.LOGIN)
    @PostMapping("/admin/login")
    @ResponseBody
    public ResultBean adminLogin(LoginParam param, HttpSession session) {
        //错误次数
        Integer errorCount = cache.get("login_error_count");
        errorCount = null == errorCount ? 0 : errorCount;
        if (errorCount > BlogConstant.ERROR_COUNT) {
            return ResultBean.error("您输入密码已经错误超过3次，请10分钟后尝试");
        }

        Users user = userService.selectByUserName(param.getUsername());
        if (user == null) {
            return ResultBean.error("账号或密码错误");
        }
        if (!user.getPassword().equals(param.getPassword())) {
            return ResultBean.error("账号或密码错误");
        }

        //rememberme实现


        session.setAttribute(BlogConstant.USER_SESSION_KEY, user);


        return ResultBean.ok();


    }
}
