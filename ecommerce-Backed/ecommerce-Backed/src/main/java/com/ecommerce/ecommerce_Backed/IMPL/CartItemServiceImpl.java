package com.ecommerce.ecommerce_Backed.IMPL;

import com.ecommerce.ecommerce_Backed.Exception.CartItemException;
import com.ecommerce.ecommerce_Backed.Exception.UserException;
import com.ecommerce.ecommerce_Backed.Model.Cart;
import com.ecommerce.ecommerce_Backed.Model.CartItem;
import com.ecommerce.ecommerce_Backed.Model.Product;

public interface CartItemServiceImpl  {
    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws Exception;

    public CartItem isCartItemExist(Cart cart, Product product,String size,Long userId) ;
    public  void removeCartItem(Long userId,Long cartItemId) throws Exception;
    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
