package cn.itcast.dao;

import java.util.List;

import cn.itcast.po.Items;

public interface ItemsDao {

	//修改
	public void updateItems(Items items);
	
	//根据id 查
	public Items findItemsById(Integer id);
	
	//查询列表
	public List<Items> findItemsList() throws Exception;

}
