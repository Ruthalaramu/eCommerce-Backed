package com.ecommerce.ecommerce_Backed.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Size {
    private String name;
    private int quantity;

    public Size(){

    }
    public Size(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
