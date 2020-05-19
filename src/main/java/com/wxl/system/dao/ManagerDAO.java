package com.wxl.system.dao;

import com.wxl.system.entity.Manager;
import com.wxl.system.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagerDAO extends BaseDAO<Manager,String>{
    Manager findByMno(String mno);
}
