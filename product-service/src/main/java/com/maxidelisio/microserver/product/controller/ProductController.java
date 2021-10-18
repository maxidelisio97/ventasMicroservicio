package com.maxidelisio.microserver.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxidelisio.microserver.product.dao.IProductDao;
import com.maxidelisio.microserver.product.entity.Category;
import com.maxidelisio.microserver.product.entity.Product;
import com.maxidelisio.microserver.product.service.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@GetMapping(value = "/get")
	public ResponseEntity<List<Product>> listProduct(@RequestParam(name="categoryId",required = false) Long categoryId){
		
		List<Product> list = new ArrayList<>();
		
		if(categoryId == null) {
			list = productService.findAll();
			if(list.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}else {
			list = productService.findByCategory(Category.builder().id(categoryId).build());
			if(list.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
		}		
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id){
		
		Product product = productService.findById(id);
		if(product == null) {
			ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(product);
		
	}
	
	@RequestMapping(path = "/register",method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product,BindingResult result){
		
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
		}
		Product productDB = productService.createProduct(product);
		
		if(productDB == null) {
			ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(productDB);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product){
		product.setId(id);
		Product productDB = productService.updateProduct(product);
		if(productDB == null) {
			ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(productDB);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
		
		Product productDeleted = productService.deleteProduct(id);
		if(productDeleted == null) {
			ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(productDeleted);
	}
	
	@PutMapping(value = "/stock/{id}")
	public ResponseEntity<Product> updateStockProduct(@PathVariable Long id,@RequestParam(value = "quantity" ,required = true )Double quantity){
		
		Product productDB = productService.updateStock(id, quantity);
		if(productDB == null) {
			ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(productDB);
	}
	
	//OBTIENE LOS ERRORES Y LOS FORMATEA
	public String formatMessage(BindingResult result) {
		
		List<Map<String,String>> errors = result.getFieldErrors().stream()
				.map(err ->{
					Map<String,String> error = new HashMap<>();
					error.put(err.getField(), err.getDefaultMessage());
					return error;
					
				}).collect(Collectors.toList());
		
		ErrorMessage errorMessage = ErrorMessage.builder()
				.code("01")
				.error(errors).build();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString="";
		try {
			jsonString = mapper.writeValueAsString(errorMessage);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}

}
