package com.wxl.system.service;

import com.wxl.system.entity.IsChoose;
import com.wxl.system.entity.Optional;

import java.util.List;

public interface OptionalService {

    //学生选课功能相关
    //按cno查找单个课程
    Optional findByCno(String cno);

    //分页呈现选课
    List<Optional> sfindByPage(Integer page, Integer rows);

    //查询课程总数
    Integer findTotal();

    //学生选课
    void updateNumber(String cno,String sno);

    //判断该学生是否已选课
    IsChoose isChoose(String sno);

    //学生更改选课
    void StuChangeCourse(String cno,String sno);

    //发布选课相关
    //批量发布选课
    Integer addOptional(List<Optional> list);

    //管理员选择需要添加的课程
    Optional findTcByCno(String cno);
}
