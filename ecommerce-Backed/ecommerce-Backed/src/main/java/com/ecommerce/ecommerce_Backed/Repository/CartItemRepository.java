package com.ecommerce.ecommerce_Backed.Repository;

import com.ecommerce.ecommerce_Backed.Model.Cart;
import com.ecommerce.ecommerce_Backed.Model.CartItem;
import com.ecommerce.ecommerce_Backed.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Query(" select ci from CartItem ci where ci.cart=:cart and ci.product=:product and ci.size=:size and ci.userId=:userId")
    public CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product")Product product,
                                    @Param("size") String size,@Param("userId") Long userId);

}
