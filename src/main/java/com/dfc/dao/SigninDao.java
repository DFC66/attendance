package com.dfc.dao;

import com.dfc.entity.Signin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author: zsh
 * @Date:21:32 2018/5/10
 * @Description:
 */
public interface SigninDao extends JpaRepository<Signin,Integer>
        , PagingAndSortingRepository<Signin,Integer>
        ,JpaSpecificationExecutor<Signin> {
    @Query(value = "select * from signin where time >= ?1 and time <= ?2 and ip = ?3",nativeQuery = true)
    Signin findSignin(@Param("start_time")String start_time, @Param("end_time")String end_time,@Param("ip")String ip);
}
