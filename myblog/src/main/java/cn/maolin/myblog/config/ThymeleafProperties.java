package cn.maolin.myblog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 张茂林
 * @since 2018/4/14 17:13
 */
@ConfigurationProperties(prefix = "blog.config")
@Component
public class ThymeleafProperties {

    private String version;
    private String enableCdn;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnableCdn() {
        return enableCdn;
    }

    public void setEnableCdn(String enableCdn) {
        this.enableCdn = enableCdn;
    }

}
