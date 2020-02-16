package com.dfc.dao;

import com.dfc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author: zsh
 * @Date:14:56 2018/5/5
 * @Description:
 */
public interface UserDao extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {



    User findUserByUserName(String userName);

    @Modifying
    @Query(value = "update user set password = ?1 where id = ?2",nativeQuery = true)
    void updatePassword(@Param(value = "password") String password, @Param(value = "id")Integer id);


    User findUserById(Integer id);
}
