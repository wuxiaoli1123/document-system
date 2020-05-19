package com.wxl.system.controller;

import com.wxl.system.entity.IsChoose;
import com.wxl.system.entity.Optional;
import com.wxl.system.entity.Result;
import com.wxl.system.entity.Sc;
import com.wxl.system.service.OptionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("optional")
public class OptionalController {

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
}
