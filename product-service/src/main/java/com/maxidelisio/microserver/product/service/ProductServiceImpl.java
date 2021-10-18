package com.maxidelisio.microserver.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxidelisio.microserver.product.dao.IProductDao;
import com.maxidelisio.microserver.product.entity.Category;
import com.maxidelisio.microserver.product.entity.Product;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private IProductDao productoDao;

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productoDao.findAll();
	}

	@Override
	public Product findById(Long id) {
		// TODO Auto-generated method stub
		return productoDao.findById(id).orElse(null);
	}

	@Override
	public Product createProduct(Product product) {
		product.setStatus("CREATED");
		product.setCreateAt(new Date());
		
		return productoDao.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		Product productDB = findById(product.getId());
		if(productDB == null) {
			return null;
		}
		productDB.setName(product.getName());
		productDB.setDescription(product.getDescription());
		productDB.setCategory(product.getCategory());
		productDB.setPrice(product.getPrice());
		
		return productoDao.save(productDB);
	}

	@Override
	public Product deleteProduct(Long id) {
		Product productDB = findById(id);
		if(productDB == null) {
			return null;
		}
		
		productDB.setStatus("DELETED");
		return productoDao.save(productDB);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		// TODO Auto-generated method stub
		return productoDao.findByCategory(category);
	}

	@Override
	public Product updateStock(Long id, Double quantity) {
		// TODO Auto-generated method stub
		Product productDB = findById(id);
		if(productDB == null) {
			return null;
		}
		Double stock = productDB.getStock() + quantity;
		productDB.setStock(stock);
		return productoDao.save(productDB);
	}

}
