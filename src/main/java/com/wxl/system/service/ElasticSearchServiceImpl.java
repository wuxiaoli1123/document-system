package com.wxl.system.service;

import com.wxl.system.dao.ElasticSearchDAO;
import com.wxl.system.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ElasticSearchServiceImpl implements ElasticSearchService{

    @Autowired
    private ElasticSearchDAO elasticSearchDAO;

    //返回所有学院
    @Override
    public List<EScollege> findESCollege(){
           return elasticSearchDAO.findESCollege();
    }

    //返回所有课程
    @Override
    public List<EScourse> findESCourse(){
          return  elasticSearchDAO.findESCourse();
    }

    //返回所有专业
    @Override
    public List<ESmajor> findESMajor(){
        return elasticSearchDAO.findESMajor();
    }

    //返回所有管理员
    @Override
    public List<ESmanager> findESManager(){
        return  elasticSearchDAO.findESManager();
    }

    //返回所有选课信息
    @Override
    public List<ESoptional> findESOptional(){
        return elasticSearchDAO.findESOptional();
    }

    //返回所有权限
    @Override
    public List<ESpermission> findESPermission(){
         return  elasticSearchDAO.findESPermission();
    }


    //返回所有角色
    @Override
    public List<ESrole> findESRole(){
         return elasticSearchDAO.findESRole();
    }

    //返回所有角色、权限
    @Override
    public List<ESrole_perm> findESRole_perm(){
         return elasticSearchDAO.findESRole_perm();
    }



    //返回所有选课信息
    @Override
    public List<ESsc> findESSc(){
         return elasticSearchDAO.findESSc();
    }

    //返回所有通知
    @Override
    public List<ESsendinfo> findESSendinfo(){
         return  elasticSearchDAO.findESSendinfo();
    }


    //返回所有学生
    @Override
    public List<ESstudent> findESStudent(){
        return elasticSearchDAO.findESStudent();
    }


    //返回所有授课信息
    @Override
    public List<EStc> findESTc(){
        return elasticSearchDAO.findESTc();
    }


    //返回所有老师
    @Override
    public List<ESteacher> findESTeacher(){
        return elasticSearchDAO.findESTeacher();
    }


    //返回所有用户
    @Override
    public List<ESuser> findESUser(){
          return elasticSearchDAO.findESUser();
    }

}
