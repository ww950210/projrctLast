package com.example.demo.dao;

import java.util.List;

import com.example.demo.pojo.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer stuId);

    int insert(Student record);

    int insertSelective(Student record);
    
    List<Student> listAllStudent();
    
    List<Student> queryByName(String stuName);
    
    Student selectByPrimaryKey(Integer stuId);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    String queryMaxStuNoByMonth(String prefix);
    
    String getSameNameMaxName(String name);
    
    Student selectByNameAndPwd(String stuLogin,String stuPasswd);
}