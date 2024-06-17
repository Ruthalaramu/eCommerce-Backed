package com.ecommerce.ecommerce_Backed.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserException extends Exception {
    public UserException(String name) {
       super(name);
    }
}

