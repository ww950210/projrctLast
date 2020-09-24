package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.College;
import com.example.demo.pojo.Goods;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Userinfo;

public interface ICardService {
	
	Userinfo selectByNameAndPwd(String userLogin,String userPasswd);
	
	List<Student> listAllStudent();
	
	Student queryBuStuId(Integer id);
	
	List<College> queryAllCollege();
	
	boolean updateStudent(Student stu);
	
	boolean addStudent(Student stu);
	
	boolean delStudent(Integer id);
	
	boolean delAllStudent(Integer[] ids);
	
	boolean updatePwd(Userinfo user,String neWPasswd);
	
	List<Student> queryByName(String stuName);
	
	List<Goods> queryAllGoods();
	
	boolean addGoods(Goods goods);
	
	Goods queryOneGoods(Integer id);
	
	boolean updateGoods(Goods goods);
	
	boolean delGoods(Integer id);
	
	boolean delAllGoods(Integer[] ids);
}