package com.wxl.system.dao;

import com.wxl.system.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeDAO {

    //（学生和教师端查看自己的）根据页码数返回结果集---Notice
    List<Notice> findByPage_Notice(@Param("start") Integer start, @Param("rows") Integer rows, @Param("account") String account);

    //返回满足条件的结果集的大小---notice
    Integer findTotals_Notice(@Param("account") String account);

    String findConBySnum(String snum);


    //管理员端返回学生和教师的广播消息
    List<Notice> findByPage_NoticeTS(@Param("start") Integer start, @Param("rows") Integer rows, @Param("role") String role);

    //返回满足条件的结果集的大小---notice
    Integer findTotals_NoticeTS(@Param("role") String role);

    //管理员发布通知相关          --syq
    Integer addNotice(Notice notice);

    //管理员删除通知相关          --syq
    Integer delNotice(List<Integer> snum);



}
