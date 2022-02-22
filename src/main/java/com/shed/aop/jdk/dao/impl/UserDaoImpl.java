package com.shed.aop.jdk.dao.impl;

import com.shed.aop.jdk.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void insert() {
        System.out.println("[UserDao]执行SQL数据添加...");
    }
}
