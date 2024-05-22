package com.example.demo.model;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
@NotEmpty()
@Size(min = 4,message = "Minimum 4 charector")
private String productName;
@NotEmpty(message = "Not empty")
private String productType;
@NotNull(message = "NOT BE Null")
private Double productPrice;
}
