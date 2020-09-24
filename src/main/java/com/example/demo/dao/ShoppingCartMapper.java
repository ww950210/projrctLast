package com.example.demo.dao;

import java.util.List;

import com.example.demo.pojo.ShoppingCart;

public interface ShoppingCartMapper {
    int deleteByPrimaryKey(Integer stuId,Integer goodId);
    
    int deleteByStuId(Integer stuId);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Integer cartId);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);
    
    List<ShoppingCart> selectByStuId(Integer stuId);
    
    ShoppingCart selectNum(Integer stuId,Integer goodId);
    
    boolean updateNum(ShoppingCart cart);
    
    boolean updateCartNum(Integer stuId,Integer goodId,Integer goodNum);
}