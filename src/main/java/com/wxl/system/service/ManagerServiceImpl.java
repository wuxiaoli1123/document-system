package com.wxl.system.service;


import com.wxl.system.dao.ManagerDAO;
import com.wxl.system.entity.Manager;
import com.wxl.system.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDAO managerDAO;

    @Override
    public Manager findByMno(String mno){return managerDAO.findByMno(mno);}

    //修改教师信息
    @Override
    public void update(Manager manager) {
        managerDAO.update(manager);
    }

}
