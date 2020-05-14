package com.wxl.system.dao;

import com.wxl.system.entity.Optional;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OptionalDAO extends BaseDAO<Optional,String>{
    Optional findByCno(String cno);
    void update(String cno);
    Integer addOptional(List<Optional> list);
//    boolean isfull(String cno);
    List<Optional> sfindByPage(@Param("start") Integer start, @Param("rows") Integer rows);
    Integer findTotal();
}
