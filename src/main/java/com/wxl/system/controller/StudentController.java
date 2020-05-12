package com.wxl.system.controller;

import com.wxl.system.entity.Result;
import com.wxl.system.entity.Student;
import com.wxl.system.entity.Teacher;
import com.wxl.system.entity.User;
import com.wxl.system.service.StudentService;
import com.wxl.system.service.TeacherService;
import com.wxl.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("student")
@CrossOrigin //允许跨域
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    //录入学生档案信息
    @PostMapping("insertStudent")
    public Result insertStudent(@RequestBody List<Student> students){
        Result result = new Result();
        try {
            studentService.insertStudent(students);
            result.setMsg("成功录入学生档案信息！");

            if(result.getState()){

                List<User> users = new ArrayList<>();
                for(int i = 0;i<students.size();i++){

                    User user = new User();
                    user.setAccount(students.get(i).getSno());
                    user.setPassword(students.get(i).getSno());
                    user.setRole("1");

                    users.add(user);
                }

                log.info("users "+users);
                userService.insertUser(users);

            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("未成功录入学生档案信息！");
        }
        return result;
    }


    @GetMapping("findBySno")
    public Student findBySno(String sno){
        return studentService.findBySno(sno);
    }

    @PostMapping("update")
    public Result update(@RequestBody Student student) {
        Result result = new Result();
        try {
            studentService.update(student);
            result.setMsg("修改信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }




}
