package com.wxl.system.controller;


import com.wxl.system.entity.Notice;
import com.wxl.system.entity.Result;
import com.wxl.system.entity.Student_abbr;
import com.wxl.system.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("notice")
@Slf4j
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    /**
     * 分页查询广播通知，展示在页面
     * by 吴小莉
     */
    @GetMapping("findByPage")
    public Map<String,Object> findStuAttention(Integer page, Integer rows, String account){
        page = page == null ? 1 : page;

        //前端页面应该是6.这里的“2”，仅用于后端测试
        rows = rows == null ? 2 : rows;

        HashMap<String,Object> map = new HashMap<>();

        //分页处理
        List<Notice> notices = noticeService.findByPage_Notice(page,rows,account);


        //计算总页数
        Integer totals = noticeService.findTotals_Notice(account);
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;

        map.put("notices",notices);
        map.put("totals",totals);
        map.put("totalPage",totalPage);

        return map;
    }

    /**
     * 查看消息具体内容
     * by 吴小莉
     */
    @GetMapping("findConBySnum")
    public String findConBySnum(String snum){
          return noticeService.findConBySnum(snum);
    }


    @GetMapping("MfindByPage")
    public Map<String,Object> findAttention(Integer page, Integer rows, String role){
        page = page == null ? 1 : page;

        //前端页面应该是6.这里的“2”，仅用于后端测试
        rows = rows == null ? 3 : rows;

        HashMap<String,Object> map = new HashMap<>();

        //分页处理
        List<Notice> notices = noticeService.findByPage_NoticeTS(page,rows,role);


        //计算总页数
        Integer totals = noticeService.findTotals_NoticeTS(role);
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;

        map.put("notices",notices);
        map.put("totals",totals);
        map.put("totalPage",totalPage);

        return map;
    }



}
