package com.wxl.system.service;

import com.wxl.system.dao.ManagerDAO;
import com.wxl.system.entity.*;
import org.apache.ibatis.annotations.Param;
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

    //修改管理员信息
    @Override
    public void update(Manager manager) {
        managerDAO.update(manager);
    }

    //发布课表插入到tc表
    @Override
    public void addScheduleTc(List<Tc> list) { managerDAO.addScheduleTc(list); }

    //发布课表插入到sc表
    @Override
    public void addScheduleSc(Tc tc) { managerDAO.addScheduleSc(tc); }

    //检测是否该年级班级
    @Override
    public void checkGradeandClass(String classno, String grade) {
        managerDAO.checkGradeandClass(classno,grade);
    }

    //更新用户行为表
    @Override
    public void updateAction(List<Action> list) {managerDAO.updateAction(list); }

    //调出用户表
    @Override
    public List<Action> showAction(Integer page, Integer rows, @Param("userID") String userID) {
        int start = (page - 1)*rows;
        return managerDAO.showAction(start,rows,userID);
    }

    //查询行为总数
    @Override
    public Integer findTotal(String userID) {
        return managerDAO.findTotal(userID);
    }


    //查询各行为总数
    @Override
    public List<AcCount> showActionCount(List<String> actions) {
        return managerDAO.showActionCount(actions);
    }

    //注销账号
    @Override
    public void cancelAccounts(List<String> accounts){
        managerDAO.cancelAccounts(accounts);
    }

}
