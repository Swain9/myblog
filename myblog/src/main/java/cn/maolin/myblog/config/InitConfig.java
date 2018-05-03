package cn.maolin.myblog.config;

import cn.maolin.myblog.extension.ThymeleafUtil;
import cn.maolin.myblog.service.SiteService;
import cn.maolin.myblog.util.MapCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张茂林
 * @since 2018/4/14 18:20
 */
@Configuration
@ConditionalOnClass(ThymeleafProperties.class)
public class InitConfig {

    private final ThymeleafProperties thymeleafProperties;

    public InitConfig(ThymeleafProperties thymeleafProperties) {
        this.thymeleafProperties = thymeleafProperties;
    }

    @Autowired
    public ThymeleafViewResolver thymeleafViewResolver(ThymeleafViewResolver thymeleafViewResolver) {
        Map<String, Object> vars = new HashMap<>(2);
        vars.put("version", thymeleafProperties.getVersion());
        vars.put("enableCdn", thymeleafProperties.getEnableCdn());
        thymeleafViewResolver.setStaticVariables(vars);
        return thymeleafViewResolver;
    }

    @Bean
    public MapCache cache() {
        return MapCache.single();
    }

    @Bean
    @ConditionalOnMissingBean
    public ThymeleafUtil utilDialect() {
        return new ThymeleafUtil();
    }

}
