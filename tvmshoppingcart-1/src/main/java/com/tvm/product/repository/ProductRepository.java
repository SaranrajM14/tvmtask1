package com.tvm.product.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tvm.product.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product ,Long >{
	@Query("SELECT p FROM Product p WHERE p.productname LIKE %?1%")
	public Product findByProductname(String productname);
}
