package cn.maolin.myblog.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张茂林
 * @since 2018/4/13 14:59
 */
@Configuration
@MapperScan(basePackages = {"cn.maolin.myblog.mapper"})
public class MyBaitsConfig {
}
