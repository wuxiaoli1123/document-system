package com.wxl.system.service;


import com.wxl.system.dao.TeacherDAO;
import com.wxl.system.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDAO teacherDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void insertTeacher(List<Teacher> teachers){
        teacherDAO.insertTeacher(teachers);
    }

}
