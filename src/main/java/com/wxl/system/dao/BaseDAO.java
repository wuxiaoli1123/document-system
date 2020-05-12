package com.wxl.system.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDAO<T, K> {


    void save(T t);

    void update(T t);

    void delete(K k);

    T findOne(K k);

    //查询所有返回结果集
    List<T> findAll();

    //根据页码数返回结果集
    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    //返回满足条件的结果集的大小
    Integer findTotals();

}
