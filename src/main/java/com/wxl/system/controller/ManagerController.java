package com.wxl.system.controller;

import com.wxl.system.entity.*;
import com.wxl.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private OptionalService optionalService;

    @Autowired
    private NoticeService noticeService;



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
    public Result cancelAccounts(@RequestBody String[] l_accounts){
        List<String> accounts = java.util.Arrays.asList(l_accounts);
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
            managerService.addScheduleSc(list);
            result.setMsg("发布课表成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

    //发布选课功能相关
    //    批量发布选课
    @PostMapping("addOptional")
    public Result addOptional(@RequestBody List<Optional> list) {
        Result result = new Result();
        try {
            optionalService.addOptional(list);
            result.setMsg("添加课程成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("添加课程失败!!!");
        }
        return result;
    }

    // 管理员选择需要添加的课程
    @GetMapping("findTcByCno")
    public Optional findTcByCno(String cno) {
        return optionalService.findTcByCno(cno);
    }


    @GetMapping("MfindByPage")
    public Map<String,Object> findAttention(Integer page, Integer rows, Integer role_id){
        page = page == null ? 1 : page;

        //前端页面应该是6.这里的“2”，仅用于后端测试
        rows = rows == null ? 3 : rows;

        HashMap<String,Object> map = new HashMap<>();

        //分页处理
        List<Notice> notices = noticeService.findByPage_NoticeTS(page,rows,role_id);


        //计算总页数
        Integer totals = noticeService.findTotals_NoticeTS(role_id);
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;

        map.put("notices",notices);
        map.put("totals",totals);
        map.put("totalPage",totalPage);

        return map;
    }

    //管理员发布通知相关          --syq
    @PostMapping("addNotice")
    public Result save(@RequestBody Notice notice) {
        Result result = new Result();
        try {
            noticeService.addNotice(notice);
            result.setMsg("发布公告成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("发布公告失败!!!");
        }
        return result;
    }

    //管理员删除通知相关          --syq
    @PostMapping("delNotice")
    public Result delNotice(@RequestBody List<Integer> snum){
        Result result = new Result();
        try {
            noticeService.delNotice(snum);
            result.setMsg("删除通知成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("删除通知失败!!!");
        }
        return result;
    }



    /**
     * 批量录入教师档案信息，并自动插入用户表
     * by 吴小莉
     */
    @PostMapping("insertTeacher")
    public Result insertTeacher(@RequestBody List<Teacher> teachers){
        Result result = new Result();
        try {
            teacherService.insertTeacher(teachers);
            result.setMsg("成功录入教师档案信息！");

            log.info("值 "+teachers.get(0).getTno());

            if(result.getState()){

                List<User> users = new ArrayList<>();
                for(int i = 0;i<teachers.size();i++){

                    User user = new User();
                    user.setAccount(teachers.get(i).getTno());
                    user.setPassword(teachers.get(i).getTno());
                    user.setRole_id(2);

                    users.add(user);
                }

                log.info("users "+users);
                userService.insertUser(users);

            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("未成功录入教师档案信息！");
        }
        return result;
    }


    /**
     * 批量录入学生档案信息，并自动插入用户表
     * by 吴小莉
     */
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
                    user.setRole_id(1);

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

}
