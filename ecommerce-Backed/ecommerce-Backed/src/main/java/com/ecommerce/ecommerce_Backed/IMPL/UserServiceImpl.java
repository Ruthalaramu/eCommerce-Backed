package com.ecommerce.ecommerce_Backed.IMPL;

import com.ecommerce.ecommerce_Backed.Model.User;

public interface UserServiceImpl {
    public User findUserById(Long id) throws Exception;
    public User findUserProfileByJwt(String jwt);

}
