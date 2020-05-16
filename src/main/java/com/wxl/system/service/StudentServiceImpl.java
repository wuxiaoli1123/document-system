package com.wxl.system.service;

import com.wxl.system.dao.StudentDAO;
import com.wxl.system.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void insertStudent(List<Student> students){
        studentDAO.insertStudent(students);
    }

    @Override
    public Student findBySno(String sno){return studentDAO.findBySno(sno);}

    @Override
    public void update(Student student) {
        studentDAO.update(student);
    }


    @Override
    public List<Student_abbr> findByPage(Integer page, Integer rows, String cname, String grade, String classno){
        int start = (page - 1)*rows;
        return studentDAO.findByPage(start,rows,cname,grade,classno);
    }

    @Override
    public Integer findTotals(String cname,String grade,String classno) {
        return studentDAO.findTotals(cname,grade,classno);
    }


    @Override
    public List<StuSchedule> findScheduleS(List<StuClassData> stuClassData){
        return studentDAO.findScheduleS(stuClassData);

    }

    @Override
    public List<StuClassData> findStuClassData(String sno, String term){
           return studentDAO.findStuClassData(sno, term);
    }

}
