package com.harshatrainings.prodcutservice.dto;

import java.util.List;

public class OrderDto {

	private Integer oid;
	private Integer ordertoal;
	private List<Integer> products;
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public Integer getOrdertoal() {
		return ordertoal;
	}
	public void setOrdertoal(Integer ordertoal) {
		this.ordertoal = ordertoal;
	}
	public List<Integer> getProducts() {
		return products;
	}
	public void setProducts(List<Integer> products) {
		this.products = products;
	}
	
	
}
