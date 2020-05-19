package com.wxl.system.service;


import com.wxl.system.dao.NoticeDAO;
import com.wxl.system.entity.Notice;
import com.wxl.system.entity.Optional;
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
    public String findConBySnum(Integer snum){
        return noticeDAO.findConBySnum(snum);
    }

    //管理员端返回学生和教师的广播消息
    @Override
    public List<Notice> findByPage_NoticeTS(Integer page,Integer rows,Integer role_id){
        int start = (page - 1)*rows;
        return noticeDAO.findByPage_NoticeTS(start,rows,role_id);
    }

    //返回满足条件的结果集的大小---notice
    @Override
    public Integer findTotals_NoticeTS(Integer role_id){
        return noticeDAO.findTotals_NoticeTS(role_id);
    }

    //管理员发布通知相关          --syq
    @Override
    public  Integer addNotice(Notice notice){return noticeDAO.addNotice(notice);}

    //管理员删除通知相关          --syq
    @Override
    public  Integer delNotice(List<Integer> snum){return noticeDAO.delNotice(snum);}
}
