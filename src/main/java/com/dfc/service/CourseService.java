package com.dfc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import com.dfc.entity.Course;

import java.util.List;

/**
 * @author: zsh
 * @Date:21:33 2018/5/9
 * @Description:
 */
public interface CourseService {

    @Transactional
    void save(Course course);

    @Transactional
    void delete(Integer id);

    @Transactional
    void deleteMany(Integer[] ids);

    @Transactional
    void update(Course course);

    @Transactional
    Page<Course> findAll(Pageable pageable);

    @Transactional
    Page<Course> findByCondition(String condition, Integer pn);

    @Transactional
    Course findCourseByTime(String date,String today);

    @Transactional
    Course findByCourseCode(Integer courseCode);

    @Transactional
    Course findByNameAndTeacherName(Course course);

    @Transactional
    List<Course> findMyCourse(String number);


}
