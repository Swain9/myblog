package cn.maolin.myblog.config;

import cn.maolin.myblog.util.contents.ContentsInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张茂林
 * @since 2018/4/13 14:59
 */
@Configuration
@MapperScan(basePackages = {"cn.maolin.myblog.mapper"})
public class MyBaitsConfig {

    @Bean
    public Interceptor contentsInterceptor(){
        return new ContentsInterceptor();
    }

}
