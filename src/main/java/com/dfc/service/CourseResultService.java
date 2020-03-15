package com.dfc.service;

import com.dfc.entity.CourseResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseResultService {


    @Transactional
    CourseResult joinCourse(CourseResult courseResult);

    @Transactional
    void  save(CourseResult courseResult);

    @Transactional
    void updateIsDeletedStatus(CourseResult courseResult);


    @Transactional
     void exitCourse(String number,Integer courseCode);

    @Transactional
    List<CourseResult> getCourseStudent(Integer courseCode);
}
