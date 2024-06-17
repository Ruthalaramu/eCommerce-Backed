package com.ecommerce.ecommerce_Backed.DTO;

import com.ecommerce.ecommerce_Backed.Model.Category;
import com.ecommerce.ecommerce_Backed.Model.Size;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateProductRequest {
    private Long id;
    private String title;
    private String description;
    private double price;
    private int discountPrice;
    private int discountPercent;
    private int quantity;
    private String brand;
    private String color;
    private String imageUrl;
    private Set<Size> size= new HashSet<>();
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
    public CreateProductRequest(){

     }

    public CreateProductRequest(Long id, String title, String description, double price, int discountPrice, int discountPercent, int quantity, String brand, String color, String imageUrl, Set<Size> size, String topLevelCategory, String secondLevelCategory, String thirdLevelCategory) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPrice = discountPrice;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.brand = brand;
        this.color = color;
        this.imageUrl = imageUrl;
        this.size = size;
        this.topLevelCategory = topLevelCategory;
        this.secondLevelCategory = secondLevelCategory;
        this.thirdLevelCategory = thirdLevelCategory;
    }
}
