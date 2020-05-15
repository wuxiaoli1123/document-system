package com.wxl.system.service;

import com.wxl.system.entity.StuGrade;
import com.wxl.system.entity.TeaSchedule;
import com.wxl.system.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {

    //录入教师档案信息
    void insertTeacher(List<Teacher> teachers);

    Teacher findByTno(String tno);

    //登记学生成绩
    void updateGrade(List<StuGrade> stuGrades);

    //查询教师教授课程
    List<String> findCnameByTTG(String tno,String term,String grade);

    //查询教师教授课程的班级号
    List<String> findClassnoByTTGC(String tno,String term,String grade,String cname);

    //教师查询课表
    List<TeaSchedule> findScheduleT(String tno,String term);
}
