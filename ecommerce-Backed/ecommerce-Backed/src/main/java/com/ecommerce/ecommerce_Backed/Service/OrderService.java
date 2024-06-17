package com.ecommerce.ecommerce_Backed.Service;

import com.ecommerce.ecommerce_Backed.Exception.OrderException;
import com.ecommerce.ecommerce_Backed.Model.Address;
import com.ecommerce.ecommerce_Backed.Model.Order;
import com.ecommerce.ecommerce_Backed.Model.User;
import com.ecommerce.ecommerce_Backed.Repository.CartItemRepository;
import com.ecommerce.ecommerce_Backed.Repository.CartRepository;
import com.ecommerce.ecommerce_Backed.Repository.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService implements OrderServiceImpl {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;


    @Override
    public Order createOrder(User userId, Address shippingAddress) throws OrderException {
        return null;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> userOrderedHistory(Long userId) throws OrderException {
        return null;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {

    }
}
