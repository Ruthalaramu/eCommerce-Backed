package com.ecommerce.ecommerce_Backed.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddemItemRequest {

    private Long productId;
    private String size;
    private int quantity;
    private Integer price;

    public AddemItemRequest() {
    }

    public AddemItemRequest(Long productId, String size, int quantity, Integer price) {
        this.productId = productId;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }
}
