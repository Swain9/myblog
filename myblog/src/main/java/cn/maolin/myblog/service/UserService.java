package cn.maolin.myblog.service;

import cn.maolin.myblog.entity.Users;

/**
 * @author 张茂林
 * @since 2018/4/13 18:27
 */
public interface UserService {
    /**
     * 根据id查询用户信息
     *
     * @param id 主键id
     * @return Users
     */
    Users selectById(Integer id);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    Users selectByUserName(String username);
}
