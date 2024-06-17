package com.ecommerce.ecommerce_Backed.Service;

import com.ecommerce.ecommerce_Backed.DTO.AddemItemRequest;
import com.ecommerce.ecommerce_Backed.Exception.ProductException;
import com.ecommerce.ecommerce_Backed.IMPL.CartItemServiceImpl;
import com.ecommerce.ecommerce_Backed.IMPL.CartServiceImpl;
import com.ecommerce.ecommerce_Backed.IMPL.ProductServiceImpl;
import com.ecommerce.ecommerce_Backed.Model.Cart;
import com.ecommerce.ecommerce_Backed.Model.CartItem;
import com.ecommerce.ecommerce_Backed.Model.Product;
import com.ecommerce.ecommerce_Backed.Model.User;
import com.ecommerce.ecommerce_Backed.Repository.CartItemRepository;
import com.ecommerce.ecommerce_Backed.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class CartService implements CartServiceImpl {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CartItemServiceImpl cartItemService;

    @Override
    public Cart createCart(User user) {
        Cart cart= new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String AddCartItem(Long userId, AddemItemRequest req) throws ProductException {
        Cart cart=cartRepository.findByUserId(userId);
        Product product=productService.findProductById(req.getProductId());
        CartItem isPresent=cartItemService.isCartItemExist(cart,product, req.getSize(),userId);
        if(isPresent==null){
            CartItem cartItem=new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(String.valueOf(userId));
            int price=req.getQuantity()*product.getDiscountPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());
            CartItem createdCartItem=cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }
        return "item Add to Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart  cart=cartRepository.findByUserId(userId);
        int totalPrice=0;
        int totalDiscountedPrice=0;
        int totalItem=0;
        for (CartItem cartItem:cart.getCartItems()) {
            totalPrice+=cartItem.getPrice();
            totalDiscountedPrice+=cartItem.getDiscountedPrice();
            totalItem+=cartItem.getQuantity();
        }
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setDiscount(totalPrice-totalDiscountedPrice);
        return cartRepository.save(cart);
    }
}
