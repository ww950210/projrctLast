package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ShoppingCartMapper;
import com.example.demo.dao.StudentMapper;
import com.example.demo.pojo.ShoppingCart;
import com.example.demo.pojo.Student;
import com.example.demo.util.MD5;
@Service
public class FrontServiceImpl implements IFrontService{
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;

	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.DEFAULT,readOnly=true)
	public Student selectByNameAndPwd(String stuLogin, String stuPasswd) {
		return studentMapper.selectByNameAndPwd(stuLogin,MD5.enctypeMD5("haha"+stuPasswd));
	}
	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.DEFAULT,readOnly=true)
	public List<ShoppingCart> selectByStuId(Integer stuId) {
		return shoppingCartMapper.selectByStuId(stuId);
	}
	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.DEFAULT,readOnly=true)
	public ShoppingCart selectNum(Integer stuId, Integer goodId) {
		return shoppingCartMapper.selectNum(stuId, goodId);
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean updateNum(ShoppingCart cart) {
		return shoppingCartMapper.updateNum(cart);
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean addCart(ShoppingCart cart) {
		cart.setGoodNum(1);
		return shoppingCartMapper.insertSelective(cart)>0;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean delSomeCart(Integer[] ids,Integer stuId) {
		boolean flag=false;
		for(Integer id:ids) {
			flag=shoppingCartMapper.deleteByPrimaryKey(stuId,id)>0;
		}
		return flag;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean deleteByStuId(Integer stuId) {
		return shoppingCartMapper.deleteByStuId(stuId)>0;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean delOneCart(Integer id, Integer stuId) {
		return shoppingCartMapper.deleteByPrimaryKey(stuId, id)>0;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor = Exception.class)
	public boolean updateCartNum(Integer StuId, Integer goodId, Integer goodNum) {
		return shoppingCartMapper.updateCartNum(StuId, goodId, goodNum);
	}
}