package com.wxl.system.dao;

import org.apache.ibatis.annotations.Mapper;
import com.wxl.system.entity.Teacher;

import java.util.List;

@Mapper
public interface TeacherDAO {
    void insertTeacher(List<Teacher> teacher);

    Teacher findByTno(String tno);
}
