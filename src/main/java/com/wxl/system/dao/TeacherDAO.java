package com.wxl.system.dao;

import org.apache.ibatis.annotations.Mapper;
import com.wxl.system.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherDAO {
    void insertTeacher(List<Teacher> teacher);

    Teacher findByTno(String tno);

    //登记学生成绩
    void updateGrade(@Param("sno") String sno,@Param("cname") String cname,@Param("grade") double grade);
}
