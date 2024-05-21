package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@XmlRootElement
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer productId;
private String productName;
private String productType;
private Double productPrice;
}
