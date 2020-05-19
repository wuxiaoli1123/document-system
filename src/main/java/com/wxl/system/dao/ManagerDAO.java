package com.wxl.system.dao;

import com.wxl.system.entity.Manager;
import com.wxl.system.entity.Student;
import com.wxl.system.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerDAO {
    Manager findByMno(String mno);


    //根据学院返回教师（集合）
    List<Teacher> findTeacherByDept(String dept);

    //根据学院,年级返回学生（集合）
    List<Student> findStudentByDG(@Param("dept") String dept,@Param("grade") String grade);

    //注销账号
    void cancelAccounts(List<String> accounts);

}
