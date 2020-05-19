package com.wxl.system.controller;



import com.wxl.system.entity.*;
import com.wxl.system.service.ManagerService;
import com.wxl.system.service.StudentService;
import com.wxl.system.service.TeacherService;
import com.wxl.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("manager")
@Slf4j
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;
//    查看管理员个人信息
    @GetMapping("findByMno")
    public Manager findByMno(String mno){
        log.info("admin:"+mno);
        return managerService.findByMno(mno);
    }


    //根据学院返回教师信息
    @GetMapping("findTeacherByDept")
    public List<Teacher> findTeacherByDept(String dept){
        return managerService.findTeacherByDept(dept);
    }

    //根据职工号返回教师信息
    @GetMapping("findTeacherByTno")
    public Teacher findTeacherByTno(String tno){
        return teacherService.findByTno(tno);
    }


    //根据学院，年级返回学生信息
    @GetMapping("findStudentByDG")
    public List<Student> findStudentByDG(String dept,String grade){
        return managerService.findStudentByDG(dept, grade);
    }

    //根据学号返回学生信息
    @GetMapping("findstudentBySno")
    public Student findstudentBySno(String sno){
        return studentService.findBySno(sno);
    }

    //注销学生、教师账号
    @PostMapping("cancelAccount")
    public Result cancelAccounts(@RequestBody List<String> accounts){
        Result result = new Result();
        try{
            managerService.cancelAccounts(accounts);
            result.setState(true).setMsg("成功注销账号！");
        }catch (Exception e){
            result.setState(false).setMsg("未成功注销账号！");
        }
        return result;
    }

    //修改管理员个人信息
    @PostMapping("update")
    public Result update(@RequestBody Manager manager) {
        Result result = new Result();
        try {
            managerService.update(manager);
            log.info(manager.getMno());
            result.setMsg("修改信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

    //发布课表
    @PostMapping("addSchedule")
    public Result addSchedule(@RequestBody List<Tc> list){
        Result result = new Result();
        try {
            managerService.addScheduleTc(list);
            result.setMsg("发布课表成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

}
