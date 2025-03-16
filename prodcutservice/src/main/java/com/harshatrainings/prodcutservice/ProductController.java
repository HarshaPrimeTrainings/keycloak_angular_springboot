package com.harshatrainings.prodcutservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.harshatrainings.prodcutservice.dao.Product;
import com.harshatrainings.prodcutservice.dto.OrderDto;
import com.harshatrainings.prodcutservice.service.ProductService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@Autowired
	RestTemplate restemplate;
	
	@Autowired
	WebClient webclinet;
	
	@PostMapping("/webclient/order")
	public Mono<OrderDto> saveOrderwithWebclient(@RequestBody OrderDto order) {
				
		return webclinet.post().uri("http://localhost:7099/order/save")
		.bodyValue(order)
		.retrieve()
		.bodyToMono(OrderDto.class);
	} 
	
	
	@GetMapping("/orders")
	public List listOrder() {
		return restemplate.getForObject("http://localhost:7099/order/list",List.class);
		
	}
	
	@PostMapping("/order")
	public OrderDto saveOrder(@RequestBody OrderDto order) {
		return restemplate.postForObject("http://localhost:7099/order/save",order, OrderDto.class);
		
	}

	@GetMapping("/list")
	public List<Product> getAllProdcuts(){
		return service.getProducts();
	}
	
	@GetMapping("/{id}")
	public Product getProductbyId(@PathVariable Integer id) {
		return service.finById(id);
	}
	
	@PostMapping("/save")
	public Product saveProduct(@RequestBody Product p) {
		return service.saveProduct(p);
		
	}
	
}
