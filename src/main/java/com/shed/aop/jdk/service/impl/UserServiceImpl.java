package com.shed.aop.jdk.service.impl;

import com.shed.aop.jdk.dao.UserDao;
import com.shed.aop.jdk.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Resource(name = "userDao")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser() {
        userDao.insert();
    }
}
