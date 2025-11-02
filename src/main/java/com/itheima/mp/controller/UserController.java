package com.itheima.mp.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "用户接口管理")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    @ApiOperation("新增用户")
    @PostMapping
    public void saveUser(@RequestBody UserFormDTO userFormDTO) {
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        userService.save(user);
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.removeById(id);
    }

    @ApiOperation("根据id查询用户")
    @GetMapping("/{id}")
    public UserVO getUser(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @ApiOperation("根据id批量查询")
    @GetMapping
    public List<UserVO> getUsers(@RequestParam("ids") List<Long> ids) {
        List<User> userList = userService.listByIds(ids);

        return BeanUtil.copyToList(userList, UserVO.class);
    }

    @PutMapping("/{id}deduct/{amount}")
    public void deductUser(@PathVariable("id") Long id, @PathVariable("amount") Integer amount) {
        userService.deductBalanceById(id,amount);
    }

    @PostMapping("/list")
    public List<UserVO> queryList(@RequestBody UserQuery userquery) {
        String username = userquery.getName();
        Integer status = userquery.getStatus();
        Integer maxBalance = userquery.getMaxBalance();
        Integer minBalance = userquery.getMinBalance();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

//        queryWrapper.lambda()

                    //成立的条件;字段；查询的关键字；如果第一个值为true才会设置当前这个条件到where中
//                .like(StrUtil.isNotBlank(username),User::getUsername,username)
//                .eq(status != null,User::getStatus,status)
//                .ge(minBalance != null,User::getBalance,minBalance)
//                .le(maxBalance != null,User::getBalance,maxBalance);
//
//        List<User> userList = userService.list(queryWrapper);


        List<User> userList = userService.lambdaQuery()
                .like(StrUtil.isNotBlank(username), User::getUsername, username)
                .eq(status != null, User::getStatus, status)
                .ge(minBalance != null, User::getBalance, minBalance)
                .le(maxBalance != null, User::getBalance, maxBalance)
                .list();

        return BeanUtil.copyToList(userList,UserVO.class);

    }

}
