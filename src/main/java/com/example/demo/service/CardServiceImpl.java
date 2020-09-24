package com.example.demo.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CollegeMapper;
import com.example.demo.dao.GoodsMapper;
import com.example.demo.dao.StudentMapper;
import com.example.demo.dao.UserinfoMapper;
import com.example.demo.pojo.College;
import com.example.demo.pojo.Goods;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Userinfo;
import com.example.demo.util.MD5;
@Service
public class CardServiceImpl implements ICardService{
	@Autowired	
	private UserinfoMapper userinfoMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private CollegeMapper collegeMapper;
	@Autowired
	private GoodsMapper goodsMapper;

	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.DEFAULT,readOnly=true)
	public Userinfo selectByNameAndPwd(String userLogin, String userPasswd) {
		return userinfoMapper.selectByNameAndPwd(userLogin, MD5.enctypeMD5("haha"+userPasswd));
	}

	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.DEFAULT,readOnly=true)
	public List<Student> listAllStudent() {
		return studentMapper.listAllStudent();
	}

	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.DEFAULT,readOnly=true)
	public Student queryBuStuId(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}

	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.DEFAULT,readOnly=true)
	public List<College> queryAllCollege() {
		return collegeMapper.queryAllCollege();
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public boolean updateStudent(Student stu) {
		return studentMapper.updateByPrimaryKeySelective(stu)>0;
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public boolean addStudent(Student stu) {
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		String monthStr=("0"+month).substring(month>=10?1:0);
		String prefix="STU"+year+monthStr;
		String maxStuNo=studentMapper.queryMaxStuNoByMonth(prefix+"%");
		if(maxStuNo==null){
			stu.setStuNo(prefix+"001");
		}else{
			String newNo = Integer.parseInt(maxStuNo.substring(maxStuNo.length()-3))+1+"";
			String stuNo = prefix+("00"+newNo).substring(newNo.length()-1);
			stu.setStuNo(stuNo);
		}
		stu.setStuPasswd(MD5.enctypeMD5("haha"+stu.getStuPasswd()));
		stu.setStuRemain(100);
		return studentMapper.insertSelective(stu)>0;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public boolean delStudent(Integer id) {
		return studentMapper.deleteByPrimaryKey(id)>0;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public boolean delAllStudent(Integer[] ids) {
		boolean flag=false;
		for(Integer id:ids) {
			flag=studentMapper.deleteByPrimaryKey(id)>0;
		}
		return flag;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public boolean updatePwd(Userinfo user, String neWPasswd) {
		user.setUserPasswd(MD5.enctypeMD5("haha"+neWPasswd));
		return userinfoMapper.updateByPrimaryKeySelective(user)>0;
	}

	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.DEFAULT,readOnly=true)
	public List<Student> queryByName(String stuName) {
		return studentMapper.queryByName("%"+stuName+"%");
	}
	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.DEFAULT,readOnly=true)
	public List<Goods> queryAllGoods() {
		return goodsMapper.queryAllGoods();
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public boolean addGoods(Goods goods) {
		return goodsMapper.insertSelective(goods)>0;
	}
	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.DEFAULT,readOnly=true)
	public Goods queryOneGoods(Integer id) {
		return goodsMapper.selectByPrimaryKey(id);
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public boolean updateGoods(Goods goods) {
		return goodsMapper.updateByPrimaryKeySelective(goods)>0;
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public boolean delGoods(Integer id) {
		return goodsMapper.deleteByPrimaryKey(id)>0;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public boolean delAllGoods(Integer[] ids) {
		boolean flag=false;
		for(Integer id:ids) {
			flag=goodsMapper.deleteByPrimaryKey(id)>0;
		}
		return flag;
	}
}
