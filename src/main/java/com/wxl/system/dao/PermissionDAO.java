package com.wxl.system.dao;


import com.wxl.system.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionDAO {

    //查询权限表中对应信息
    List<Permission> findAllPermission();

    //修改权限
    void updatePermission(@Param("id") Integer id, @Param("name") String name,@Param("code") String code);

    //删除权限信息
    void deletePermission(Integer id);

}
