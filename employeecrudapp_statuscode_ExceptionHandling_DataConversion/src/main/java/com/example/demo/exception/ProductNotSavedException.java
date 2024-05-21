package com.example.demo.exception;

public class ProductNotSavedException extends RuntimeException {

	public ProductNotSavedException(String msg) {
		super(msg);
	}
}
