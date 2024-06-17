package com.ecommerce.ecommerce_Backed.Service;

import com.ecommerce.ecommerce_Backed.Exception.UserException;
import com.ecommerce.ecommerce_Backed.IMPL.UserServiceImpl;
import com.ecommerce.ecommerce_Backed.JWTValidation.JwtUtil;
import com.ecommerce.ecommerce_Backed.Model.User;
import com.ecommerce.ecommerce_Backed.Repository.UserRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.swing.text.Utilities;
import java.util.Optional;

@Service
public class UserService  implements UserServiceImpl {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public User findUserById(Long id) throws Exception{
        try {
            Optional<User> user= userRepository.findById(id);
            if(user.isPresent()){
                return (user.get());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
        throw new UserException("User not found with us "+id);
    }

    @Override
    public User findUserProfileByJwt(String jwt) {
        String mobileNumber=jwtUtil.extractMobileNumFromToken(jwt);
        User user= (User) userRepository.findByMobileNumber(mobileNumber);
        return user;
    }
}
