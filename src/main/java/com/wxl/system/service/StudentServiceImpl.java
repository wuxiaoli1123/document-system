package com.wxl.system.service;

import com.wxl.system.dao.StudentDAO;
import com.wxl.system.entity.*;
import org.apache.ibatis.annotations.Param;
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

    //录入学生档案信息
    @Transactional(propagation = Propagation.SUPPORTS)
    public void insertStudent(List<Student> students){
        studentDAO.insertStudent(students);
    }

    //按学号查找学生
    @Override
    public Student findBySno(String sno){return studentDAO.findBySno(sno);}

    //修改学生个人信息
    @Override
    public void update(Student student) {
        studentDAO.update(student);
    }

    //参数1：当前页； 参数2：每页显示记录条数---student
    @Override
    public List<Student_abbr> findByPage(Integer page, Integer rows, String cname, String grade, String classno){
        int start = (page - 1)*rows;
        return studentDAO.findByPage(start,rows,cname,grade,classno);
    }

    //查询总条数----student
    @Override
    public Integer findTotals(String cname,String grade,String classno) {
        return studentDAO.findTotals(cname,grade,classno);
    }


    //学生查询课表
    @Override
    public List<StuSchedule> findScheduleS(List<StuClassData> stuClassData){
        return studentDAO.findScheduleS(stuClassData);

    }

    //根据学生学号、学期查询本学期所选修课程
    @Override
    public List<StuClassData> findStuClassData(String sno, String term){
           return studentDAO.findStuClassData(sno, term);
    }

    //学生查看成绩相关
    //分页查看成绩
    @Override
    public  List<StuCheckGrade> gradefindByPage(Integer page, Integer rows , @Param("sno") String sno) {
        int start = (page-1)*rows;
        return studentDAO.gradefindByPage(start,rows,sno);
    }

    //查询课程总数
    @Override
    public Integer findTotal(String sno) {
        return studentDAO.findTotal(sno);
    }
}
