package com.dfc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.dfc.entity.Student;

import java.io.InputStream;
import java.util.List;

/**
 * @author: zsh
 * @Date:20:04 2018/5/5
 * @Description:
 */
public interface StudentService {

    @Transactional
    void save(Student student);

    @Transactional
    void importExcelInfo(InputStream in, MultipartFile file);


    /**
     * @author zsh
     * @date 2018/5/6 18:01
     * @param: [id]
     * @return: void
     * @Description: 通过id删除单个学生
     */
    @Transactional
    void deleteOne(Integer id);

    /**
     * @author zsh
     * @date 2018/5/6 18:08
     * @param: [ids]
     * @return: void
     * @Description: 通过ids删除多个学生
     */
    @Transactional
    void deleteMany(Integer[] ids);

    /**
     * @author zsh
     * @date 2018/5/6 18:43
     * @param: [student]
     * @return: void
     * @Description: 更新学生信息
     */
    @Transactional
    void update(Student student);

    /**
     * @author zsh
     * @date 2018/5/6 18:56
     * @param: [pageable]
     * @return: org.springframework.data.domain.Page<Student>
     * @Description: 分页查询所有学生，默认10/页
     */
    @Transactional
    Page<Student> findAll(Pageable pageable);

    /**
     * @author zsh
     * @date 2018/5/6 20:24
     * @param: [condition]
     * @return: org.springframework.data.domain.Page<Student>
     * @Description: 通过姓名，班级，学号模糊查询
     */
    @Transactional
    Page<Student> findAllByNameLikeOrClassesLikeOrNumberLike(String condition,Integer pn);

    /**
     * @author zsh
     * @date 2018/5/13 10:22
     * @param: [number]
     * @return: Student
     * @Description: 通过学号查询学生信息，查重用
     */
    @Transactional
    Student findByNumber(String number);

    @Transactional
    List<Student> find();

    @Transactional
    boolean  existsByOpenid(String openid);

    @Transactional
    Student findByOpenid(String openid);

    @Transactional
    Integer updateMessage(Student student);

}
