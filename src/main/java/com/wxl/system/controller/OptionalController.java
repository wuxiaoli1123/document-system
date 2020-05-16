package com.wxl.system.controller;

import com.wxl.system.entity.Optional;
import com.wxl.system.entity.Result;
import com.wxl.system.entity.Sc;
import com.wxl.system.service.OptionalService;
import com.wxl.system.service.ScService;
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

    @Autowired
    private ScService scService;

    //    按cno查找单个课程
    @GetMapping("findByCno")
    public Optional findByCno(String cno){
        return optionalService.findByCno(cno);
    }

    //  学生选课
    @GetMapping("update")
    public Result update(String cno,String sno){
        Result result = new Result();
        try {
            Optional optional = optionalService.findByCno(cno);
            if (optional.getMax()>optional.getNumber()){
                optionalService.update(cno,sno);
                Sc sc = new Sc();
                sc.setCno(cno);
                sc.setClassno("0");
                sc.setCredit(optional.getCredit());
                sc.setGrade(0.00);
                sc.setSno(sno);
                sc.setType("公共课");
                scService.addSc(sc);
                result.setMsg("选课成功!");
            } else {
                throw new RuntimeException("课程已满!!!");
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
        map.put("provinces", optionals);
        map.put("totals", totals);
        map.put("totalPage", totalPage);
        map.put("page", page);
        return map;
    }

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


}
