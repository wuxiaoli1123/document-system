package com.wxl.system.service;

import com.wxl.system.dao.ScDAO;
import com.wxl.system.entity.Sc;
import com.wxl.system.entity.StuCheckGrade;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScServicelmpl implements ScService{
    @Autowired
    private ScDAO scDAO;

    @Override
    public  List<StuCheckGrade> gradefindByPage(Integer page, Integer rows , @Param("sno") String sno) {
        int start = (page-1)*rows;
        return scDAO.gradefindByPage(start,rows,sno);
    }

    @Override
    public void addSc(Sc sc) { scDAO.addSc(sc);}


    @Override
    public Integer findTotal(String sno) {
        return scDAO.findTotal(sno);
    }
}
