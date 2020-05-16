package com.wxl.system.service;

import com.wxl.system.entity.Optional;
import com.wxl.system.entity.Sc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScService {

    //选课成功后将选课信息插入sc表
    void addSc(Sc sc);

    List<Sc> gradefindByPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("sno") String sno);

    Integer findTotal(String sno);

}
