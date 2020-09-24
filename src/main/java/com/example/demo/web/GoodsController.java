package com.example.demo.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.demo.pojo.Goods;
import com.example.demo.pojo.ShoppingCart;
import com.example.demo.pojo.Student;
import com.example.demo.service.ICardService;
import com.example.demo.service.IFrontService;
import com.example.demo.util.AlipayConfig;

import io.minio.MinioClient;

@RestController//@RestController=@Controller+@ResponseBody
@RequestMapping("/goods")
@CrossOrigin(allowCredentials="true", allowedHeaders="*")
public class GoodsController {
	@Autowired
	private ICardService cardService;
	
	@Autowired
	private IFrontService frontService;
	
	@RequestMapping(path="/showAll",method = {RequestMethod.POST,RequestMethod.GET})
	public List<Goods> ShowGoods(HttpSession session) {
		return cardService.queryAllGoods();
	}
	
	@RequestMapping(path="/addGoods",method = {RequestMethod.POST,RequestMethod.GET})
	public String addGoods(MultipartFile file,Goods goods) throws Exception{
		if (file==null || file.getSize() == 0) {
			return "文件为空";
		}else if ("".equals(goods.getGoodName())) {
			return "名字为空";
		}else if ("".equals(goods.getGoodNo())) {
			return "编号为空";
		}else if ("".equals(goods.getGoodPrice())) {
			return "价格为空";
		}else if ("".equals(goods.getGoodNum().toString())) {
			return "库存为空";
		}else {
			try {
				MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "minioadmin", "minioadmin"); // 连接
				if (!minioClient.bucketExists("goods")) { // 是否存在名为“test”的bucket
					minioClient.makeBucket("goods");
				}
				String fileName = file.getOriginalFilename();
				String newName = UUID.randomUUID().toString().replaceAll("-", "")+fileName.substring(fileName.lastIndexOf("."));
				InputStream inputStream = file.getInputStream(); // 获取file的inputStream
				//image/png
				minioClient.putObject("goods", newName, inputStream,"image/png"); // 上传
				//minioClient.putObject("goods", newName, inputStream, "application/octet-stream"); // 上传
				inputStream.close();
				String url = minioClient.getObjectUrl("goods", newName); // 文件访问路径
				goods.setGoodImg(url);
				cardService.addGoods(goods);
				return "添加成功";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "上传失败";
		}
		
	}
	
	@RequestMapping(path="/oneGoods",method = {RequestMethod.POST,RequestMethod.GET})
	public Goods oneGoods(Integer id) {
		return cardService.queryOneGoods(id);
	}
	@RequestMapping(path="/updataGoods",method = {RequestMethod.POST,RequestMethod.GET})
	public String updateGoods(MultipartFile file,Goods goods) throws Exception{
		if (file==null||file.getSize()==0) {
			cardService.updateGoods(goods);
			return "修改成功";
		}else {
			try {
				MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "minioadmin", "minioadmin"); // 连接
				if (!minioClient.bucketExists("goods")) { // 是否存在名为“test”的bucket
					minioClient.makeBucket("goods");
				}
				String fileName = file.getOriginalFilename();
				String newName = UUID.randomUUID().toString().replaceAll("-", "")+fileName.substring(fileName.lastIndexOf("."));
				InputStream inputStream = file.getInputStream(); // 获取file的inputStream
				//image/png
				minioClient.putObject("goods", newName, inputStream,"image/png"); // 上传
				//minioClient.putObject("goods", newName, inputStream, "application/octet-stream"); // 上传
				inputStream.close();
				String url = minioClient.getObjectUrl("goods", newName); // 文件访问路径
				goods.setGoodImg(url);
				cardService.updateGoods(goods);
				return "修改成功";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "修改失败";
	}
	@RequestMapping(path="/delGoods",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean delGoods(Integer id) {
		return cardService.delGoods(id);
	}
	@RequestMapping(path="/delAllGoods",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean delAllGoods(Integer[] ids) {
		return cardService.delAllGoods(ids);
	}
	
	@RequestMapping(path="/allCart",method = {RequestMethod.POST,RequestMethod.GET})
	public List<ShoppingCart> showAllCart(HttpSession session) {
		List<ShoppingCart> list=new ArrayList<>();
		Student stu=(Student)session.getAttribute("student");
		list=frontService.selectByStuId(stu.getStuId());
		return list;
	}
	@RequestMapping(path="/addCart",method = {RequestMethod.POST,RequestMethod.GET})
	public boolean addCart(HttpSession session,@RequestBody ShoppingCart cart) {
		boolean rt=false;
		Student stu=(Student)session.getAttribute("student");
		if(stu!=null) {
			if(frontService.selectNum(stu.getStuId(), cart.getGoodId())!=null) {
				cart.setStuId(stu.getStuId());
				rt=frontService.updateNum(cart);
			}else {
				cart.setStuId(stu.getStuId());
				rt=frontService.addCart(cart);
			}
		}else {
			rt=false;
		}
		return rt;
	}
	
	@RequestMapping(path="/delSomeCart",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean delSomeCart(Integer[] ids,HttpSession session) {
		boolean rt=false;
		Student stu=(Student)session.getAttribute("student");
		if(stu!=null) {
			rt=frontService.delSomeCart(ids,stu.getStuId());
		}
		return rt;
	}
	
	@RequestMapping(path="/delAllCart",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean delAllCart(HttpSession session) {
		boolean rt=false;
		Student stu=(Student)session.getAttribute("student");
		if(stu!=null) {
			rt=frontService.deleteByStuId(stu.getStuId());
		}
		return rt;
	}
	
	@RequestMapping(path="/delCart",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean delCart(HttpSession session,Integer id) {
		boolean rt=false;
		Student stu=(Student)session.getAttribute("student");
		if(stu!=null) {
			rt=frontService.delOneCart(id, stu.getStuId());
		}
		return rt;
	}
	@RequestMapping(path="/updateNum",method = {RequestMethod.POST,RequestMethod.GET})
	public Boolean updateNum(HttpSession session,Integer id,Integer num) {
		boolean rt=false;
		Student stu=(Student)session.getAttribute("student");
		if(stu!=null) {
			if(num==0) {
				rt=frontService.delOneCart(id, stu.getStuId());
			}else {
				rt=frontService.updateCartNum(stu.getStuId(),id,num);
			}
		}
		return rt;
	}
	@RequestMapping(path="/showAlipay",method = {RequestMethod.POST,RequestMethod.GET})
	public String showAlipay(Integer[] ids,Integer allmoney,HttpSession session) throws Exception{
		Student stu=(Student)session.getAttribute("student");
		String result = null;
		if(stu!=null) {
			for(Integer id:ids) {
				frontService.delOneCart(id, stu.getStuId());
			}
			String out_trade_no=UUID.randomUUID()+""+stu.getStuId();
			String total_amount=allmoney+"";
			String subject=UUID.randomUUID()+""+stu.getStuName();
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			//设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			//商品描述，可空
			String body =null;
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
					+ "\"total_amount\":\""+ total_amount +"\"," 
					+ "\"subject\":\""+ subject +"\"," 
					+ "\"body\":\""+ body +"\"," 
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
	        result=alipayClient.pageExecute(alipayRequest).getBody();
		}
		return result;
	}
}