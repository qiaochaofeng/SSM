package cn.itcast.service;

import cn.itcast.pojo.ResultModel;

public interface ProductService {

	public ResultModel queryProduct(String queryString, String catalog_name, String price, String sort, Integer page) throws Exception;
}
