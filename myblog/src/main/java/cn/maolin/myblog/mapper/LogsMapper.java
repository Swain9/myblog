package cn.maolin.myblog.mapper;

import cn.maolin.myblog.entity.Logs;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/17 21:36
 */
@Repository
public interface LogsMapper {
    /**
     * 查询所有日志信息
     *
     * @return List<Logs>
     */
    List<Logs> selectLogs();

    /**
     * 保存一条日志
     *
     * @param logs 日志
     * @return 影响条数
     */
    int insert(Logs logs);
}
