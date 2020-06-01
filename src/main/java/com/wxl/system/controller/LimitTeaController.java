package com.wxl.system.controller;


import com.wxl.system.entity.Result;
import com.wxl.system.entity.StuGrade;
import com.wxl.system.entity.Student_abbr;
import com.wxl.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("limitTea")
@Slf4j
public class LimitTeaController {
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

    /**
     * 查询学生信息----分页查询----用于登记成绩中，展示学生信息
     * by 吴小莉
     */
    @GetMapping("findByPage")
    public Map<String,Object> findByPage(Integer page, Integer rows, String cname, String grade, String classno){
        page = page == null ? 1 : page;

        //前端页面应该是20.这里的“2”，仅用于后端测试
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
