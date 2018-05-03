package cn.maolin.myblog.interceptor;

import cn.maolin.myblog.annotation.Login;
import cn.maolin.myblog.util.BlogConstant;
import cn.maolin.myblog.util.JsonUtils;
import cn.maolin.myblog.util.ResultBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张茂林
 * @since 2018/4/15 12:46
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Login login;
        //判断handler的类型
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            login = handlerMethod.getMethodAnnotation(Login.class);
        } else {
            return true;
        }

        if (login == null) {
            return true;
        }

        Object user = request.getSession().getAttribute(BlogConstant.USER_SESSION_KEY);
        if (user == null) {

            String header = request.getHeader(BlogConstant.X_REQUESTED_WITH);
            if (BlogConstant.XML_HTTPR_EQUEST.equalsIgnoreCase(header)) {
                ResultBean bean = ResultBean.error(HttpStatus.UNAUTHORIZED.value(), "未登录", BlogConstant.NO_LOGIN_URL);
                String json = JsonUtils.objectToJson(bean);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(json);
            } else {
                response.sendRedirect(BlogConstant.NO_LOGIN_URL);
            }
            return false;

        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String uri = request.getRequestURI();
        if (uri.startsWith(BlogConstant.ADMIN_URI) && !uri.startsWith(BlogConstant.LOGIN_URI)) {
            request.setAttribute(BlogConstant.PLUGINS_MENU_NAME, BlogConstant.PLUGIN_MENUS);
        }
    }
}
