package com.wxl.system.service;

import com.wxl.system.entity.Notice;
import com.wxl.system.entity.Student;
import com.wxl.system.entity.Student_abbr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {

    //录入学生档案信息
    void insertStudent(List<Student> students);

    //按学号查找学生
    Student findBySno(String sno);

    //修改学生个人信息
    void update(Student student);


    //参数1：当前页； 参数2：每页显示记录条数---student
    List<Student_abbr> findByPage(Integer page, Integer rows, String cname, String grade, String classno);

    //查询总条数----student
    Integer findTotals(String cname,String grade,String classno);




}
