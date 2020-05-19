package com.wxl.system.controller;



import com.wxl.system.entity.Manager;
import com.wxl.system.entity.Result;
import com.wxl.system.entity.Teacher;
import com.wxl.system.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("manager")
@Slf4j
public class ManagerController {
    @Autowired
    private ManagerService managerService;

//    查看管理员个人信息
    @GetMapping("findByMno")
    public Manager findByMno(String mno){
        log.info("admin:"+mno);
        return managerService.findByMno(mno);
    }

    //修改教师个人信息
    @PostMapping("update")
    public Result update(@RequestBody Manager manager) {
        Result result = new Result();
        try {
            managerService.update(manager);
            log.info(manager.getMno());
            result.setMsg("修改信息成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }
}
