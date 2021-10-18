package com.maxidelisio.microserver.product.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxidelisio.microserver.product.entity.Category;
import com.maxidelisio.microserver.product.entity.Product;

public interface IProductDao extends JpaRepository<Product, Long>{
	
	public List<Product> findByCategory(Category category);

}
