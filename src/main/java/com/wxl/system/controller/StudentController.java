package com.wxl.system.controller;

import com.wxl.system.entity.Result;
import com.wxl.system.entity.Student;
import com.wxl.system.entity.Student_abbr;
import com.wxl.system.entity.User;
import com.wxl.system.service.StudentService;
import com.wxl.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            log.info(student.getSno());
            result.setMsg("修改信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

   //查询学生信息----分页查询----用于登记成绩中，展示学生信息
    @GetMapping("findByPage")
    public Map<String,Object> findByPage(Integer page,Integer rows,String cname,String grade, String classno){
        page = page == null ? 1 : page;
        rows = rows == null ? 2 : rows;

        HashMap<String,Object> map = new HashMap<>();

        //分页处理
        List<Student_abbr> student_abbrs = studentService.findByPage(page,rows,cname,grade,classno);

        log.info("student   size" + student_abbrs.size());

        //计算总页数
        Integer totals = studentService.findTotals(cname,grade,classno);
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("students",student_abbrs);
        map.put("totals",totals);
        map.put("totalPage",totalPage);

        return map;

    }




}
