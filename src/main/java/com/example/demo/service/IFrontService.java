package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.ShoppingCart;
import com.example.demo.pojo.Student;

public interface IFrontService {
	
	Student selectByNameAndPwd(String stuLogin,String stuPasswd);
	
	List<ShoppingCart> selectByStuId(Integer stuId);
	
	ShoppingCart selectNum(Integer stuId,Integer goodId);
	
	boolean updateNum(ShoppingCart cart);
	
	boolean addCart(ShoppingCart cart);
	
	boolean deleteByStuId(Integer stuId);
	
	boolean delSomeCart(Integer[] ids,Integer stuId);
	
	boolean delOneCart(Integer id,Integer stuId);
	
	boolean updateCartNum(Integer StuId,Integer goodId,Integer goodNum);
}