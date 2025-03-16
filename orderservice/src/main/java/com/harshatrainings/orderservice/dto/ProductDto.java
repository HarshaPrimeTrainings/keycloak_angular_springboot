package com.harshatrainings.orderservice.dto;

public class ProductDto {
	
	private Integer pid;
	private String panme;
	private Integer price;
	
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPanme() {
		return panme;
	}
	public void setPanme(String panme) {
		this.panme = panme;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

}
