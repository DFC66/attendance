package com.dfc.service.impl;

import com.dfc.dao.CourseResultDao;
import com.dfc.entity.CourseResult;
import com.dfc.service.CourseResultService;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseResultServiceImpl implements CourseResultService {


    @Autowired
   private   CourseResultDao courseResultDao;


    @Override
    public CourseResult joinCourse(CourseResult courseResult) {
        return courseResultDao.findByCourseCodeAndNumber(courseResult.getCourseCode(),courseResult.getNumber());
    }

    @Override
    public void save(CourseResult courseResult) {
        courseResultDao.save(courseResult);
    }

    @Override
    public void updateIsDeletedStatus(CourseResult courseResult) {
           courseResultDao.updateIsDeletedStatus(courseResult.getNumber(),courseResult.getCourseCode());
    }

    @Override
    public void exitCourse(String number, Integer courseCode) {
        courseResultDao.exitCourse(number,courseCode);
    }
}
