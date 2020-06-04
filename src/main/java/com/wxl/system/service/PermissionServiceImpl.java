package com.wxl.system.service;


import com.wxl.system.dao.PermissionDAO;
import com.wxl.system.entity.Perm;
import com.wxl.system.entity.Permission;
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

}
