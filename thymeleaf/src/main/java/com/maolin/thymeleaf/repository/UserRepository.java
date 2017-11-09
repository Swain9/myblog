package com.maolin.thymeleaf.repository;

import com.maolin.thymeleaf.domain.User;

import java.util.List;

/**
 * @author 45022
 * @since 2017/11/8 19:55
 */
public interface UserRepository {
    /**
     * 添加或修改一个用户
     *
     * @param user
     * @return
     */
    User saveOrUpdateUser(User user);

    /**
     * 删除一个用户
     *
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 根据ID查询一个用户
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 获取用户列表
     *
     * @return
     */
    List<User> listUser();
}
