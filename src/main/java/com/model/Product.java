package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Product")
public class Product {

	
	/*create table product (
			pid INT,
			Product_name VARCHAR(50),
			price VARCHAR(50)
		  PRIMARY KEY (pid)
		);*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long pid;
	private String product_name;
	private String price;
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
