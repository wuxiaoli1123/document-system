package com.wxl.system.service;

import com.wxl.system.entity.Student;

import java.util.List;

public interface StudentService {

    //录入学生档案信息
    void insertStudent(List<Student> students);

}
