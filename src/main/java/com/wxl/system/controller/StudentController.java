package com.wxl.system.controller;

import com.wxl.system.entity.*;
import com.wxl.system.service.OptionalService;
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

   /* *//**
     * 批量录入学生档案信息，并自动插入用户表
     * by 吴小莉
     *//*
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
    }*/


    //按学号查找学生
    @GetMapping("findBySno")
    public Student findBySno(String sno){
        return studentService.findBySno(sno);
    }

    //修改学生个人信息
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


   /* *//**
     * 查询学生信息----分页查询----用于登记成绩中，展示学生信息
     * by 吴小莉
     *//*
    @GetMapping("findByPage")
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

    /**
     * 根据学期，学生账号返回学生本学期的课表
     * by 吴小莉
     */
    @GetMapping("findScheduleS")
    public Object[] findScheduleT (String sno,String term){

        List<StuClassData> stuClassData = studentService.findStuClassData(sno, term);

        List<StuSchedule> stuSchedules = studentService.findScheduleS(stuClassData);

        /*for(int i=0;i<stuSchedules.size();i++){
            log.info("example "+stuSchedules.get(i));
        }*/

        Object[]  ScheduleS= new Object[stuSchedules.size()];

        //针对学生的每门课的上课时间
        for (int i = 0; i<stuSchedules.size(); i++){

            String tm = stuSchedules.get(i).getTime();
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

            map1.put("cname",stuSchedules.get(i).getCname());
            map1.put("classno",stuSchedules.get(i).getClassno());
            map1.put("tname",stuSchedules.get(i).getTname());
            map1.put("hour",stuSchedules.get(i).getHour());
            map1.put("place",stuSchedules.get(i).getPlace());
            map1.put("time",Objtime);

            ScheduleS[i] = map1;

            log.info("S "+ScheduleS[i]);
        }




        return ScheduleS;
    }

    //学生查询成绩
    @GetMapping("gradefindByPage")
    public Map<String, Object> gradefindByPage(Integer page, Integer rows, String sno) {
        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;
        HashMap<String, Object> map = new HashMap<>();
        //分页处理
        List<StuCheckGrade> sc = studentService.gradefindByPage(page, rows, sno);
        //计算总页数
        Integer totals = studentService.findTotal(sno);
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("sc", sc);
        map.put("totals", totals);
        map.put("totalPage", totalPage);
        map.put("page", page);
        return map;
    }


    @Autowired
    private OptionalService optionalService;

    //学生选课功能相关
    //按cno查找单个课程
    @GetMapping("findByCno")
    public Optional findByCno(String cno){
        return optionalService.findByCno(cno);
    }

    //  学生选课
    @GetMapping("updateNumber")
    public Result updateNumber(String cno,String sno){
        Result result = new Result();
        try {
            Optional optional = optionalService.findByCno(cno);
            IsChoose isChoose = optionalService.isChoose(sno);
            if (optional.getMax()>optional.getNumber()){
                if(isChoose.getIsChoose() == 0) {
                    Sc sc = new Sc();
                    sc.setTc_id(optional.getTc_id());
                    sc.setCno(cno);
                    sc.setClassno("0");
                    sc.setCredit(optional.getCredit());
                    sc.setSno(sno);
                    sc.setType("公共课");
                    sc.setTerm(optional.getTerm());
                    optionalService.updateNumber(cno, sno);
                    result.setMsg("选课成功!该课选课人数为" + optional.getNumber());
                } else {
                    throw new RuntimeException("已选择"+isChoose.getIsChoose()+"门课，"+"是否放弃课程"+isChoose.getCname());
                }
            } else {
                throw new RuntimeException("课程已满!!!该课选课人数为"+optional.getNumber());
            }
        }catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);
        }
        return result;
    }


    //学生更改选课
    @GetMapping("StuChangeCourse")
    public Result StuChangeCourse(String cno,String sno){
        Result result = new Result();
        try {
            Optional optional = optionalService.findByCno(cno);
            IsChoose isChoose = optionalService.isChoose(sno);
            if (optional.getMax()>optional.getNumber()){
                optionalService.StuChangeCourse(isChoose.getCno(),sno);
                Sc sc = new Sc();
                sc.setTc_id(optional.getTc_id());
                sc.setCno(cno);
                sc.setClassno("0");
                sc.setCredit(optional.getCredit());
                sc.setSno(sno);
                sc.setType("公共课");
                sc.setTerm(optional.getTerm());
                optionalService.addSc(sc);
                optionalService.updateNumber(cno, sno);
                result.setMsg("选课成功!该课选课人数为" + optional.getNumber());
            } else {
                throw new RuntimeException("课程已满!!!该课选课人数为"+optional.getNumber());
            }
        }catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);
        }
        return result;
    }

    //   分页呈现选课
    @GetMapping("sfindByPage")
    public Map<String, Object> sfindByPage(Integer page, Integer rows) {
        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;
        HashMap<String, Object> map = new HashMap<>();
        //分页处理
        List<Optional> optionals = optionalService.sfindByPage(page, rows);
        //计算总页数
        Integer totals = optionalService.findTotal();
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("optionals", optionals);
        map.put("totals", totals);
        map.put("totalPage", totalPage);
        map.put("page", page);
        return map;
    }


}
