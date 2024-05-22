package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.servicei.HomeServicei;

import jakarta.validation.Valid;
@RequestMapping("/product")
@RestController
public class HomeController {
@Autowired 
HomeServicei hs;
@PostMapping(value="/product",consumes= {"application/json","application/xml"})
public ResponseEntity<Product>saveProduct(@Valid @RequestBody Product product){
	if(product.getProductId()<=0) {
		return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
	}
	else {
		Product p = hs.saveProduct(product);
		return new ResponseEntity<Product>(p,HttpStatus.CREATED);
	}
	
}
@GetMapping(value = "/products",produces = {"application/json","application/xml"})
public ResponseEntity<List<Product>>getAllProduct(){
	List<Product> pList = hs.getAllProducts();
	return new ResponseEntity<List<Product>>(pList,HttpStatus.OK);

}
@GetMapping(value = "/product/{productName}",produces = {"application/json","application/xml"})
public ResponseEntity<Product>getProductByName(@PathVariable String productName){
	Product p = hs.getProductByName(productName);
	
	return new ResponseEntity<Product>(p,HttpStatus.OK);
}

@DeleteMapping(value = "/product/{productId}",consumes = {"application/xml","application/json"})
public ResponseEntity<String>deleteProductById(@PathVariable Integer productId){
	
	hs.deleteProductById(productId);
	return new ResponseEntity<String>("Sucess",HttpStatus.NO_CONTENT);
	
}
@PutMapping(value = "/product/{productId}",consumes = {"application/xml","application/json"},produces = {"application/json","application/xml"})
public ResponseEntity<Product>updateProduct(@RequestBody Product p, @PathVariable Integer productId){

	Product prod = hs.updateProduct(p,productId);
	if(prod!=null) {
		return new ResponseEntity<Product>(prod,HttpStatus.OK);
	}
	else {
		return new ResponseEntity<Product>(prod,HttpStatus.BAD_REQUEST);
	}
}



}
