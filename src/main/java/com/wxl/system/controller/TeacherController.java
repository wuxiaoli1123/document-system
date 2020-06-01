package com.wxl.system.controller;

import com.alibaba.druid.sql.visitor.functions.Lcase;
import com.wxl.system.entity.*;
import com.wxl.system.service.StudentService;
import com.wxl.system.service.TeacherService;
import com.wxl.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.*;

@RestController
@RequestMapping("teacher")
@CrossOrigin //允许跨域
@Slf4j
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    /*@Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;



    /**
     * 教师登录学生成绩
     * by 吴小莉
     */
    /*@PostMapping("updateGrade")
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
*/

    /**
     * 根据学期、年级返回教师所教课程名
     * by 吴小莉
     */
    @GetMapping("findCname")
    public  List<String> findCnameByTTG(String tno,String term,String grade){
        return teacherService.findCnameByTTG(tno, term, grade);
    }


    /**
     * 根据学期、年级返回教师所教课程名
     * by 吴小莉
     */
    @GetMapping("findClassno")
    public List<String> findClassnoByTTGC(String tno,String term,String grade,String cname){
        return teacherService.findClassnoByTTGC(tno, term, grade, cname);
    }

    /**
     * 根据学期，教师账号返回教师本学期的课表
     * by 吴小莉
     */
    @GetMapping("findScheduleT")
    public Object[] findScheduleT (String tno,String term){

           List<TeaSchedule> teaSchedules= teacherService.findScheduleT(tno, term);

           Object[]  ScheduleT= new Object[teaSchedules.size()];

           //针对教师的每门课的上课时间
           for (int i = 0; i<teaSchedules.size(); i++){

                String tm = teaSchedules.get(i).getTime();
                HashMap<String,Object> map1 = new HashMap<>();

                String[] t = tm.split(",",7);

                Object[]  Objtime= new Object[t.length];

                for(int j=0;j<t.length;j++){

                    HashMap<String,Object> map2 = new HashMap<>();

                    //截取第一位
                    char k = t[j].charAt(0);

                    //截取剩下的字符串
                    String item = t[j].substring(1);

                    int num = Integer.parseInt(String.valueOf(k));

                    String day = null;
                    String time = null;

                    switch (num){
                        case 1:
                            day="星期一";
                            break;
                        case 2:
                            day="星期二";
                            break;
                        case 3:
                            day="星期三";
                            break;
                        case 4:
                            day="星期四";
                            break;
                        case 5:
                            day="星期五";
                            break;
                        case 6:
                            day="星期六";
                            break;
                        case 7:
                            day="星期七";
                            break;
                    }

                    boolean status = t[j].contains("0");

                    if(status){
                        if(t[j].length()==5){
                            //String item = t[j].substring(1);
                            if(item.equals("0102")){
                                time = "1112";
                            }else if(item.equals("0203")){
                                time = "1213";
                            }else if(item.equals("8900")){
                                time = "8910";
                            }
                        }else if(t[j].length() == 4){
                            if(item.equals("900")){
                                time = "910";
                            }
                        }else if(t[j].length() == 7){
                            if(item.equals("010203")){
                                time = "111213";
                            }
                        }
                    }else{
                        time = item;
                    }

                    map2.put("time",time);
                    map2.put("day",day);


                    Objtime[j] = map2;
                }

                map1.put("cno",teaSchedules.get(i).getCno());
                map1.put("cname",teaSchedules.get(i).getCname());
                map1.put("classno",teaSchedules.get(i).getClassno());
                map1.put("place",teaSchedules.get(i).getPlace());
                map1.put("grade",teaSchedules.get(i).getGrade());
                map1.put("hour",teaSchedules.get(i).getHour());
                map1.put("time",Objtime);

               ScheduleT[i] = map1;

               log.info("S "+ScheduleT[i]);
           }
           return ScheduleT;
    }

    //修改教师个人信息
    @PostMapping("update")
    public Result update(@RequestBody Teacher teacher) {
        Result result = new Result();
        try {
            teacherService.update(teacher);
            log.info(teacher.getTno());
            result.setMsg("修改信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 查询学生信息----分页查询----用于登记成绩中，展示学生信息
     * by 吴小莉
     */
    /*@GetMapping("findByPage")
    public Map<String,Object> findByPage(Integer page,Integer rows,String cname,String grade, String classno){
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
*/
}
