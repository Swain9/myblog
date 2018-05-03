package cn.maolin.myblog.resolver;

import cn.maolin.myblog.annotation.LoginUser;
import cn.maolin.myblog.entity.Users;
import cn.maolin.myblog.util.BlogConstant;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Users.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        HttpServletRequest httpRequest = request.getNativeRequest(HttpServletRequest.class);
        //获取用户
        Object object = httpRequest.getSession().getAttribute(BlogConstant.USER_SESSION_KEY);
        if (object == null) {
            return null;
        }
        return object;
    }
}
