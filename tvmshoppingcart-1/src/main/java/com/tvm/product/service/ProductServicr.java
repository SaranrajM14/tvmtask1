package com.tvm.product.service;

import java.util.List;

import com.tvm.product.model.Product;


public interface ProductServicr  {
	List< Product > getAllProduct();
	void saveProduct(Product product);
	Product getProductId(long productid);
	Product getProductByproductname(String producttname);
    void deleteProductByproductId(long productid);
}
