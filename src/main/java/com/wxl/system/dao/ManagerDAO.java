package com.wxl.system.dao;

import com.wxl.system.entity.Manager;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagerDAO {
    Manager findByMno(String mno);
}
