package com.harshatrainings.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshatrainings.orderservice.dao.OrderRepository;
import com.harshatrainings.orderservice.dao.Orders;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderRepository repo;
	
	@GetMapping("/list")
	public List<Orders> getALlOrder(){
		return repo.findAll();
	}
	
	@GetMapping("/{oid}")
	public Orders getOrder(@PathVariable Integer oid) {
		return repo.findById(oid).orElseThrow(()->new RuntimeException("No order Placed with "+ oid));
		
	}
	
	@PostMapping("/save")
	public Orders saveOrder(@RequestBody Orders order) {
		return repo.save(order);
		
	}
	
}
