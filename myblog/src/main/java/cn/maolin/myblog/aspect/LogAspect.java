package cn.maolin.myblog.aspect;

import cn.maolin.myblog.annotation.Log;
import cn.maolin.myblog.entity.Logs;
import cn.maolin.myblog.entity.Users;
import cn.maolin.myblog.exception.TipException;
import cn.maolin.myblog.mapper.LogsMapper;
import cn.maolin.myblog.util.DateUtil;
import cn.maolin.myblog.util.HttpContextUtils;
import cn.maolin.myblog.util.IPUtils;
import cn.maolin.myblog.util.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 张茂林
 * @since 2018/4/21 11:55
 */
@Aspect
@Component
public class LogAspect {


    private final LogsMapper logsMapper;

    public LogAspect(LogsMapper logsMapper) {
        this.logsMapper = logsMapper;
    }

    @Around(value = "@annotation(log)")
    public Object save(ProceedingJoinPoint pjp, Log log) throws Throwable {

        Object result = pjp.proceed();

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

        Object objUser = request.getSession().getAttribute("user");
        if (objUser == null) {
            throw new TipException("尚未登陆");
        }
        Users users = (Users) objUser;
        Object[] args = pjp.getArgs();

        String data = JsonUtils.objectToJson(args[0]);

        Logs logs = new Logs();
        logs.setIp(IPUtils.getIpAddr(request));
        logs.setAuthorId(users.getUid());
        logs.setAction(log.value());
        logs.setCreated(DateUtil.nowUnix());
        logs.setData(data);

        logsMapper.insert(logs);


        return result;
    }

}
