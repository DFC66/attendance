package com.dfc.dao;

import com.dfc.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


/**
 * @author: zsh
 * @Date:19:51 2018/5/5
 * @Description:
 */
public interface StudentDao extends JpaRepository<Student,Integer>
        , PagingAndSortingRepository<Student,Integer>
        ,JpaSpecificationExecutor<Student> {

    Student findByNumber(String number);

    Student findByOpenid(String openid);


    @Modifying
    @Query(value = "update student set number = ?1,name = ?2, classes = ?3 where openid = ?4",nativeQuery = true)
    int updateMessage(@Param(value = "number") String number, @Param(value = "name") String name,  @Param(value = "classes") String classes ,@Param(value = "openid")String openid);



     boolean  existsByOpenid(String openid);
}
