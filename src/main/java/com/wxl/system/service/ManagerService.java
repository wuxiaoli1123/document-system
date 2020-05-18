package com.wxl.system.service;

import com.wxl.system.entity.Manager;
import com.wxl.system.entity.Teacher;

public interface ManagerService {
    Manager findByMno(String mno);

    //修改管理员个人信息
    void update(Manager manager);
}