package cn.maolin.myblog.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HttpContextUtils {
    /**
     * 获取request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        //todo
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
