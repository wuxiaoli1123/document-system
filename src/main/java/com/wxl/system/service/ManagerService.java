package com.wxl.system.service;

import com.wxl.system.entity.Manager;
import com.wxl.system.entity.Student;
import com.wxl.system.entity.Teacher;

import java.util.List;

public interface ManagerService {
    Manager findByMno(String mno);


    //根据学院返回教师（集合）
    List<Teacher> findTeacherByDept(String dept);

    //根据学院、年级返回学生（集合）
    List<Student> findStudentByDG(String dept, String grade);

    //注销账号
    void cancelAccounts(List<String> accounts);

    //修改才个人信息
    void update(Manager manager);

}