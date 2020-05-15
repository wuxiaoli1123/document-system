package com.wxl.system.dao;

import com.wxl.system.entity.Optional;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionalDAO extends BaseDAO<Optional,String>{

    //    按cno查找单个课程
    Optional findByCno(String cno);

    //  学生选课
    void update(String cno,String sno);

    //    批量发布选课
    Integer addOptional(List<Optional> list);

    //   分页呈现选课
    List<Optional> sfindByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    //   查询课程总数
    Integer findTotal();
}
