package com.wxl.system.controller;

import com.wxl.system.entity.Optional;
import com.wxl.system.entity.Result;
import com.wxl.system.service.OptionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin //允许跨域
@RequestMapping("optional")
@Slf4j
public class OptionalController {

    @Autowired
    private OptionalService optionalService;

    @GetMapping("findByCno")
    public Optional findByCno(String cno){
        return optionalService.findByCno(cno);
    }


    @GetMapping("update")
    public void update(String cno){
        Result result = new Result();
        optionalService.update(cno);
        result.setMsg("选课成功");
    }

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

    //发布单个选课
    @PostMapping("save")
    public Result save(@RequestBody Optional optional) {
        Result result = new Result();
        try {
            optionalService.save(optional);
            result.setMsg("添加课程成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("添加课程失败!!!");
        }
        return result;
    }

    //    批量发布选课
    @PostMapping("addOptional")
    public Result addOptional(@RequestBody Optional[] op_list) {
        log.info(op_list.toString());
        List<Optional> list = (List<Optional>) java.util.Arrays.asList(op_list);
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
