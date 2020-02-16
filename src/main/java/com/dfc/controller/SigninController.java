package com.dfc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.dfc.entity.Course;
import com.dfc.entity.Result;
import com.dfc.entity.Signin;
import com.dfc.entity.Student;
import com.dfc.service.CourseService;
import com.dfc.service.SigninService;
import com.dfc.service.StudentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: zsh
 * @Date:9:25 2018/5/10
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("signin")
public class SigninController {

    @Resource
    SigninService signinService;
    @Resource
    StudentService studentService;
    @Resource
    CourseService courseService;

    @RequestMapping(value = "/to",method = RequestMethod.GET)
    public ModelAndView to(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("native");
        return modelAndView;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Result add(String number,String data,String timer,HttpServletRequest request){
        Signin signin = new Signin();
        Result result = new Result();
        //200 签到成功
        //201 没有此学生，请检查学号是否输入正确
        //202 已经下课
        //203 该生非本次上课学生
        //204 该设备已经签到
        Student student = studentService.findByNumber(number);
        log.info(""+number);
        if (student == null){
            result.setCode(201);
            result.setMsg("没有此学生，请检查学号是否输入正确");
            return result;
        }else{
            //查得此课程。time[0]代表当前星期几，time[1]代表当前时:分
            String[] time = data.split(",");
            log.info("time"+time[0]+"---"+time[1]);
            Course course = courseService.findCourseByTime(time[1],time[0]);
            if (course == null){
                result.setCode(202);
                result.setMsg("已经下课");
                return result;
            }else {
                String classess = course.getClassess();
                String[] split1 = classess.split(",");
                for (int i = 0;i<split1.length;i++){
                    if (split1[i].equals(student.getClasses())){
                        break;
                    }
                    if (i == split1.length-1){
                        result.setCode(203);
                        result.setMsg("该生非本次上课学生");
                        return result;
                    }
                }
                //split[0]代表年-月-日
                String[] split = timer.split(" ");
                //remoteAddr代表IP地址
                String remoteAddr = request.getRemoteAddr();
                //拼接开始时间和结束时间
                String start_time = split[0]+" "+course.getStartTime();
                String end_time = split[0]+" "+course.getEndTime();
                Signin signin_check = signinService.findSignin(start_time, end_time, remoteAddr);
                log.info(""+signin_check);
                if (signin_check == null){
                    signin.setName(student.getName());
                    signin.setClasses(student.getClasses());
                    signin.setNumber(number);
                    signin.setTime(timer);
                    signin.setRoom(course.getRoom());
                    signin.setCourse_name(course.getName());
                    signin.setIp(remoteAddr);
                    signinService.save(signin);
                    //签到成功
                    result.setCode(200);
                    result.setMsg("签到成功");
                    return result;
                }else {
                    result.setCode(204);
                    result.setMsg("该设备已经签到");
                    return result;
                }
            }
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result deleteOne(Integer id){
        Result result = new Result();
        signinService.delete(id);
        result.setCode(200);
        result.setMsg("删除签到记录成功");
        return result;
    }

    @RequestMapping(value = "/deleteMany",method = RequestMethod.POST)
    public Result deleteMany(String ids){
        Result result = new Result();
        String[] id = ids.split(",");
        Integer[] ids_int = new Integer[id.length];

        for (int i = 0;i<id.length;i++){
            ids_int[i] = Integer.parseInt(id[i]);
        }
        signinService.deleteByIds(ids_int);
        result.setCode(200);
        result.setMsg("删除签到记录成功");
        return result;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(Signin signin){
        signinService.update(signin);
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ModelAndView findAll(@RequestParam(value = "pn", defaultValue = "0")int pn
            ,String condition){
        ModelAndView modelAndView = new ModelAndView();
        Pageable pageable = PageRequest.of(pn,30);
        modelAndView.setViewName("listSignin");
        if (condition == null){
            Page<Signin> page = signinService.findAll(pageable);
            modelAndView.addObject("page",page);
            modelAndView.addObject("condition",null);
        }else{
            Page<Signin> like = signinService.findAllByCondition(condition,pn);
            modelAndView.addObject("page",like);
            modelAndView.addObject("condition",condition);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/2",method = RequestMethod.GET)
    public ModelAndView toFind(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jumpto");
        return modelAndView;
    }

    @RequestMapping(value = "/findByTime",method = RequestMethod.GET)
    public ModelAndView findByTime(String data){
        ModelAndView modelAndView = new ModelAndView();
        log.info(data);
        String[] time = data.split(",");
        Course courseByTime = courseService.findCourseByTime(time[1],time[0]);
        log.info(""+courseByTime);
        modelAndView.setViewName("login");
        return modelAndView;

    }
}
