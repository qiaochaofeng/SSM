package cn.itcast.service;

import java.util.List;

import cn.itcast.po.Items;

public interface ItemsService {

	//修改
	public void updateItems(Items items);
	
	//根据id 查询
	public Items findItemsById(Integer id);
	
	//查询列表
	public List<Items> findItemsList() throws Exception;


}
