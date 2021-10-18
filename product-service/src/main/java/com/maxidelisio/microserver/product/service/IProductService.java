package com.maxidelisio.microserver.product.service;

import java.util.List;

import com.maxidelisio.microserver.product.entity.Category;
import com.maxidelisio.microserver.product.entity.Product;

public interface IProductService {
	
	public List<Product> findAll();	
	public Product findById(Long id);	
	public Product createProduct(Product product);	
	public Product updateProduct(Product product);	
	public Product deleteProduct(Long id);	
	public List<Product> findByCategory(Category category);
	public Product updateStock(Long id, Double quantity);

}
