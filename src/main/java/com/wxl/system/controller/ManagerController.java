package com.wxl.system.controller;



import com.wxl.system.entity.Manager;
import com.wxl.system.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("manager")
@Slf4j
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @GetMapping("findByMno")
    public Manager findByMno(String mno){
        log.info("admin:"+mno);
        return managerService.findByMno(mno);
    }
}
