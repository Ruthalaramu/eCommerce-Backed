package com.ecommerce.ecommerce_Backed.Repository;

import com.ecommerce.ecommerce_Backed.Exception.OrderException;
import com.ecommerce.ecommerce_Backed.Model.Address;
import com.ecommerce.ecommerce_Backed.Model.Order;
import com.ecommerce.ecommerce_Backed.Model.User;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderServiceImpl {
    public Order createOrder(User userId, Address shippingAddress) throws OrderException;

    public Order findOrderById(Long orderId) throws OrderException;
    public List<Order> userOrderedHistory(Long userId) throws OrderException;
    public Order placedOrder(Long orderId) throws OrderException;
    public Order confirmedOrder(Long orderId) throws OrderException;
    public Order shippedOrder(Long orderId) throws OrderException;
    public Order deliveredOrder (Long orderId) throws OrderException;
    public Order cancelOrder (Long orderId) throws OrderException;
    public List<Order> getAllOrder();
    public void deleteOrder(Long orderId)throws OrderException;


}
