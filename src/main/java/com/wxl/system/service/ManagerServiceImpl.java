package com.wxl.system.service;


import com.wxl.system.dao.ManagerDAO;
import com.wxl.system.entity.Manager;
import com.wxl.system.entity.Student;
import com.wxl.system.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDAO managerDAO;

    @Override
    public Manager findByMno(String mno){return managerDAO.findByMno(mno);}

    //根据学院返回教师
    @Override
    public List<Teacher> findTeacherByDept(String dept){
          return managerDAO.findTeacherByDept(dept);
    }

    //根据学院、年级返回学生信息
    @Override
    public List<Student> findStudentByDG(String dept, String grade){
        return managerDAO.findStudentByDG(dept, grade);
    }

   //注销账号
    @Override
   public void cancelAccounts(List<String> accounts){
        managerDAO.cancelAccounts(accounts);
    }

}
