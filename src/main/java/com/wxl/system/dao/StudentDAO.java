package com.wxl.system.dao;

import com.wxl.system.entity.Notice;
import com.wxl.system.entity.Student;
import com.wxl.system.entity.Student_abbr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentDAO extends BaseDAO<Student_abbr,String> {
    //插入学生档案信息
    void insertStudent(List<Student> students);
    //按学号查找学生
    Student findBySno(String sno);
    //修改学生个人信息
    void update(Student student);

}

