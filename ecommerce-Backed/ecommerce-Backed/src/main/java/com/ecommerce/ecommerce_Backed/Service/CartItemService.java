package com.ecommerce.ecommerce_Backed.Service;

import com.ecommerce.ecommerce_Backed.DTO.AddemItemRequest;
import com.ecommerce.ecommerce_Backed.Exception.CartItemException;
import com.ecommerce.ecommerce_Backed.Exception.UserException;
import com.ecommerce.ecommerce_Backed.IMPL.CartItemServiceImpl;
import com.ecommerce.ecommerce_Backed.IMPL.CartServiceImpl;
import com.ecommerce.ecommerce_Backed.Model.Cart;
import com.ecommerce.ecommerce_Backed.Model.CartItem;
import com.ecommerce.ecommerce_Backed.Model.Product;
import com.ecommerce.ecommerce_Backed.Model.User;
import com.ecommerce.ecommerce_Backed.Repository.CartItemRepository;
import com.ecommerce.ecommerce_Backed.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService implements CartItemServiceImpl {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice((int) (cartItem.getProduct().getPrice()*cartItem.getQuantity()));
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountPrice()*cartItem.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
        CartItem item=findCartItemById(id);
        User user=userService.findUserById(Long.valueOf(item.getUserId()));
        if(user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice((int) (item.getQuantity()*item.getProduct().getPrice()));
            item.setDiscountedPrice(item.getProduct().getDiscountPrice()*item.getQuantity());
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return cartItemRepository.isCartItemExist(cart,product,size,userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItem cartItem=findCartItemById(cartItemId);
        User user=userService.findUserById(Long.valueOf(cartItem.getUserId()));
        User reqUser=userService.findUserById(userId);
        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        }else{
            throw new UserException("you can't remove another users id");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt=cartItemRepository.findById(cartItemId);
        if(opt.isPresent()){
            return opt.get();
        }else{
           throw new CartItemException("CartItem Not Found with this User") ;
        }

    }
}
