package com.ecommerce.ecommerce_Backed.IMPL;

import com.ecommerce.ecommerce_Backed.DTO.AddemItemRequest;
import com.ecommerce.ecommerce_Backed.Exception.ProductException;
import com.ecommerce.ecommerce_Backed.Model.Cart;
import com.ecommerce.ecommerce_Backed.Model.User;

public interface CartServiceImpl {
    public Cart createCart(User user);
    public String AddCartItem (Long userId, AddemItemRequest addemItemRequest) throws ProductException;
    public Cart findUserCart(Long userId);
}
