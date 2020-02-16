package com.dfc.service;

import org.springframework.transaction.annotation.Transactional;
import com.dfc.entity.User;

/**
 * @author: zsh
 * @Date:14:51 2018/5/5
 * @Description:
 */
public interface UserService {

    /**
     * @param username
     * @return  
     * @author zsh
     * @date 2018/5/5 14:53
     * @Description: 通过用户名查询用户
     */
    @Transactional
    User findUserByUserName(String username);

    @Transactional
    void updatePassword(String password,Integer id);

    @Transactional
    User findUserById(Integer id);
}
