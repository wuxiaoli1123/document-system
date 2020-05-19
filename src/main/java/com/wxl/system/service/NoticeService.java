package com.wxl.system.service;

import com.wxl.system.entity.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoticeService {

    //参数1：当前页； 参数2：每页显示记录条数---Notice
    List<Notice> findByPage_Notice(Integer page,Integer rows,String account);


    //查询总条数----Notice
    Integer findTotals_Notice(String account);


    String findConBySnum(String snum);


    //管理员端返回学生和教师的广播消息
    List<Notice> findByPage_NoticeTS(Integer start,Integer rows,Integer role_id);

    //返回满足条件的结果集的大小---notice
    Integer findTotals_NoticeTS(Integer role_id);

}
