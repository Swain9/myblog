package cn.maolin.myblog.annotation;

import java.lang.annotation.*;

/**
 * @author 张茂林
 * @since 2018/4/21 11:21
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
