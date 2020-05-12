package com.wxl.system.service;

import com.wxl.system.entity.Manager;

public interface ManagerService {
    Manager findByMno(String mno);
}