package com.example.demo.dao;

import java.util.List;

import com.example.demo.pojo.College;

public interface CollegeMapper {
    int deleteByPrimaryKey(Integer collegeId);

    int insert(College record);

    int insertSelective(College record);

    College selectByPrimaryKey(Integer collegeId);
    
    List<College> queryAllCollege();

    int updateByPrimaryKeySelective(College record);

    int updateByPrimaryKey(College record);
}