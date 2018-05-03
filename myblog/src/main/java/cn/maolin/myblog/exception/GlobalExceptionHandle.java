package cn.maolin.myblog.exception;

import cn.maolin.myblog.util.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常处理器
 *
 * @author 茂林
 * @since 2017/11/21 10:31
 */
@RestControllerAdvice
public class GlobalExceptionHandle {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    /**
     * 自定义异常处理
     *
     * @param e TipException
     * @return ResultBean
     */
    @ExceptionHandler(TipException.class)
    public ResultBean handleTipException(TipException e) {
        logger.error(e.getMessage(), e);
        return ResultBean.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResultBean handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return ResultBean.error("数据库中已存在该记录");
    }

    @ExceptionHandler(BindException.class)
    public ResultBean bindException(BindException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        for (ObjectError error : allErrors) {
            logger.error(error.getDefaultMessage());
        }
        return ResultBean.error(allErrors.get(0).getDefaultMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResultBean handleException(Exception e) {

        logger.error(e.getMessage(), e);
        return ResultBean.error("服务器繁忙,请稍后再试");
    }
}
