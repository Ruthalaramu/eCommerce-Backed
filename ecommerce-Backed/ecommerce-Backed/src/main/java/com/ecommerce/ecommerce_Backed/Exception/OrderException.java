package com.ecommerce.ecommerce_Backed.Exception;

import com.ecommerce.ecommerce_Backed.Model.Order;
import org.aspectj.weaver.ast.Or;

public class OrderException extends Exception{
    public OrderException (String message){
        super(message);
    }
}
