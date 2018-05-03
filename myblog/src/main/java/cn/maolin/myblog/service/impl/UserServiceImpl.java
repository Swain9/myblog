package cn.maolin.myblog.service.impl;

import cn.maolin.myblog.entity.Users;
import cn.maolin.myblog.mapper.UsersMapper;
import cn.maolin.myblog.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 张茂林
 * @since 2018/4/13 18:28
 */
@Service
public class UserServiceImpl implements UserService {

    private final UsersMapper usersMapper;

    public UserServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    /**
     * 根据id查询用户信息
     *
     * @param id 主键id
     * @return Users
     */
    @Override
    public Users selectById(Integer id) {
        return usersMapper.selectById(id);
    }

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public Users selectByUserName(String username) {
        return usersMapper.selectByUserName(username);
    }
}
