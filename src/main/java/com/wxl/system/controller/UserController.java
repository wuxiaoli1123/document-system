package com.wxl.system.controller;

import com.wxl.system.entity.Result;
import com.wxl.system.entity.User;
import com.wxl.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("user")
@CrossOrigin //允许跨域
@Slf4j

public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * by 吴小莉
     */
    @RequestMapping("login")
    public Result login(@RequestBody User user, HttpServletRequest request) {
        Result result = new Result();
        log.info("user:" + user);
        try {
            User userDB = userService.login(user);
            //通过账号获取用户角色
            String role = userService.findRoleByAccount(user.getAccount());
            log.info("role:" + role);

            String username;

            //根据用户角色，查询表，获取用户姓名
            if(role.equals("3")){
                username = userService.findNameByAccountM(user.getAccount());
                log.info("用户名:" + username);
            } else if(role.equals("2")){
                username = userService.findNameByAccountT(user.getAccount());
            }else{
                username = userService.findNameByAccountS(user.getAccount());
            }

            result.setRole(role);
            result.setUsername(username);

            //登录成功之后保存用户的标记,应该用redis,还没学，智能这样
            request.getServletContext().setAttribute(userDB.getAccount(), userDB);
            //返回用户的账号，以便其他接口的验证，前台数据存放在localstorage里面
            result.setMsg("登录成功！").setUser_account(userDB.getAccount());

            log.info("result:" + result);

        } catch (Exception e) {
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 用户修改密码
     * by 吴小莉
     */
    @PostMapping("changePassword")
    public Result changePassword(String account,String oldpassword,String newpassword,String conpassword){
        Result result = new Result();
        User user = userService.findByAccount(account);
        try {

            if(user == null) {
                result.setState(false).setMsg("用户账号输入错误！");
            }else{

           if(user.getPassword().equals(oldpassword)){

                   if((newpassword == null || newpassword.trim().equals(""))&&(conpassword == null || conpassword.trim().equals(""))){
                       result.setState(false).setMsg("新密码和确认密码不能为空！");
                   }else {
                       if(newpassword == null || newpassword.trim().equals("")){
                           result.setState(false).setMsg("新密码不能为空！");
                       }else if(conpassword == null || conpassword.trim().equals("")){
                           result.setState(false).setMsg("确认密码不能为空！");
                       }else {
                           if(newpassword.length()<8 || newpassword.length()>20){
                               result.setState(false).setMsg("密码长度需要设置在8-20之间！");
                           }else {
                               boolean isDigit = false;
                               boolean isLetter = false;
                               boolean isChinese = false;
                               for (int i= 0;i<newpassword.length();i++){
                                    if (Character.isDigit(newpassword.charAt(i))) {  //用char包装类中的判断数字的方法判断每一个字符
                                                 isDigit = true;
                                    } else if (Character.isLetter(newpassword.charAt(i))) { //用char包装类中的判断字母的方法判断每一个字符
                                               isLetter = true;
                                    }
                               }

                               Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                               Matcher m = p.matcher(newpassword);

                               if(m.find()){
                                   isChinese = true;
                               }

                               if(isChinese){
                                   result.setState(false).setMsg("密码中不能包含中文！");
                               }else if(!isDigit&&!isLetter){
                                   result.setState(false).setMsg("密码必须包含数字和字母！");
                               }else if(!isDigit){
                                   result.setState(false).setMsg("密码必须包含数字！");
                               }else if(!isLetter){
                                   result.setState(false).setMsg("密码必须包含字母！");
                               }else {
                                   if (!newpassword.equals(conpassword)){
                                       result.setState(false).setMsg("两次密码输入不一致！");
                                   }else {
                                                     user.setPassword(newpassword);
                                                     userService.changePassword(user);
                                                     result.setState(true).setMsg("密码修改成功！");
                                   }
                               }
                           }
                       }
/*                       log.info("account "+account);
                       log.info("oldpassword "+oldpassword);
                       log.info("newpassword "+newpassword);
                       log.info("conpassword "+conpassword);
                       log.info("userpasword " + user.getPassword());*/
                   }
            }else {
                result.setState(false).setMsg("原密码输入错误");
            }

            }

        }catch(Exception e){
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }


}
