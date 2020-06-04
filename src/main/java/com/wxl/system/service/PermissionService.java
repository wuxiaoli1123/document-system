package com.wxl.system.service;

import com.wxl.system.entity.Perm;
import com.wxl.system.entity.Permission;
import com.wxl.system.entity.Role;
import com.wxl.system.entity.Role_Perm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionService {

    //查询权限表中对应信息
    List<Permission> findAllPermission();

    //修改权限
    void updatePermission(Perm perm);

    //删除权限信息
    void deletePermission(Integer id);

    //返回所有角色信息
    List<Role> findAllRole();

    //返回角色现有权限（角色id）
    List<Permission> findPermissionByRole(Integer role_id);

    //增加权限（角色id、权限id）
    void insertPermToRole(Role_Perm role_perm);

    //删除权限（角色id、权限id）
    void deletePermByRole(Role_Perm role_perm);

}
