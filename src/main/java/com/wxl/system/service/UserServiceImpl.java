package com.wxl.system.service;


import com.wxl.system.dao.UserDAO;
import com.wxl.system.entity.User;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired()
    private UserDAO userDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {

        User userDB = userDAO.findByAccount(user.getAccount());

        if (userDB != null) {
            if (userDB.getPassword().equals(user.getPassword())) {
                return userDB;
            }
            throw new RuntimeException("密码输入错误！");
        } else {
            throw new RuntimeException("用户账号输入错误！");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findByAccount(String account){
        return userDAO.findByAccount(account);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public String findRoleByAccount(String account){
        return userDAO.findRoleByAccount(account);
    }

   @Override
   @Transactional(propagation = Propagation.SUPPORTS)
   public String findNameByAccountS(String account){
        return userDAO.findNameByAccountS(account);
   }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public String findNameByAccountT(String account){
        return userDAO.findNameByAccountT(account);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public String findNameByAccountM(String account){
        return userDAO.findNameByAccountM(account);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void changePassword(User user){

        String password = user.getPassword();
        String salt = RandomStringUtils.randomNumeric(6);
        user.setPrivate_salt(salt);
        Md5Hash md5Hash = new Md5Hash(password,salt);//模拟MD5加密一次
        user.setPassword(md5Hash.toString());

        userDAO.changePassword(user);
    }

    //插入用户表
    public void insertUser(List<User> users){

        List<User> users1 = new ArrayList();

        for (int i=0;i<users.size();i++){
            User user = users.get(i);
            String password = user.getPassword();
            String salt = RandomStringUtils.randomNumeric(6);
            user.setPrivate_salt(salt);
            Md5Hash md5Hash = new Md5Hash(password,salt);//模拟MD5加密一次
            user.setPassword(md5Hash.toString());
            user.setUser_status("1");

            users1.add(user);
        }


        userDAO.insertUser(users1);
    };
}
