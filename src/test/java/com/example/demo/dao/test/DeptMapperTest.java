package com.example.demo.dao.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.UserinfoMapper;
import com.example.demo.pojo.ShoppingCart;
import com.example.demo.service.IFrontService;

@SpringBootTest
public class DeptMapperTest {
	@Autowired
	private UserinfoMapper userinfoMapper;
	@Autowired
	private IFrontService frontService;
	@Test
	public void testSelectByPrimaryKey() throws Exception{
		ShoppingCart cart=new ShoppingCart();
		cart.setGoodId(1);
		cart.setStuId(6);
		boolean rt=frontService.updateNum(cart);
		System.out.println(rt);
	}
}
