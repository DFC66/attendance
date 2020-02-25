package com.dfc.dao;

import com.dfc.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: zsh
 * @Date:21:32 2018/5/9
 * @Description:
 */
public interface CourseDao extends JpaRepository<Course,Integer>
        , PagingAndSortingRepository<Course,Integer>
        ,JpaSpecificationExecutor<Course> {

    @Query(value = "select * from course where start_time <= ?1 and end_time >= ?1 and today = ?2",nativeQuery = true)
    Course findCourseByTime(@Param("data")String date,@Param("today")String today);


     Course findByCourseCode(Integer courseCode);

    @Query(value = "select c.*  from  course c inner  join course_result cr  on c.course_code =cr.course_code where cr.number = ?1",nativeQuery = true)
    List<Course> findMyCourse(String number);


    Course findByNameAndTeacherName(String name, String teacherName);
}
