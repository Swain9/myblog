package cn.maolin.myblog.mapper;

import cn.maolin.myblog.entity.Metas;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/17 21:25
 */
@Repository
public interface MetasMapper {
    /**
     * 根据type查询元数据数量
     *
     * @param type:分类
     * @return long
     */
    long countMetas(String type);

    /**
     * 根据type查询元数据
     *
     * @param type 分类
     * @return List<Metas>
     */
    List<Metas> selectMetasByType(String type);

    /**
     * 根据type和name查询元数据
     *
     * @param name 名称
     * @param type 类型
     * @return Metas
     */
    Metas selectMetasByTypeAndName(@Param("name") String name, @Param("type") String type);

    /**
     * 保存
     *
     * @param metas Metas
     * @return
     */
    int insert(Metas metas);

    /**
     * 查询出最新的标签/分类数据(含文章数)
     *
     * @param type  类型
     * @param limit 数量
     * @return List<Metas>
     */
    List<Metas> selectRecentMetasWithCount(@Param("type") String type, @Param("limit") int limit);

    /**
     * 查询出随机的标签/分类数据(含文章数)
     *
     * @param type  类型
     * @param limit 数量
     * @return List<Metas>
     */
    List<Metas> selectRandomMetasWithCount(@Param("type") String type, @Param("limit") int limit);

    /**
     * 更新一个元数据
     *
     * @param metas metas
     * @return
     */
    int update(Metas metas);

    /**
     * 根据主键查询元数据
     *
     * @param mid 主键
     * @return Metas
     */
    Metas selectMetasByMid(int mid);

    /**
     * 删除一个元数据
     *
     * @param mid mid
     * @return
     */
    int deleteByMid(int mid);
}
