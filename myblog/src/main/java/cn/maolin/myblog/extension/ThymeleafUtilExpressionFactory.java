package cn.maolin.myblog.extension;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author 张茂林
 * @since 2018/4/18 15:25
 */
public class ThymeleafUtilExpressionFactory implements IExpressionObjectFactory {

    public static final String MY_EXPRESSION_OBJECT_NAME = "myutil";


    private static final ThymeleafUtil MYUTIL_EXPRESSION_OBJECT = new ThymeleafUtil();


    private static final Set<String> ALL_EXPRESSION_OBJECT_NAMES =
            Collections.unmodifiableSet(new LinkedHashSet<String>(java.util.Arrays.asList(
                    MY_EXPRESSION_OBJECT_NAME)));


    /**
     * 返回该工厂类能创建的工具类对象的集合。
     *
     * @return
     */
    @Override
    public Set<String> getAllExpressionObjectNames() {
        return ALL_EXPRESSION_OBJECT_NAMES;
    }

    /**
     * 根据表达式的名称,创建工具类对象
     *
     * @param context
     * @param expressionObjectName
     * @return
     */
    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        return MY_EXPRESSION_OBJECT_NAME.equals(expressionObjectName) ? MYUTIL_EXPRESSION_OBJECT : null;
    }

    /**
     * 返回该工具对象是否可缓存。(可能理解的不太到位)
     *
     * @param expressionObjectName
     * @return
     */
    @Override
    public boolean isCacheable(String expressionObjectName) {
        return (expressionObjectName != null && expressionObjectName.equals(MY_EXPRESSION_OBJECT_NAME));
    }
}
