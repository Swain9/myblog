package cn.maolin.myblog;

import cn.maolin.myblog.entity.Users;
import cn.maolin.myblog.mapper.UsersMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void contextLoads() {
        Users tUsers = usersMapper.selectById(1);
        Assert.assertNotNull(tUsers);
    }

}
