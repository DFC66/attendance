package com.dfc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import com.dfc.entity.Signin;

/**
 * @author: zsh
 * @Date:21:33 2018/5/10
 * @Description:
 */
public interface SigninService {

   
    /**
     * @author zsh
     * @date 2018/5/10 9:12
     * @param: [signin] 
     * @return: void 
     * @Description: 
     */
    @Transactional
    void save(Signin signin);

    /**
     * @author zsh
     * @date 2018/5/10 9:12
     * @param: [id] 
     * @return: void 
     * @Description: 
     */
    @Transactional
    void delete(Integer id);
    
    /**
     * @author zsh
     * @date 2018/5/10 9:12
     * @param: [ids] 
     * @return: void 
     * @Description: 
     */
    @Transactional
    void deleteByIds(Integer[] ids);
    
    /**
     * @author zsh
     * @date 2018/5/10 9:13
     * @param: [signin] 
     * @return: void 
     * @Description: 
     */
    @Transactional
    void update(Signin signin);

    /**
     *
     * @param condition
     * @return
     */
    @Transactional
    Page<Signin> findAllByCondition(String condition,Integer pn);
    
    /**
     * @author zsh
     * @date 2018/5/10 9:21
     * @param: [pageable] 
     * @return: org.springframework.data.domain.Page<Signin>
     * @Description: 
     */
    Page<Signin> findAll(Pageable pageable);

    @Transactional
    Signin findSignin(String start_time,String end_time,String ip);
}
