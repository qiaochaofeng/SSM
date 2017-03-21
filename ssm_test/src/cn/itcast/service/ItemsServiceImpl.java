package cn.itcast.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.ItemsDao;
import cn.itcast.po.Items;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {

	@Resource
	private ItemsDao itemsDao;

	//修改
	@Override
	public void updateItems(Items items) {
		itemsDao.updateItems(items);
	}
	
	//根据id 查询
	@Override
	public Items findItemsById(Integer id) {
		return itemsDao.findItemsById(id);
	}
	
	//列表
	@Override
	public List<Items> findItemsList() throws Exception{
//		Integer i = 1 / 0;
		return itemsDao.findItemsList();
	}

}
