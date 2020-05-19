package com.wxl.system.dao;

import com.wxl.system.entity.*;
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

    //学生查看课表
    List<StuSchedule> findScheduleS(List<StuClassData> stuClassData);

    //根据学号、学期查询学生本学期所选修课程
    List<StuClassData> findStuClassData(@Param("sno") String sno, @Param("term") String term);

    //学生查看成绩相关
    //分页查看成绩
    List<StuCheckGrade> gradefindByPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("sno") String sno);

    //查询课程总数
    Integer findTotal(String sno);
}

