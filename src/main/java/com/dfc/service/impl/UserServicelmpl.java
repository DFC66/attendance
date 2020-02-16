package com.dfc.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.dfc.dao.UserDao;
import com.dfc.entity.User;
import com.dfc.service.UserService;

import javax.annotation.Resource;

/**
 * @author: zsh
 * @Date:14:54 2018/5/5
 * @Description:
 */
@Service("userService")
@Slf4j
public class UserServicelmpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * @param
     * @return  
     * @author zsh
     * @date 2018/5/5 15:19
     * @Description: 
     */
    @Override
    public User findUserByUserName(String username){
        return userDao.findUserByUserName(username);
    }

    @Override
    public void updatePassword(String password, Integer id) {
        userDao.updatePassword(password,id);
    }

    @Override
    public User findUserById(Integer id) {
        User userById = userDao.findUserById(id);
        return userById;
    }
}
