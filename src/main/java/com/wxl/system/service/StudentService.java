package com.wxl.system.service;

import com.wxl.system.entity.Student;

import java.util.List;

public interface StudentService {

    //录入学生档案信息
    void insertStudent(List<Student> students);

    Student findBySno(String sno);

    void update(Student student);


    //参数1：当前页； 参数2：每页显示记录条数
    List<Student> findByPage(Integer page,Integer rows);

    //查询总条数
    Integer findTotals();



}
