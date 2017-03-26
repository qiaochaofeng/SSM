package cn.itcast.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.pojo.ResultModel;
import cn.itcast.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="list.action")
	public String queryProduct(String queryString, String catalog_name, String price, String sort, Integer page, Model model) throws Exception{
		
		ResultModel resultModel = productService.queryProduct(queryString, catalog_name, price, sort, page);
	
		model.addAttribute("result", resultModel);		
		// 回显查询条件
		model.addAttribute("queryString", queryString);
		model.addAttribute("catalog_name", catalog_name);
		model.addAttribute("price", price);
		model.addAttribute("sort", sort);

	return "product_list";
	}
	
	//转到首页
	@RequestMapping(value="product.action")
	public String toProductListPage(){
		return "product_list";
	}
	
}
