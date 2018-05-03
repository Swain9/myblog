package cn.maolin.myblog.service;

import cn.maolin.myblog.entity.Attach;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/5/3 10:49
 */
public interface AttachService {
    /**
     * 保存附件信息
     *
     * @param attach
     */
    void save(Attach attach);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Attach findById(Integer id);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 数量
     * @return List<Attach>
     */
    List<Attach> page(int page, int limit);

}
