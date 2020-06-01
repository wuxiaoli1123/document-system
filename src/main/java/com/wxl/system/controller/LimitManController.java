package com.wxl.system.controller;


import com.wxl.system.entity.Result;
import com.wxl.system.entity.User;
import com.wxl.system.service.NoticeService;
import com.wxl.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("limitMan")
@Slf4j

public class LimitManController {

    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

    //增加管理员
    @RequestMapping("insertAdmin")
    public Result insertAdmin(@RequestBody List<User> users) {
        Result result = new Result();
        try {

            List<User> users1 = new ArrayList<>();
            for (int i = 0; i < users.size(); i++) {
                User user = new User();
                user.setAccount(users.get(i).getAccount());
                user.setPassword(users.get(i).getPassword());
                user.setRole_id(3);
                users1.add(user);
            }

            log.info("users " + users1);
            userService.insertUser(users1);
            result.setState(true).setMsg("成功录入管理员信息！");

        } catch (Exception e) {
            result.setState(false).setMsg("未成功录入管理员信息！");
        }
        return result;
    }
}
