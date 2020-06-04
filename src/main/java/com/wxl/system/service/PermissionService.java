package com.wxl.system.service;

import com.wxl.system.entity.Perm;
import com.wxl.system.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionService {

    //查询权限表中对应信息
    List<Permission> findAllPermission();

    //修改权限
    void updatePermission(Perm perm);

    //删除权限信息
    void deletePermission(Integer id);

}
