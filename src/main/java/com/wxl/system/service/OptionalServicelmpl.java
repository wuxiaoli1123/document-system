package com.wxl.system.service;

import com.wxl.system.dao.OptionalDAO;
import com.wxl.system.dao.ScDAO;
import com.wxl.system.entity.Optional;
import com.wxl.system.entity.Sc;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OptionalServicelmpl implements OptionalService{
    @Autowired
    private OptionalDAO optionalDAO;

    //学生选课功能相关
    //按cno查找单个课程
    @Override
    public Optional findByCno(String cno){return optionalDAO.findByCno(cno);}

    //分页呈现选课
    @Override
    public List<Optional> sfindByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return optionalDAO.sfindByPage(start,rows);
    }

    //学生选课
    @Override
    public void update(String cno,String sno) { optionalDAO.update(cno,sno);}

    //查询课程总数
    @Override
    public Integer findTotal() {
        return optionalDAO.findTotal();
    }


    //发布选课相关
    //批量发布选课
    @Override
    public  Integer addOptional(List<Optional> list){return optionalDAO.addOptional(list);}

    //管理员选择需要添加的课程
    @Override
    public Optional findTcByCno(String cno){return optionalDAO.findTcByCno(cno);}

}
