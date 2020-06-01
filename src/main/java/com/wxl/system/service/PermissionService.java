package com.wxl.system.service;

import com.wxl.system.entity.Permission;

import java.util.List;

public interface PermissionService {

    //查询权限表中对应信息
    List<Permission> findAllPermission();

}
