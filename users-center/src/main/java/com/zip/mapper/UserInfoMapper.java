package com.zip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pojo.user.UserInfo;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
