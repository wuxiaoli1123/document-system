package com.wxl.system.service;

import com.wxl.system.dao.OptionalDAO;
import com.wxl.system.dao.ScDAO;
import com.wxl.system.entity.Optional;
import com.wxl.system.entity.Sc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OptionalServicelmpl implements OptionalService{
    @Autowired
    private OptionalDAO optionalDAO;

    @Autowired
    private ScDAO scDAO;

    @Override
    public Optional findByCno(String cno){return optionalDAO.findByCno(cno);}

    @Override
    public List<Optional> sfindByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return optionalDAO.sfindByPage(start,rows);
    }

    @Override
    public void update(String cno,String sno) { optionalDAO.update(cno,sno);}

    @Override
    public void addSc(Sc sc) { scDAO.addSc(sc);}

    @Override
    public Integer findTotal() {
        return optionalDAO.findTotal();
    }

    @Override
    public  Integer addOptional(List<Optional> list){return optionalDAO.addOptional(list);}

//    @Override
//    public boolean isfull(String cno) {
//        Optional optional = optionalDAO.findByCno(cno);
//        if (optional != null) {
//            if (optional.getNumber() <= optional.getMax()) {
//                optionalDAO.update(cno);
//            } else {
//                throw new RuntimeException("该课已选满！");
//            }
//        }
//    }
}
