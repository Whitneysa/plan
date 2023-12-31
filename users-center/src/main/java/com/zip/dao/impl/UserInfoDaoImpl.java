package com.zip.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zip.dao.UserInfoDao;
import com.zip.mapper.UserInfoMapper;
import constants.CommonConstant;
import org.springframework.stereotype.Repository;
import pojo.user.UserInfo;

import javax.annotation.Resource;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getByUserId(String userId) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getUserId, userId);
        queryWrapper.eq(UserInfo::getDelFlag, CommonConstant.EXIST);
        return userInfoMapper.selectOne(queryWrapper);
    }
}
