package cn.maolin.myblog.util.contents;

import cn.maolin.myblog.entity.Users;
import cn.maolin.myblog.util.BlogUtil;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;

@Intercepts({
        @Signature(
                //设置拦截的接口
                type = Executor.class,
                //设置拦截接口的方法
                method = "query",
                //设置拦截接口方法中的参数列表以确定正确的方法
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
        )
})
public class ContentsInterceptor implements Interceptor {

    //将要拦截的表名写死，后期可扩展
    private final String TALBE_NAME = "T_CONTENTS";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        Executor executor = (Executor) invocation.getTarget();
        CacheKey cacheKey;
        BoundSql boundSql;
        //由于逻辑关系，只会进入一次
        if (args.length == 4) {
            //4 个参数时
            boundSql = ms.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        } else {
            //6 个参数时
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }

        String sql = boundSql.getSql();
        sql = preSql(sql);
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), parameter);

        List resultList = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, newBoundSql);
        return resultList;
    }

    private String preSql(String s) {
        StringBuilder sb = new StringBuilder(s);

        String SQL = s.toUpperCase();
        //非文章表
        if (!SQL.contains(TALBE_NAME)) {
            return s;
        }

        boolean hasWhere = true;
        if (!SQL.contains("WHERE")) {
            int startTable = SQL.indexOf(TALBE_NAME);
            sb.insert(startTable + TALBE_NAME.length(), " WHERE ");
            SQL = sb.toString().toUpperCase();
            hasWhere = false;
        }

        int startWhere = SQL.indexOf("WHERE");
        String whereSql = SQL.substring(startWhere);

        //已经存在AUTHOR_ID和ALLOW_SEE不再需要处理
        if (whereSql.contains("AUTHOR_ID") || whereSql.contains("ALLOW_SEE")) {
            return s;
        }
        //不存在则添加
        String insertStr = "";
        Users user = BlogUtil.getLoginUser();
        if (user != null && user.getUid() == 1) {
            return s;
        }

        if (user != null) {
            insertStr = " author_id = " + user.getUid() + " ";
        }

        if (user == null) {
            insertStr = " allow_see IS TRUE ";
        }
        sb.insert(startWhere + 5, insertStr);
        if (hasWhere) {
            sb.insert(startWhere + 5 + insertStr.length(), " AND ");
        }
        return sb.toString();

    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
