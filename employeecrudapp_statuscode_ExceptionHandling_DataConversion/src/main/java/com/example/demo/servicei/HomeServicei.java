package com.example.demo.servicei;

import java.util.List;

import com.example.demo.model.Product;

public interface HomeServicei {

	public Product saveProduct(Product product);

	public List<Product> getAllProducts();

	public Product getProductByName(String productName);

	public void deleteProductById(Integer productId);

	public Product updateProduct(Product p, Integer productId);

}
