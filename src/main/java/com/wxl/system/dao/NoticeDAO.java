package com.wxl.system.dao;

import com.wxl.system.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeDAO {

    //根据页码数返回结果集---Notice
    List<Notice> findByPage_Notice(@Param("start") Integer start, @Param("rows") Integer rows, @Param("account") String account);

    //返回满足条件的结果集的大小---notice
    Integer findTotals_Notice(@Param("account") String account);

    void deleteByList(List<Notice> notices);

    String findConBySnum(String snum);

}
