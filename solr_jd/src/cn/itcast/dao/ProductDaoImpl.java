package cn.itcast.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.itcast.pojo.ProductModel;
import cn.itcast.pojo.ResultModel;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private SolrServer server;
	
	@Override
	public ResultModel queryProduct(SolrQuery query) throws Exception {
		
		ResultModel resultModel = new ResultModel();
		List<ProductModel> productModelList= new ArrayList<ProductModel>();
		
		//执行查询
		QueryResponse response = server.query(query);
		
		//返回结果集
		SolrDocumentList results = response.getResults();
		
		//商品总数
		long numFound = results.getNumFound();
		resultModel.setRecordCount(numFound);
		
		for (SolrDocument solrDocument : results) {
			
			ProductModel productModel = new ProductModel();
			productModel.setPid(solrDocument.get("id").toString());//商品编号
			
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			
			String productName = "";
			List<String> list = highlighting.get(solrDocument.get("id")).get("product_name");
			if(list == null){
				productName = solrDocument.get("product_name").toString();
			}else{
				productName = list.get(0);
			}
			
			productModel.setName(productName);// 商品名称
			productModel.setPrice((float)solrDocument.get("product_price")); // 价格
			productModel.setCatalog_name(solrDocument.get("product_catalog_name").toString()); // 商品分类名称
			productModel.setPicture(solrDocument.get("product_picture").toString()); // 图片名称
			
			productModelList.add(productModel);
		}
		
		resultModel.setProductList(productModelList);
		
		
		return resultModel;
	}

}
