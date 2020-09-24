package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.College;
import com.example.demo.service.ICardService;

@RestController
@RequestMapping("/college")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class CollegeController {
	
	@Autowired
	private ICardService cardService;
	
	@RequestMapping(path="/show",method = {RequestMethod.GET,RequestMethod.POST})
	public List<College> queryAllCollege() {
		return cardService.queryAllCollege();
	}
}
