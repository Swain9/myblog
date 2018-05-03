package cn.maolin.myblog.mapper;

import cn.maolin.myblog.entity.Attach;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/17 20:20
 */
@Repository
public interface AttachMapper {
    /**
     * 统计附件数
     *
     * @return long:附件数
     */
    long countAttachs();

    /**
     * 保存一个附件信息
     *
     * @param attach attach
     * @return
     */
    int save(Attach attach);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return Attach
     */
    Attach selectById(Integer id);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Attach> select();

}
