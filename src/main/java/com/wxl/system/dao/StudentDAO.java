package com.wxl.system.dao;

import com.wxl.system.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDAO {
    //插入学生档案信息
    void insertStudent(List<Student> students);


    Student findBySno(String sno);

    void update(Student student);
}

