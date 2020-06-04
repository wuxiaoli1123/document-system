package com.wxl.system.service;


import com.wxl.system.dao.PermissionDAO;
import com.wxl.system.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDAO permissionDAO;

    //查询权限表中对应信息
    @Override
    public List<Permission> findAllPermission(){
         return permissionDAO.findAllPermission();
    }

    //修改权限
    @Override
    public void updatePermission(Perm perm)
    {
           permissionDAO.updatePermission(perm);
    }

    //删除权限信息
    @Override
    public void deletePermission(Integer id)
    {
        permissionDAO.deletePermission(id);
    }

    //返回所有角色信息
    @Override
    public List<Role> findAllRole(){
        return permissionDAO.findAllRole();
    }

    //返回角色现有权限（角色id）
    @Override
    public List<Permission> findPermissionByRole(Integer role_id){
         return permissionDAO.findPermissionByRole(role_id);
    }

    //增加权限（角色id、权限id）
    @Override
    public void insertPermToRole(Role_Perm role_perm) {
         permissionDAO.insertPermToRole(role_perm);
    }

    //删除权限（角色id、权限id）
    @Override
    public void deletePermByRole(Role_Perm role_perm){

    }

}
