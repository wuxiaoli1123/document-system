package com.wxl;



import com.wxl.system.System01Application;
import com.wxl.system.entity.Student;
import com.wxl.system.entity.Teacher;
import com.wxl.system.service.StudentService;
import com.wxl.system.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = System01Application.class)
@RunWith(SpringRunner.class)
public class TestStudentService {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Test
    public void testFindBySno(){
        Student student = studentService.findBySno("2017110435");
        System.out.println(student);
    }


    @Test
    public void testupdate(){
        Student student = studentService.findBySno("2017110435");
        student.setDept("2018");
        student.setMajor("信息技术");
        student.setClassno("4");
        student.setPol("党员");
        student.setGs("xx");
        student.setBirth("19990914");
        student.setSex(0);
        student.setSname("苏祎晴");
        student.setGrade("2016");
        studentService.update(student);
    }

    @Test
    public void testtupdate(){
        Teacher teacher = teacherService.findByTno("12345678");
        teacher.setDept("xx");
        teacher.setEdurecord("xx");
        teacher.setPol("xx");
        teacher.setPosition("xx");
        teacher.setSex(1);
        teacher.setTel("xx");
        teacher.setTname("xx");
        teacher.setWorktime("xx");
        teacherService.update(teacher);
    }
}
