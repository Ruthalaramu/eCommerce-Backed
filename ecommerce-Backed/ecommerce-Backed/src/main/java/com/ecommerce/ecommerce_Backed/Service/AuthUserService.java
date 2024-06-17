package com.ecommerce.ecommerce_Backed.Service;

import com.ecommerce.ecommerce_Backed.JWTValidation.JwtUtil;
import com.ecommerce.ecommerce_Backed.Model.User;
import com.ecommerce.ecommerce_Backed.Repository.UserRepository;
import com.ecommerce.ecommerce_Backed.Response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {
    @Autowired
private  BCryptPasswordEncoder  passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<AuthResponse> createUser(User user) {
            User userBo= new User();
            if(user.getEmail()!=null && !user.getEmail().isEmpty()){
                userBo.setEmail(user.getEmail());}
            if(user.getName()!=null && !user.getName().isEmpty()){
                userBo.setName(user.getName());}
            if(user.getMobileNumber()!=null && !user.getMobileNumber().isEmpty()){
                userBo.setMobileNumber(user.getMobileNumber());}
            if(user.getPassword()!=null && !user.getPassword().isEmpty()){
                userBo.setPassword(passwordEncoder.encode(user.getPassword()));}
            if(user.getRole()!=null && !user.getRole().isEmpty()){
                userBo.setRole(user.getRole());}
            userBo.setIsActive(Boolean.TRUE);
             User savedUser=   userRepository.save(userBo);
            Authentication authentication= new UsernamePasswordAuthenticationToken(savedUser.getMobileNumber(),savedUser.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token=jwtUtil.generateToken(authentication);
            AuthResponse authResponse= new AuthResponse(token,"Signup Success");
            return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED) ;
    }
}
