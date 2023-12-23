package com.plan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pojo.user.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
