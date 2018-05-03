package cn.maolin.myblog.mapper;

import cn.maolin.myblog.entity.Users;
import org.springframework.stereotype.Repository;

/**
 * @author 张茂林
 * @since 2018/4/13 15:05
 */
@Repository
public interface UsersMapper {
    /**
     * 根据id查询用户
     *
     * @param uid 主键id
     * @return Users
     */
    Users selectById(Integer uid);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return Users
     */
    Users selectByUserName(String username);
}
