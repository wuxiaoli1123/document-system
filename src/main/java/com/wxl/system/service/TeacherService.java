package com.wxl.system.service;

import com.wxl.system.entity.StuGrade;
import com.wxl.system.entity.Teacher;

import java.util.List;

public interface TeacherService {

    //录入教师档案信息
    void insertTeacher(List<Teacher> teachers);

    Teacher findByTno(String tno);

    //登记学生成绩
    void updateGrade(List<StuGrade> stuGrades);


}
