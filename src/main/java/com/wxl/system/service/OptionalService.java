package com.wxl.system.service;

import com.wxl.system.entity.Optional;

import java.util.List;

public interface OptionalService {

    Optional findByCno(String cno);

    List<Optional> sfindByPage(Integer page, Integer rows);

    Integer findTotal();
    //修改省份信息
    void update(String cno,String sno);

    Integer addOptional(List<Optional> list);
}
