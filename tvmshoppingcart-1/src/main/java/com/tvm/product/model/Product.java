package com.tvm.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
	private long productid;
	private String productname;
	private long productprice;
	private long productavilable;
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public long getProductprice() {
		return productprice;
	}
	public void setProductprice(long productprice) {
		this.productprice = productprice;
	}
	public long getProductavilable() {
		return productavilable;
	}
	public void setProductavilable(long productavilable) {
		this.productavilable = productavilable;
	}
	
}
