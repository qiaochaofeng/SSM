package cn.itcast.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.itcast.exception.CustomException;
import cn.itcast.po.Items;
import cn.itcast.po.User;
import cn.itcast.queryvo.QueryVo;
import cn.itcast.service.ItemsService;

@Controller
@RequestMapping(value="item/") //窄化请求映射
public class ItemsController {

	@Resource
	private ItemsService itemsService;
	
	//登入
	@RequestMapping(value="login.action")
	public String login(){
		return "login";
	}
	
	//验证
	@RequestMapping(value="submit.action")
	public String submit(HttpSession session,User user){
		session.setAttribute("user", user);
		return "forward:itemsList.action";
	}
	
	//restful
	@RequestMapping(value="viewItems/{id}")
	/*public @ResponseBody Items viewItems(@PathVariable("id") String id,Model model){
		Items items = itemsService.findItemsById(Integer.valueOf(id));
		return items;
	}*/
	public ModelAndView viewItems(@PathVariable Integer id){
		Items items = itemsService.findItemsById(id);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("item", items);
		mv.setViewName("editItem");
		return mv;
	}
	
	//JSON数据交互
	@RequestMapping(value="editItemsWithJSONType.action")
	public @ResponseBody Items editItemsWithJSONType(@RequestBody Items items){
		items.setName("json格式");
		return items;
	}
	
	//返回值是  void
	@RequestMapping(value="testAjax.action")
	public void testAjax(Items items,HttpServletRequest request,HttpServletResponse response){
		 
		try {
			items.setName("xiaomi");
			response.getWriter().write(items.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//转发
	@RequestMapping(value="toEditPage.action")
	public String toEditPage(Integer id){
		
		return "forward:itemEdit.action";
	}
	
	//重定向
	@RequestMapping(value="updateItem.action")
	public String updateItems(Items items,MultipartFile pictureFile){
		
		String name = pictureFile.getOriginalFilename();
		
		itemsService.updateItems(items);
		
		return "redirect:itemsList.action";
	}
	
	//返回 string 类型
	@RequestMapping(value="itemEdit.action")
	public String getItemsById(Integer id,Model model) throws CustomException{
		
		Items items = itemsService.findItemsById(id);
		if(items == null){
			throw new CustomException("没有该商品");
		}
		model.addAttribute("item", items);
		
		return "editItem";
	}
	
	//传递参数为 List集合 ( 需要将 List 放到包装类中)
	@RequestMapping(value="updateItemsList.action")
	public ModelAndView updateItems(QueryVo queryVo, Integer[] ids){
		
		return null;
	}
	
	//传递参数为 数组
	@RequestMapping(value="updateItemsArray.action")
	public ModelAndView updateItems(Integer[] ids){
		
		return null;
	}
	
	//查询items列表
	//@RequestMapping(value={"itemsList.action","itemsList2.action","itemsList3.action"}) //URL 路径映射
	@RequestMapping(value="itemsList.action",method={RequestMethod.POST,RequestMethod.GET})  //请求方式限定
	public ModelAndView getItemsList() throws Exception{
		
		List<Items> itemsList = itemsService.findItemsList();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", itemsList);
		modelAndView.setViewName("itemList");
		
		return modelAndView;
	}
}
