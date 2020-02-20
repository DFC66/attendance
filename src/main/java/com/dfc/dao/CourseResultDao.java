package com.dfc.dao;

import com.dfc.entity.CourseResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author: zsh
 * @Date:21:32 2018/5/9
 * @Description:
 */
public interface CourseResultDao extends JpaRepository<CourseResult,Integer>
        , PagingAndSortingRepository<CourseResult,Integer>
        ,JpaSpecificationExecutor<CourseResult> {


      CourseResult findByCourseCodeAndNumber(Integer courseCode,String number);

//     CourseResult findByNameAndTeacherName(String name, String teacherName);
}
