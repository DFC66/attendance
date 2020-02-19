package com.dfc.controller;

import com.dfc.api.StorageApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.dfc.entity.Course;
import com.dfc.entity.Result;
import com.dfc.service.CourseService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author: zsh
 * @Date:22:02 2018/5/9
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/course")
public class CourseController {

    @Resource
    CourseService courseService;

    @Autowired
    private StorageApi storageApi;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(Course course) {
        Result result = new Result<Course>();
        courseService.save(course);
        result.setCode(200);
        result.setMsg("添加课程成功!");
        return result;
    }


    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public Result addCourse(@RequestBody Course course) throws Exception {
//        String contentType = request.getContentType();
//        storageApi.uploadByServletRequest(request,"courseImg");
        Result result = new Result<Course>();

        Course existCourse = courseService.findByNameAndTeacherName(course);
        if (existCourse != null) {
            result.setCode(000);
            result.setMsg("课程已存在,无法添加");
        } else {
            Integer courseCode = getCourseCode(courseService);
            course.setCourseCode(courseCode);
            courseService.save(course);
            result.setCode(200);
            result.setMsg("添加课程成功!");
        }


        return result;
    }

//    @RequestMapping(value = "/judgeCourseExists",method = RequestMethod.POST)
//    public Result judgeCourseExists(Course course){
//        Result result = new Result<Course>();
//        courseService.save(course);
//        result.setCode(200);
//        result.setMsg("添加课程成功!");
//        return result;
//    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result delete(Integer id) {
        Result result = new Result();
        courseService.delete(id);
        result.setCode(200);
        result.setMsg("删除课程成功!");
        return result;
    }

    @RequestMapping(value = "/deleteMany", method = RequestMethod.POST)
    public Result deleteMany(String ids) {
        Result result = new Result();
        String[] id = ids.split(",");
        Integer[] ids_int = new Integer[id.length];

        for (int i = 0; i < id.length; i++) {
            ids_int[i] = Integer.parseInt(id[i]);
        }
        courseService.deleteMany(ids_int);
        result.setCode(200);
        result.setMsg("删除课程成功!");
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(Course course) {
        Result result = new Result();
        courseService.update(course);
        result.setCode(200);
        result.setMsg("修改课程成功!");
        return result;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ModelAndView findAll(@RequestParam(value = "pn", defaultValue = "0") int pn
            , String condition) {
        ModelAndView modelAndView = new ModelAndView();
        Pageable pageable = PageRequest.of(pn, 10);
        modelAndView.setViewName("listCourse");
        if (condition == null) {
            Page<Course> page = courseService.findAll(pageable);
            modelAndView.addObject("page", page);
            modelAndView.addObject("condition", null);
        } else {
            Page<Course> like = courseService.findByCondition(condition, pn);
            modelAndView.addObject("page", like);
            modelAndView.addObject("condition", condition);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/findByTime", method = RequestMethod.GET)
    public Course findByTime(String data) {
        String[] time = data.split(",");
        Course courseByTime = courseService.findCourseByTime(time[1], time[0]);
        return courseByTime;
    }


    private Integer getCourseCode(CourseService courseService) {
        Integer num = 0;

        while (true) {
            num = (int) ((Math.random() * 9 + 1) * 1000);
            Course course = courseService.findByCourseCode(num);
            if (course == null) {
                break;
            }

        }
        return num;
    }
}