package com.wxl.system.dao;

import com.wxl.system.entity.Sc;


public interface ScDAO {

    //选课成功后将选课信息插入sc表
    void addSc(Sc sc);
}
