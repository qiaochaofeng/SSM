package cn.itcast.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.itcast.dao.ProductDao;
import cn.itcast.pojo.ResultModel;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	private static final Integer SIZE = 8;

	@Override
	public ResultModel queryProduct(String queryString, String catalog_name, String price, String sort, Integer page)
			throws Exception {
		
		
//		// 总页数
//		private Long pageCount;
//		// 当前页
//		private long curPage;
		
		//solr 查询对象
		SolrQuery query = new SolrQuery();
		
		//设置搜索条件
		if(null != queryString && "" != queryString){
			query.setQuery(queryString);
		}else{
			query.setQuery("*:*");
		}
		//设置筛选条件
		if(null != catalog_name && "" != catalog_name){
			query.addFilterQuery("product_catalog_name:"+catalog_name);
		}
		//价格
		if(null != price && "" != price ){
			String[] split = price.split("-");
			query.addFilterQuery("product_price:["+split[0]+" TO "+split[1]+"]");
		}
		
		//设置默认搜索域
		query.set("df", "product_name");
		
		//设置排序
		if(null != sort && "" != sort){
			query.setSort("product_price", "1".equals(sort) ? ORDER.asc : ORDER.desc);
		}
		
		//设置分页
		if(page == null){
			page = 1;
		}
		query.setStart((page - 1) * SIZE);
		query.setRows(SIZE);
		
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<font color = 'red'>");
		query.setHighlightSimplePost("</font>");
		
		ResultModel resultModel = productDao.queryProduct(query);
		resultModel.setCurPage(page);
		// 总页数
		Long recordCount = resultModel.getRecordCount();
		Long pageCount = recordCount / SIZE;
		if(recordCount % SIZE > 0){
			pageCount++;
		}
		resultModel.setPageCount(pageCount);
		
		return resultModel;
	}

}
