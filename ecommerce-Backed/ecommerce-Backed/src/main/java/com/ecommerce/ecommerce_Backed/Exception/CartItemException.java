package com.ecommerce.ecommerce_Backed.Exception;

import com.ecommerce.ecommerce_Backed.Repository.CartItemRepository;

public class CartItemException extends  Exception{
    public CartItemException (String message){
        super(message);
    }
}
