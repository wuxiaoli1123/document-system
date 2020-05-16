package com.wxl.system.dao;

import com.wxl.system.entity.Sc;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ScDAO {

    //选课成功后将选课信息插入sc表
    void addSc(Sc sc);

    Sc checkGrade(String sno);

    List<Sc> gradefindByPage(@Param("start") Integer start, @Param("rows") Integer rows,@Param("sno") String sno);

    //   查询课程总数
    Integer findTotal(String sno);
}
