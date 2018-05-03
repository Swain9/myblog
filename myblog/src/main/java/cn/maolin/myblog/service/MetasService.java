package cn.maolin.myblog.service;

import cn.maolin.myblog.entity.Metas;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/18 18:21
 */
public interface MetasService {
    /**
     * 获取元数据
     *
     * @param type 类型
     * @return List<Metas>
     */
    List<Metas> getMetas(String type);

    /**
     * 保存多个项目
     *
     * @param cid   文章id
     * @param names 类型名称列表
     * @param type  类型，tag or category
     */
    void insert(Integer cid, String names, String type);

    /**
     * 保存一个元数据
     *
     * @param type  类型
     * @param cname 名称
     * @param mid   主键
     */
    void saveMeta(String type, String cname, Integer mid);

    /**
     * 删除一个元数据
     *
     * @param mid 主键
     */
    void delete(int mid);
}
