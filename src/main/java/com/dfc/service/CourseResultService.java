package com.dfc.service;

import com.dfc.entity.CourseResult;
import org.springframework.transaction.annotation.Transactional;

public interface CourseResultService {


    @Transactional
    CourseResult joinCourse(CourseResult courseResult);

    @Transactional
    void  save(CourseResult courseResult);
}
