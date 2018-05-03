package cn.maolin.myblog.annotation;

import java.lang.annotation.*;

/**
 * @author 张茂林
 * @since 2018/4/15 15:19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
