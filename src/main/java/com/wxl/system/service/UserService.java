package com.wxl.system.service;

import com.wxl.system.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User login(User user);

    //根据账号查询用户
    User findByAccount(String account);

    //根据用户账号，查询用户角色
    String findRoleByAccount(String account);

    //学生：根据用户账号查询用户姓名
    String findNameByAccountS(String account);

    //教师：根据用户账号查询用户姓名
    String findNameByAccountT(String account);

    //管理员：根据用户账号查询用户姓名
    String findNameByAccountM(String account);

    //修改密码
    void changePassword(User user);

    //插入用户表
    void insertUser(List<User> users);

    //根据账号返回用户role_code
    Set<String> findRCodeByAccount(String account);

    //根据账号返回用户perm_code
    Set<String> findPCodeByAccount(String account);

    //返回学校所有学院
    List<String> findAllCollege();

    //根据学院返回该学院的专业
    List<String> findMajorByCollege(String college);

}
