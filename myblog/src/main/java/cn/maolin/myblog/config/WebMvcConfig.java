package cn.maolin.myblog.config;

import cn.maolin.myblog.interceptor.LoginInterceptor;
import cn.maolin.myblog.resolver.LoginUserHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/15 14:47
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 注册拦截器
     *
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登陆拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //登陆用户注解解析器
        resolvers.add(new LoginUserHandlerMethodArgumentResolver());
    }
    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files").addResourceLocations("file:C:/upload");
    }
    */
}
