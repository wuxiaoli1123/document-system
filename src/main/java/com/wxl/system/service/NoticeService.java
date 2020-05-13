package com.wxl.system.service;

import com.wxl.system.entity.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeService {

    //参数1：当前页； 参数2：每页显示记录条数---Notice
    List<Notice> findByPage_Notice(Integer page,Integer rows,String account);


    //查询总条数----Notice
    Integer findTotals_Notice(String account);

}
