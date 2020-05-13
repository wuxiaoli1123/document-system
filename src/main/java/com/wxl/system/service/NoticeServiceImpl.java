package com.wxl.system.service;


import com.wxl.system.dao.NoticeDAO;
import com.wxl.system.entity.Notice;
import com.wxl.system.entity.Student_abbr;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeDAO noticeDAO;

    @Override
    public List<Notice> findByPage_Notice(Integer page,Integer rows,String account){
        int start = (page - 1)*rows;
        return noticeDAO.findByPage_Notice(start,rows,account);
    }

    @Override
    public Integer findTotals_Notice(String account) {
        return noticeDAO.findTotals_Notice(account);
    }

    @Override
    public String findConBySnum(String snum){
        return noticeDAO.findConBySnum(snum);
    }

}
