package com.itheima.mp.Wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testQueryWrapper1(){
        //创建条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //查询的列
        queryWrapper.select("id","username","info","balance");
        //查询条件：名字带“o"
        queryWrapper.like("username","o");
        //查询条件：存款大于等于1000元
        queryWrapper.ge("balance",1000);
        //查询
        List<User> usersList = userMapper.selectList(queryWrapper);
        //输出
        usersList.forEach(System.out::println);
    }

    @Test
    public void testQueryWrapper2(){
        User user = new User();
        user.setBalance(2000);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username","jack");

        userMapper.update(user,queryWrapper);

    }

    @Test
    public void testQueryWrapper3(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        //自定义更新的语句，设置的是set---set balance = balance-200
        updateWrapper.setSql("balance = balance - 200");
        updateWrapper.in("id",1,2,4);
        userMapper.update(null,updateWrapper);
    }

    @Test
    public void testLambdaQueryWrapper1(){
        //创建条件构造器
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询的列
        lambdaQueryWrapper.select(User::getId,User::getUsername,User::getInfo,User::getBalance);
        //查询条件：名字带“o"
        lambdaQueryWrapper.like(User::getUsername,"o");
        //查询条件：存款大于等于1000元
        lambdaQueryWrapper.ge(User::getBalance,1000);
        //查询
        List<User> usersList = userMapper.selectList(lambdaQueryWrapper);
        //输出
        usersList.forEach(System.out::println);
    }

    //自定义sql：更新id为1，2，4的用户的余额，扣200
    @Test
    public void testCustomSqlSegment(){
        //构造条件
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(User::getId,List.of(1L,2L,4L));

        userMapper.updateBanlanceByWrapper(200,lambdaQueryWrapper);
    }

}
