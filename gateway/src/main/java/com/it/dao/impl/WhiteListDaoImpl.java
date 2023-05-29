package com.it.dao.impl;

import com.it.dao.WhiteListDao;
import com.it.mapper.WhiteListMapper;
import org.springframework.stereotype.Repository;
import pojo.gateway.WhiteList;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class WhiteListDaoImpl implements WhiteListDao {

    @Resource
    private WhiteListMapper whiteListMapper;


    @Override
    public List<WhiteList> getAll() {
        return whiteListMapper.selectList(null);
    }
}
