package com.wxl.system.controller;

import com.wxl.system.entity.*;
import com.wxl.system.service.TeacherService;
import com.wxl.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("teacher")
@CrossOrigin //允许跨域
@Slf4j
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;


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
                     user.setRole("2");

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

    @GetMapping("findByTno")
    public Teacher findByTno(String tno){
        return teacherService.findByTno(tno);
    }



    /**
     * 教师登录学生成绩
     * by 吴小莉
     */
    @PostMapping("updateGrade")
    public Result updateGrade(@RequestBody List<StuGrade> stuGrades){
        Result result = new Result();

        try{
            boolean isTrue = true;
            for(int i=0;i<stuGrades.size();i++){
                StuGrade stuGrade = new StuGrade();
                stuGrade = stuGrades.get(i);
                if(stuGrade.getGrade()<0 || stuGrade.getGrade()>100){
                    isTrue = false;
                }
            }

            if(isTrue){
                for(int i=0;i<stuGrades.size();i++){
                    StuGrade stuGrade = new StuGrade();
                    stuGrade = stuGrades.get(i);
                    //保留两位小数
                    DecimalFormat df = new DecimalFormat( "0.00 ");
                    double g = Double.parseDouble(df.format(stuGrade.getGrade()));

                    stuGrade.setGrade(g);
                    log.info("成绩 "+stuGrade.getGrade());
                }
                teacherService.updateGrade(stuGrades);
                result.setState(true).setMsg("成功登记学生成绩！");
            }else{
                result.setState(false).setMsg("存在分数值不位于0-100！");
            }

        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg("未成功登记学生成绩！");
        }
        return result;
    }




}
