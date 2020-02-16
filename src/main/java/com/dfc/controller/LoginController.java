package com.dfc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.dfc.entity.Result;
import com.dfc.entity.User;
import com.dfc.service.UserService;
import com.dfc.utils.ToDd5;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: zsh
 * @Date:22:16 2018/5/4
 * @Description:
 */
@Slf4j
@RestController
public class LoginController {

    @Resource
    UserService userService;

    /**
     * @param
     * @return
     * @author zsh
     * @date 2018/5/5 19:33
     * @Description: 跳转到登陆页面
     */
    @GetMapping(value = "/")
    public ModelAndView index(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        log.info(remoteAddr);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * @author zsh
     * @date 2018/5/11 12:55
     * @param: [username, password, session]
     * @return: org.springframework.web.servlet.ModelAndView
     * @Description: 登陆方法
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(String username, String password, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByUserName(username);

        if (user != null){
            if (user.getPassWord().equals(password)){
                session.setAttribute("user",user);
                modelAndView.setViewName("index");
                /*                modelAndView.addObject("msg","账号密码正确");*/
            }else {
                modelAndView.setViewName("login");
                modelAndView.addObject("msg","密码错误");
            }
        }else {
            modelAndView.setViewName("login");
            modelAndView.addObject("msg","用户不存在");
        }
        return modelAndView;
    }

    @RequestMapping(value = "toIndex",method = RequestMethod.GET)
    public ModelAndView toIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * @author zsh
     * @date 2018/5/11 13:06
     * @param: [model, role] 
     * @return: java.lang.String 
     * @Description: 退出登录后,防倒退再次进入
     */
    @RequestMapping(value = "/logOff")
    public ModelAndView logOff() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("logOff");
        return modelAndView;
    }
    
    
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        session.removeAttribute("user");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public Result updatePassword(String oldpass, String again, String newpass,Integer id){
        log.info(oldpass+" "+newpass+" "+id);
        Result result = new Result();
        if (!newpass.equals(again)){
            result.setMsg("两次输入的密码不一致");
            return result;
        }else {
            String encode = ToDd5.encode(oldpass);
            User userById = userService.findUserById(id);
            if (!encode.equals(userById.getPassWord())){
                result.setMsg("原密码错误");
                return result;
            }else {
                userService.updatePassword(ToDd5.encode(newpass),id);
                result.setMsg("修改成功");
                return result;
            }
        }
    }
}
