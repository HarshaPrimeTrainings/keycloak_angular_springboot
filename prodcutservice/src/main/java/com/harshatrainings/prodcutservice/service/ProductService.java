package com.harshatrainings.prodcutservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.harshatrainings.prodcutservice.dao.Product;
import com.harshatrainings.prodcutservice.dao.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repo;
	
	
	
	public List<Product> getProducts(){
		return repo.findAll();
	}
	
	public Product finById(Integer pid) {
		return repo.findById(pid).orElseThrow(()->new RuntimeException("Prodcut Not Found "+ pid));
	}
	
	public Product saveProduct(Product p) {
		return repo.save(p);
	}
	
	
}
