package com.ecommerce.ecommerce_Backed.Repository;

import com.ecommerce.ecommerce_Backed.Model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {
//    @Query(value="select * from user where mobile_number=:mobileNumber and is_active=1",nativeQuery = true)
//    public User findByMobile(String email);

    @Query(value="select * from user where mobile_number=:mobileNumber and is_active=1",nativeQuery = true)
    UserDetails findByMobileNumber(String mobileNumber);
}
