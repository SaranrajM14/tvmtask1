package com.tvm.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvm.product.model.Product;
import com.tvm.product.repository.ProductRepository;

@Service
public class ProductserviceImpl implements ProductServicr {
	@Autowired
	private ProductRepository productRepository;
	@Override
	public List<Product> getAllProduct() {
		
		return productRepository.findAll();
	}

	@Override
	public void saveProduct(Product product) {
		
		this.productRepository.save(product);
	}

	@Override
	public Product getProductId(long productid) {
		 Optional < Product > optional = productRepository.findById(productid);
	        Product product = null;
	        if (optional.isPresent()) {
	           product = optional.get();
	        } else {
	            throw new RuntimeException(" Product not found for productid :: " + productid);
	        }
	        return product;
	}

	@Override
	public Product getProductByproductname(String productname) {
        Product product = productRepository.findByProductname(productname);
        
        return product;
	}

	@Override
	public void deleteProductByproductId(long productid) {
		this.productRepository.deleteById(productid);
		
	}

}
