package com.dfc.dao;

import com.dfc.entity.CourseResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: zsh
 * @Date:21:32 2018/5/9
 * @Description:
 */
public interface CourseResultDao extends JpaRepository<CourseResult,Integer>
        , PagingAndSortingRepository<CourseResult,Integer>
        ,JpaSpecificationExecutor<CourseResult> {


      CourseResult findByCourseCodeAndNumber(Integer courseCode,String number);

      @Modifying
      @Query(value = "update course_result set is_deleted = '1',face_img = ?1  where number = ?2 and course_code = ?3",nativeQuery = true)
      void updateIsDeletedStatus(String faceImg, String number,Integer courseCode);

      @Modifying
      @Query(value = "update course_result set is_deleted = '0' where number = ?1 and course_code = ?2",nativeQuery = true)
      void  exitCourse(String number,Integer courseCode);



    @Query(value = "select * from course_result  where  is_deleted = '1' and course_code = ?1 order  by update_time desc ",nativeQuery = true)
    List<CourseResult>  findThisCourseStudents(Integer courseCode);


}
