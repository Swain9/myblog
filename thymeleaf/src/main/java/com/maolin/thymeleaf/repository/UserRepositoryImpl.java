package com.maolin.thymeleaf.repository;

import com.maolin.thymeleaf.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 45022
 * @since 2017/11/8 20:09
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<>();

    /**
     * 添加或修改一个用户
     *
     * @param user
     * @return
     */
    @Override
    public User saveOrUpdateUser(User user) {
        Long id = user.getId();
        if (id == null) {
            id = counter.incrementAndGet();
            user.setId(id);
        }
        this.userMap.put(id, user);
        return user;
    }

    /**
     * 删除一个用户
     *
     * @param id
     */
    @Override
    public void deleteUser(Long id) {
        this.userMap.remove(id);
    }

    /**
     * 根据ID查询一个用户
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        return this.userMap.get(id);
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @Override
    public List<User> listUser() {
        return new ArrayList<>(this.userMap.values());
    }
}
