package com.wxl.system.service;


import com.wxl.system.dao.TeacherDAO;
import com.wxl.system.entity.StuGrade;
import com.wxl.system.entity.TeaSchedule;
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

    @Override
    public Teacher findByTno(String tno){return teacherDAO.findByTno(tno);}


    //登记学生成绩
    @Override
    public void updateGrade(List<StuGrade> stuGrades){
        teacherDAO.updateGrade(stuGrades);
    }

   @Override
   public List<String> findCnameByTTG(String tno,String term,String grade){
        return teacherDAO.findCnameByTTG(tno, term, grade);
   }

    @Override
    public List<String> findClassnoByTTGC(String tno,String term,String grade,String cname){
        return teacherDAO.findClassnoByTTGC(tno, term, grade, cname);
    }

    @Override
    public List<TeaSchedule> findScheduleT(String tno, String term){
        return teacherDAO.findScheduleT(tno, term);
    }
}
