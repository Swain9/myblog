package cn.maolin.myblog.mapper;

import cn.maolin.myblog.entity.Relationships;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/19 16:53
 */
@Repository
public interface RelationshipsMapper {
    /**
     * 统计是否存在
     *
     * @param cid
     * @param mid
     * @return
     */
    long count(@Param("cid") Integer cid, @Param("mid") Integer mid);

    /**
     * 保存一条数据
     *
     * @param relationships
     * @return
     */
    int insert(Relationships relationships);

    /**
     * 删除数据
     *
     * @param cid 文章主键
     * @return
     */
    int delete(Integer cid);

    /**
     * 根据mid查询所有关联的数据
     *
     * @param mid 主键
     * @return List<Relationships>
     */
    List<Relationships> selectAllByMid(int mid);

    /**
     * 删除数据
     *
     * @param mid 元数据id
     * @return
     */
    int deleteByMid(int mid);
}
