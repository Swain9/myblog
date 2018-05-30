package cn.maolin.myblog.mapper;

import cn.maolin.myblog.entity.Contents;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/17 16:03
 */
@Repository
public interface ContentsMapper {
    /**
     * 根据文章状态和文章类型查找对应的文章列表
     *
     * @param status 文章状态
     * @param type   文章类型
     * @return List<Contents>
     */
    List<Contents> selectContentsByStatusAndType(@Param("status") String status, @Param("type") String type);

    /**
     * 根据文章状态和文章类型以及登陆id查找对应的文章列表
     *
     * @param status 文章状态
     * @param type   文章类型
     * @param uid    登陆id
     * @return List<Contents>
     */
    List<Contents> selectContentsByStatusAndTypeAndAuthor(@Param("status") String status, @Param("type") String type, @Param("uid") Integer uid);

    /**
     * 根据文章状态和文章类型查找已公开的文章列表
     *
     * @param status 文章状态
     * @param type   文章类型
     * @return List<Contents>
     */
    List<Contents> selectContentsByStatusAndTypeWithNoLogin(@Param("status") String status, @Param("type") String type);

    /**
     * 根据文章状态和文章类型来随机获取文章
     *
     * @param status 文章状态
     * @param type   文章类型
     * @return List<Contents>
     */
    List<Contents> selectRandomContentsByStatusAndType(@Param("status") String status, @Param("type") String type);

    /**
     * 根据文章状态和文章类型统计对应的文章数量
     *
     * @param status 文章状态
     * @param type   文章类型
     * @return long
     */
    long countContentsByStatusAndType(@Param("status") String status, @Param("type") String type);

    /**
     * 保存一篇文章
     *
     * @param contents 文章
     * @return 返回结果
     */
    int insert(Contents contents);

    /**
     * 根据文章类型和伪页面查询文章数量
     *
     * @param slug 伪页面路径
     * @param type 类型
     * @return 数量
     */
    long countContentsBySlugAndType(@Param("slug") String slug, @Param("type") String type);

    /**
     * 更新一篇文章
     *
     * @param contents 文章
     * @return 返回结果
     */
    int update(Contents contents);

    /**
     * 删除一篇文章
     *
     * @param cid 主键
     * @return 返回结果
     */
    int delete(int cid);

    /**
     * 分页查询
     *
     * @param type 文章类型
     * @return List<Contents>
     */
    List<Contents> selectContentsByPage(String type);

    /**
     * 根据id 主键查询
     *
     * @param cid 主键
     * @return
     */
    Contents selectContentsByCid(Integer cid);

    /**
     * 根据slug查询
     *
     * @param slug 别名
     * @return
     */
    Contents selectContentsBySlug(String slug);

    /**
     * 查询下一篇文章
     *
     * @param contents
     * @return
     */
    Contents selectNextContent(Contents contents);

    /**
     * 查询上一篇文章
     *
     * @param contents
     * @return
     */
    Contents selectPrevContent(Contents contents);

    /**
     * 更新点击率
     *
     * @param temp
     * @return
     */
    int updateHits(Contents temp);
}
