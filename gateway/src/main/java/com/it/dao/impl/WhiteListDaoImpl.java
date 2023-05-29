package com.it.dao.impl;

import com.it.dao.WhiteListDao;
import com.it.mapper.WhiteListMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class WhiteListDaoImpl implements WhiteListDao {

    @Resource
    private WhiteListMapper whiteListMapper;


}
