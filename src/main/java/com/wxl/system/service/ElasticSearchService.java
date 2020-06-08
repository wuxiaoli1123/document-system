package com.wxl.system.service;

import com.wxl.system.entity.*;

import java.util.List;

public interface ElasticSearchService {

    //返回所有学院
    List<EScollege> findESCollege();

    //返回所有课程
    List<EScourse> findESCourse();

    //返回所有专业
    List<ESmajor> findESMajor();

    //返回所有管理员
    List<ESmanager> findESManager();

    //返回所有选课信息
    List<ESoptional> findESOptional();

    //返回所有权限
    List<ESpermission> findESPermission();

    //返回所有角色
    List<ESrole> findESRole();

    //返回所有角色、权限
    List<ESrole_perm> findESRole_perm();

    //返回所有选课信息
    List<ESsc> findESSc();

    //返回所有通知
    List<ESsendinfo> findESSendinfo();

    //返回所有学生
    List<ESstudent> findESStudent();

    //返回所有授课信息
    List<EStc> findESTc();

    //返回所有老师
    List<ESteacher> findESTeacher();

    //返回所有用户
    List<ESuser> findESUser();
}
