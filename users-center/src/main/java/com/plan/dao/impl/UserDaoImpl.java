package com.plan.dao.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.plan.dao.UserDao;
import com.plan.mapper.UserInfoMapper;
import com.plan.mapper.UserMapper;
import constants.CommonConstant;
import org.springframework.stereotype.Repository;
import pojo.user.User;
import pojo.user.UserInfo;

import javax.annotation.Resource;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, username);
        userLambdaQueryWrapper.eq(User::getDelFlag, CommonConstant.EXIST);
        return userMapper.selectOne(userLambdaQueryWrapper);
    }

    @Override
    public boolean save(User user) {
        return userMapper.insert(user) < 1;
    }

    @Override
    public boolean saveUserInfo(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo) < 1;
    }

    @Override
    public User getUserByPhone(String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, phone).eq(User::getDelFlag, CommonConstant.EXIST);
        return userMapper.selectOne(wrapper);
    }
}
