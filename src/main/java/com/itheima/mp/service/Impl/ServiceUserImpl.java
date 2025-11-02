package com.itheima.mp.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.enums.UserStatus;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUserImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void deductBalanceById(Long id, Integer amount) {
        //1.判断用户是否存在
        User user = this.getById(id);
        if (user != null || user.getStatus() == UserStatus.FREEZE) {
            throw new RuntimeException("用户账号有问腿");
        }
        //2.判断余额是否充足；当前的用户的余额是否大于等于要扣除的金额
        if (user.getBalance() < amount) {
            throw new RuntimeException("余额不足");
        }

        //3.扣减
        //userMapper.deductBalanceById(id,amount);
        //获取扣减之后的余额
        int remainBalance = user.getBalance() - amount;
        this.lambdaUpdate()
                .set(User::getBalance, remainBalance)  //设置余额
                .set(remainBalance == 0, User::getStatus, 2)
                .eq(User::getId, id)  //条件
                .update();
    }
}
