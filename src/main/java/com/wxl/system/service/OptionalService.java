package com.wxl.system.service;

import com.wxl.system.entity.Optional;
import com.wxl.system.entity.Sc;

import java.util.List;

public interface OptionalService {

    //选课成功后将选课信息插入sc表
    void addSc(Sc sc);

    Optional findByCno(String cno);

    List<Optional> sfindByPage(Integer page, Integer rows);

    Integer findTotal();
    //修改省份信息
    void update(String cno,String sno);

    Integer addOptional(List<Optional> list);
}
