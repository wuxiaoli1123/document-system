package com.wxl.system.controller;

import com.wxl.system.entity.Sc;
import com.wxl.system.service.ScService;
import com.wxl.system.service.ScServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("sc")
public class ScController {

    @Autowired
    private ScService scService;


    @GetMapping("gradefindByPage")
    public Map<String, Object> gradefindByPage(Integer page, Integer rows, String sno) {
        page = page == null ? 1 : page;
        rows = rows == null ? 4 : rows;
        HashMap<String, Object> map = new HashMap<>();
        //分页处理
        List<Sc> sc = scService.gradefindByPage(page, rows, sno);
        //计算总页数
        Integer totals = scService.findTotal(sno);
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("provinces", sc);
        map.put("totals", totals);
        map.put("totalPage", totalPage);
        map.put("page", page);
        return map;
    }

}
