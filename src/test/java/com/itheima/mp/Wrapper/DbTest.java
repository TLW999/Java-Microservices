package com.itheima.mp.Wrapper;

import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DbTest {

    @Test
    public void testQueryById(){
        User user = Db.getById(1L, User.class);
        System.out.println(user);
    }

    //查询名字中包含o且余额大于等于1000的用户
    @Test
    public void testQueryByNameAndBalance(){
        List<User> userList = Db.lambdaQuery(User.class)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000)
                .list();
        userList.forEach(System.out::println);
    }

    //更新用户名为Rose的余额为2000
    @Test
    public void testUpdate(){
        Db.lambdaUpdate(User.class)
                .set(User::getUsername, "Rose")
                .set(User::getBalance, 2000)
                .update();
    }
}
