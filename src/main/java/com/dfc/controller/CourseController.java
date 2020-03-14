package com.dfc.controller;

import com.dfc.api.StorageApi;
import com.dfc.entity.CourseResult;
import com.dfc.service.CourseResultService;
import com.dfc.util.HttpsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.dfc.entity.Course;
import com.dfc.entity.Result;
import com.dfc.service.CourseService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private CourseResultService courseResultService;

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
    public Result addCourse(@RequestBody Course course,HttpServletRequest request) throws Exception {
        String contentType = request.getContentType();
//        storageApi.uploadByServletRequest(request,"courseImg");
        Result result = new Result<Course>();

        Course existCourse = courseService.findByNameAndTeacherName(course);
        if (existCourse != null) {
            result.setCode(000);
            result.setMsg("课程已存在,无法添加");
        } else {
            Integer courseCode = getCourseCode(courseService);
            course.setCourseCode(courseCode);
            course.setSigninStatus(1);
            courseService.save(course);
            result.setCode(200);
            result.setMsg("添加课程成功!");
        }


        return result;
    }





     @Transactional
    @RequestMapping(value = "/joinCourse", method = RequestMethod.POST)
    public Result JoinCourseJudge(String number, Integer courseCode) throws Exception {
        Result result = new Result<CourseResult>();
        Course byCourseCode = courseService.findByCourseCode(courseCode);
        if (byCourseCode == null) {
            result.setCode(000);
            result.setMsg("查无此课程号的课程");
        } else {
            CourseResult courseResult = new CourseResult();
            courseResult.setCourseCode(courseCode);
            courseResult.setNumber(number);
            CourseResult joinCourse = courseResultService.joinCourse(courseResult);
            if (joinCourse != null&&joinCourse.getIsDeleted()==1) {
                result.setCode(300);
                result.setMsg("你已加入此课程");
            } else {
                result.setCode(200);
                result.setMsg("开始添加人脸信息");
            }

        }

        return result;
    }

    @Transactional
    @RequestMapping(value = "/addFace", method = RequestMethod.POST)
    public Result addFaceToDB(String number, Integer courseCode)  {
        Result result = new Result<CourseResult>();
          CourseResult courseResult = new CourseResult();
          courseResult.setNumber(number);
          courseResult.setCourseCode(courseCode);
        CourseResult isJoinCourse = courseResultService.joinCourse(courseResult);
        if (isJoinCourse!=null){
             courseResultService.updateIsDeletedStatus(courseResult);
            result.setCode(200);
            result.setMsg("课程添加成功");
            result.setMessage(courseResult);
        }else {
            courseResult.setIsDeleted(1);
            courseResultService.save(courseResult);
            result.setCode(200);
            result.setMsg("课程添加成功");
            result.setMessage(courseResult);

        }

        return result;
    }


    @Transactional
    @RequestMapping(value = "/getMyCourse", method = RequestMethod.POST)
    public Result getMyCourse(String number) throws Exception {
        Result result = new Result<>();
        List<Course> myCourse = courseService.findMyCourse(number);
        if (myCourse.size()>0){
            result.setCode(200);
            result.setMsg("找到自己的课程了");
            result.setMessage(myCourse);
        }else{
            result.setCode(0);
            result.setMsg("还没有加入的课程");
            result.setMessage(myCourse);
        }
        return result;
    }


    @Transactional
    @RequestMapping(value = "/getAllCourse", method = RequestMethod.POST)
    public Result getAllCourse(){
        Result result = new Result<>();
        List<Course> allCourse = courseService.findAll();
        if (allCourse.size()>0){
            result.setCode(200);
            result.setMsg("找到所有的课程了");
            result.setMessage(allCourse);
        }else{
            result.setCode(404);
            result.setMsg("表显示错误");
        }
        return result;
    }



    @Transactional
    @RequestMapping(value = "/getCourseDetail", method = RequestMethod.POST)
    public Result getCourseDetail(Integer courseCode) throws Exception {
        Result result = new Result<>();
        Course course = courseService.findByCourseCode(courseCode);
        if (course!=null){
            result.setCode(200);
            result.setMsg("找到对应的课程了");
            result.setMessage(course);
        }else{
            result.setCode(0);
            result.setMsg("没有该课程");
            result.setMessage(null);
        }
        return result;
    }




    @Transactional
    @RequestMapping(value = "/exitCourse", method = RequestMethod.POST)
    public Result exitCourse(String number,Integer courseCode)  {
        Result result = new Result<CourseResult>();
        courseResultService.exitCourse(number,courseCode);
        CourseResult courseResult = new CourseResult();
        courseResult.setNumber(number);
        courseResult.setCourseCode(courseCode);
        CourseResult isJoinCourse = courseResultService.joinCourse(courseResult);
        if (isJoinCourse.getIsDeleted()==0){
            result.setCode(200);
            result.setMsg("课程退出成功");
            result.setMessage(isJoinCourse);
        }else{
            result.setCode(204);
            result.setMsg("课程退出失败");
            result.setMessage(isJoinCourse);
        }

        return  result;
    }


    @Transactional
    @RequestMapping(value = "/updateSignStatus", method = RequestMethod.POST)
    public Result updateSignStatus(Integer status,Integer courseCode) {
        Result result = new Result<Course>();
        Integer isSuccess = null;
        if (status==1){
            isSuccess = courseService.updateSignInStatus(0, courseCode);
        }else if (status==0){
            isSuccess = courseService.updateSignInStatus(1, courseCode);
        }
        result.setCode(2000000);
        result.setMsg(String.valueOf(isSuccess));
        Course course = courseService.findByCourseCode(courseCode);
        if (course!=null){
            result.setMessage(course);
        }
        return  result;
    }





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
