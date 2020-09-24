package com.example.demo.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.Userinfo;
import com.example.demo.pojo.vo.Password;
import com.example.demo.service.ICardService;
import com.example.demo.service.IFrontService;
import com.example.demo.util.MD5;

@RestController//@RestController=@Controller+@ResponseBody
@RequestMapping("/card")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class StuController {
	@Autowired
	private ICardService cardService;
	@Autowired
	private IFrontService frontService;
	@RequestMapping(path="/login",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean verifyLogin(@RequestBody Userinfo userinfo,HttpSession session) {
		boolean rt=false;
		Userinfo user=cardService.selectByNameAndPwd(userinfo.getUserLogin(), userinfo.getUserPasswd());
		if(user!=null) {
			session.setAttribute("user", user);
			rt=true;
		}
		return rt;
	}
	
	@RequestMapping(path="/userName",method = {RequestMethod.POST,RequestMethod.GET})
	public String getname(HttpSession session) {
		Userinfo user=(Userinfo)session.getAttribute("user");
		if(user!=null) {
			return user.getUserName();
		}
		return null;
	}
	
	@RequestMapping(path="/showStu",method = {RequestMethod.POST,RequestMethod.GET})
	public List<Student> ShowStu(HttpSession session) {
		return cardService.listAllStudent();
	}
	
	@RequestMapping(path="/oneStu",method = {RequestMethod.POST,RequestMethod.GET})
	public Student oneStu(Integer id) {
		return cardService.queryBuStuId(id);
	}
	
	@RequestMapping(path="/savestu",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean updateStudent(@RequestBody Student stu) {
		return cardService.updateStudent(stu);
	}
	@RequestMapping(path="/addstu",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean addStudent(@RequestBody Student stu) {
		return cardService.addStudent(stu);
	}
	
	@RequestMapping(path="/delStu",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean delStudent(Integer id) {
		return cardService.delStudent(id);
	}
	
	@RequestMapping(path="/delAllStu",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean delAllStudent(Integer[] ids) {
		return cardService.delAllStudent(ids);
	}
	
	@RequestMapping(path="/changePwd",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean changePwd(@RequestBody Password pwd,HttpSession session) {
		Userinfo user=(Userinfo)session.getAttribute("user");
		if(user.getUserPasswd().equals(MD5.enctypeMD5("haha"+pwd.getOldPasswd()))&&pwd.getNewPasswd().equals(pwd.getVerify())) {
			cardService.updatePwd(user,pwd.getNewPasswd());
			return true;
		}else {
			return false;
		}
	}
	
	@RequestMapping(path="/queryByName",method = {RequestMethod.POST,RequestMethod.GET})
	public List<Student> queryByName(String name) {
		return cardService.queryByName(name);
	}
	
	@RequestMapping(path="/frontlogin",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean frontLogin(@RequestBody Student stu,HttpSession session) {
		boolean rt=false;
		Student student=frontService.selectByNameAndPwd(stu.getStuLogin(), stu.getStuPasswd());
		if(student!=null) {
			session.setAttribute("student", student);
			rt=true;
		}
		return rt;
	}
	
	@RequestMapping(path="/stuName",method = {RequestMethod.POST,RequestMethod.GET})
	public String getstuName(HttpSession session) {
		Student stu=(Student)session.getAttribute("student");
		if(stu!=null) {
			return stu.getStuName();
		}
		return null;
	}
}
