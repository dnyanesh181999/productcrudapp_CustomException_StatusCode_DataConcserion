package com.example.demo.HomeServieimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoDataPresentException;
import com.example.demo.exception.NoProductFoundException;
import com.example.demo.exception.ProductNotSavedException;
import com.example.demo.model.Product;
import com.example.demo.repository.HomeRepository;
import com.example.demo.servicei.HomeServicei;
@Service
public class HomeServiceimpl implements HomeServicei{
@Autowired
HomeRepository hr;

@Override
public Product saveProduct(Product product) {
	Product p=hr.save(product);
	if(p!=null) {
		return p;
	}
	else {
		throw new ProductNotSavedException("Product not saved");
	}
	
	
}

@Override
public List<Product> getAllProducts() {
	List<Product>pList = (List<Product>)hr.findAll();
	
	if(pList.isEmpty()) {
		throw new NoDataPresentException("Not any data present");
	}
	else {
		return pList;
	}
	
}

@Override
public Product getProductByName(String productName) {
	Product p = hr.findByProductName(productName);
	if(p!=null) {
		return p;
	}
	else {
		throw new NoProductFoundException("Product Not Available");
	}
	
}

@Override
public void deleteProductById(Integer productId) {
	Optional<Product> opt=hr.findById(productId);
	if(opt.isPresent()) {
		hr.delete(opt.get());
	}
	else {
		throw new NoDataPresentException("Not Any Data persent with ID :"+productId);
	}
	
}

@Override
public Product updateProduct(Product p, Integer productId) {
	Optional<Product>opt=hr.findById(productId);
	if(opt.isPresent()) {
		Product pr=opt.get();
		if(p.getProductId()!=null) {
			pr.setProductId(p.getProductId());
		}
		if(p.getProductName()!=null) {
			pr.setProductName(p.getProductName());
		}
		if(p.getProductPrice()!=null) {
			pr.setProductPrice(p.getProductPrice());
		}
		if(p.getProductType()!=null) {
			pr.setProductType(p.getProductType());
		}
		Product pp=hr.save(pr);
		return pp;
	}
	else {
		throw new NoDataPresentException("NO ANY DATA PRESENT");
	}
}
}
