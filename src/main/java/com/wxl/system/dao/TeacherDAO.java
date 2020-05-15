package com.wxl.system.dao;

import com.wxl.system.entity.Notice;
import com.wxl.system.entity.StuGrade;
import com.wxl.system.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import com.wxl.system.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherDAO {
    void insertTeacher(List<Teacher> teacher);

    //按教师号查找教师
    Teacher findByTno(String tno);

    //登记学生成绩
    void updateGrade(List<StuGrade> stuGrades);

    List<String> findCnameByTTG(@Param("tno") String tno,@Param("term") String term,@Param("grade") String grade);

    List<String> findClassnoByTTGC(@Param("tno") String tno,@Param("term") String term,@Param("grade") String grade,@Param("cname") String cname);



}
