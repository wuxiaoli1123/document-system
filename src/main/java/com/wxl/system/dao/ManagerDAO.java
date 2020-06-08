package com.wxl.system.dao;

import com.wxl.system.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerDAO  extends BaseDAO<Manager,String>{
    Manager findByMno(String mno);


    //根据学院返回教师（集合）
    List<Teacher> findTeacherByDept(String dept);

    //根据学院,年级返回学生（集合）
    List<Student> findStudentByDG(@Param("dept") String dept,@Param("grade") String grade);

    //注销账号
    void cancelAccounts(List<String> accounts);

    //发布课表插入到tc表
    void addScheduleTc(List<Tc> list);

    //发布课表插入到sc表
    void addScheduleSc(Tc tc);

    //检测是否该年级班级
    void checkGradeandClass(String classno,String grade);

    //更新用户行为表
    void updateAction(List<Action> list);

    //调出用户表
    List<Action> showAction(@Param("start") Integer start, @Param("rows") Integer rows,@Param("userID") String userID);

    //查询行为总数
    Integer findTotal(String userID);

    //查询各行为总数
    List<AcCount> showActionCount(List<String> actions);
}
