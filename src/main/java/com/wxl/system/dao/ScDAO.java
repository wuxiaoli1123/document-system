package com.wxl.system.dao;

import com.wxl.system.entity.Sc;
import com.wxl.system.entity.StuCheckGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ScDAO {

    //学生选课相关
    //选课成功后将选课信息插入sc表
    void addSc(Sc sc);

    //学生查看成绩相关
    //分页
    List<StuCheckGrade> gradefindByPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("sno") String sno);

    //查询课程总数
    Integer findTotal(String sno);
}
