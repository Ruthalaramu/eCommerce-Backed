package com.ecommerce.ecommerce_Backed.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
   private LocalDate createdDate;
   @Embedded
   @ElementCollection
   @Column(name = "size")
  private Set<Size> size= new HashSet<>();
   @ManyToOne
   @JoinColumn(name = "category_id")
    private Category category;

   public Product(){

   }
    public Product(Long id, String title, String description, double price, int discountPrice, int discountPercent, int quantity, String brand, String color, String imageUrl, Set<Size> size, Category category) {
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
        this.category = category;
    }
}
