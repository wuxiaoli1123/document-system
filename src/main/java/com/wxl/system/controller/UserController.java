package com.wxl.system.controller;

import com.wxl.system.entity.*;
import com.wxl.system.service.NoticeService;
import com.wxl.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("user")
@CrossOrigin //允许跨域
@Slf4j

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

    /**
     * 用户登录
     * by 吴小莉
     */
    @RequestMapping("login")
    public Result login(@RequestBody User user, HttpServletRequest request) {
        //@RequestBody User user, HttpServletRequest request
        //String account,String password

        Result result = new Result();

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());

        User user1 = userService.findByAccount(user.getAccount());

        try {

            if (user1.getUser_status().equals("0")) {
                result.setState(false).setMsg("用户不存在！");
            } else {
                //执行登录方法，如果没有异常，就登录成功
                subject.login(token);

                //通过账号获取用户角色
                String role = userService.findRoleByAccount(user.getAccount());
                log.info("role:" + role);

                String username;

                if (role.equals("3")) {
                    //根据用户角色，查询表，获取用户姓名
                    username = userService.findNameByAccountM(user.getAccount());
                    log.info("用户名:" + username);
                } else if (role.equals("2")) {
                    username = userService.findNameByAccountT(user.getAccount());
                } else {
                    username = userService.findNameByAccountS(user.getAccount());
                }
                result.setRole(role);
                result.setUsername(username);

                result.setState(true).setMsg("登录成功！");
            }
        } catch (UnknownAccountException e) {
            //用户名不存在
            result.setState(false).setMsg("用户名不存在！");
        } catch (IncorrectCredentialsException e) {
            //密码错误
            result.setState(false).setMsg("密码错误！");
        }




        /*log.info("user:" + user);
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
        }*/
        return result;
    }

    /**
     * 用户修改密码
     * by 吴小莉
     */
    @PostMapping("changePassword")
    public Result changePassword(@RequestBody UserChangePwd userChangePwd ) {

        Result result = new Result();
        User user = userService.findByAccount(userChangePwd.getAccount());


        try {

            if (user == null) {
                result.setState(false).setMsg("用户账号输入错误！");
            } else {

                //盐值加密
                String salt = user.getPrivate_salt();

                Md5Hash md5Hash = new Md5Hash(userChangePwd.getOldpassword(), salt);//模拟MD5加密一次

                String moldpassword = md5Hash.toString();


                if (user.getPassword().equals(moldpassword)) {

                    if ((userChangePwd.getNewpassword() == null || userChangePwd.getNewpassword().trim().equals("")) && (userChangePwd.getConpassword() == null || userChangePwd.getConpassword().trim().equals(""))) {
                        result.setState(false).setMsg("新密码和确认密码不能为空！");
                    } else {
                        if (userChangePwd.getNewpassword() == null || userChangePwd.getNewpassword().trim().equals("")) {
                            result.setState(false).setMsg("新密码不能为空！");
                        } else if (userChangePwd.getConpassword() == null || userChangePwd.getConpassword().trim().equals("")) {
                            result.setState(false).setMsg("确认密码不能为空！");
                        } else {
                            /*newpassword.length()<8 || newpassword.length()>20*/
                            if (userChangePwd.getNewpassword().length() < 8 || userChangePwd.getNewpassword().length() > 20) {
                                result.setState(false).setMsg("密码长度需要设置在8-20之间！");
                            } else {
                                boolean isDigit = false;
                                boolean isLetter = false;
                                boolean isChinese = false;
                                for (int i = 0; i < userChangePwd.getNewpassword().length(); i++) {
                                    if (Character.isDigit(userChangePwd.getNewpassword().charAt(i))) {  //用char包装类中的判断数字的方法判断每一个字符
                                        isDigit = true;
                                    } else if (Character.isLetter(userChangePwd.getNewpassword().charAt(i))) { //用char包装类中的判断字母的方法判断每一个字符
                                        isLetter = true;
                                    }
                                }

                                Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                                Matcher m = p.matcher(userChangePwd.getNewpassword());

                                if (m.find()) {
                                    isChinese = true;
                                }

                                if (isChinese) {
                                    result.setState(false).setMsg("密码中不能包含中文！");
                                } else if (!isDigit && !isLetter) {
                                    result.setState(false).setMsg("密码必须包含数字和字母！");
                                } else if (!isDigit) {
                                    result.setState(false).setMsg("密码必须包含数字！");
                                } else if (!isLetter) {
                                    result.setState(false).setMsg("密码必须包含字母！");
                                } else {
                                    if (!userChangePwd.getNewpassword().equals(userChangePwd.getConpassword())) {
                                        result.setState(false).setMsg("两次密码输入不一致！");
                                    } else {
                                        user.setPassword(userChangePwd.getNewpassword());

                                        //修改密码
                                        userService.changePassword(user);

                                        result.setState(true).setMsg("密码修改成功！");
                                    }
                                }
                            }
                        }
                    }
                } else {
                    result.setState(false).setMsg("原密码输入错误");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("noauth")
    @ResponseBody
    public String unauthorized() {
        return "未经授权无法访问此页面";
    }


    /*//增加管理员
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
    }*/

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
    public String findConBySnum(Integer snum){
        return noticeService.findConBySnum(snum);
    }

    /**
     * 返回学校所有学院
     * by 吴小莉
     */
    @GetMapping("findAllCollege")
    public List<String> findAllCollege(){
        return userService.findAllCollege();
    }

    /**
     * 根据学院返回专业
     * by 吴小莉
     */
    @GetMapping("findMajorByCollege")
    public List<String> findMajorByCollege(String college){
        return userService.findMajorByCollege(college);
    }
}