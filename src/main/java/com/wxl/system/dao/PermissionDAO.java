package com.wxl.system.dao;


import com.wxl.system.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionDAO {

    //查询权限表中对应信息
    List<Permission> findAllPermission();

}
