package com.example.demo.dao;

import java.util.List;

import com.example.demo.pojo.Goods;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodId);
    
    List<Goods> queryAllGoods();

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}