package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.itheima.mp.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Update("update user set balance = balance - #{amount} $(ew.customSqlSegment)")
    void updateBanlanceByWrapper(@Param("amount") int amount, @Param("ew") LambdaQueryWrapper<User> queryWrapper);

    @Update("update user set balance = balance - #{amount} where id = #{id}")
    void deductBalanceById(@Param("id") Long id, @Param("amount") Integer amount);

//    void saveUser(User user);
//
//    void deleteUser(Long id);
//
//    void updateUser(User user);
//
//    User queryUserById(@Param("id") Long id);
//
//    List<User> queryUserByIds(@Param("ids") List<Long> ids);
}
