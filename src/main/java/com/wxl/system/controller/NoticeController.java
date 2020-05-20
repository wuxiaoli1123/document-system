package com.wxl.system.controller;


import com.wxl.system.entity.Notice;
import com.wxl.system.entity.Result;
import com.wxl.system.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


   /* *//**
     * 分页查询广播通知，展示在页面
     * by 吴小莉
     *//*
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

    *//**
     * 查看消息具体内容
     * by 吴小莉
     *//*
    @GetMapping("findConBySnum")
    public String findConBySnum(Integer snum){
          return noticeService.findConBySnum(snum);
    }*/


    /*@GetMapping("MfindByPage")
    public Map<String,Object> findAttention(Integer page, Integer rows, Integer role_id){
        page = page == null ? 1 : page;

        //前端页面应该是6.这里的“2”，仅用于后端测试
        rows = rows == null ? 3 : rows;

        HashMap<String,Object> map = new HashMap<>();

        //分页处理
        List<Notice> notices = noticeService.findByPage_NoticeTS(page,rows,role_id);


        //计算总页数
        Integer totals = noticeService.findTotals_NoticeTS(role_id);
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;

        map.put("notices",notices);
        map.put("totals",totals);
        map.put("totalPage",totalPage);

        return map;
    }

    //管理员发布通知相关          --syq
    @PostMapping("addNotice")
    public Result save(@RequestBody Notice notice) {
        Result result = new Result();
        try {
            noticeService.addNotice(notice);
            result.setMsg("发布公告成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("发布公告失败!!!");
        }
        return result;
    }

    //管理员删除通知相关          --syq
    @PostMapping("delNotice")
    public Result delNotice(@RequestBody Integer[] l_snum){
        List<Integer> snum = (List<Integer>)java.util.Arrays.asList(l_snum);
        Result result = new Result();
        try {
            noticeService.delNotice(snum);
            result.setMsg("删除通知成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("删除通知失败!!!");
        }
        return result;
    }
*/
}
