package com.ecommerce.ecommerce_Backed.Controller;

import com.ecommerce.ecommerce_Backed.DTO.LoginRequest;
import com.ecommerce.ecommerce_Backed.JWTValidation.JwtUtil;
import com.ecommerce.ecommerce_Backed.Model.User;
import com.ecommerce.ecommerce_Backed.Repository.UserRepository;
import com.ecommerce.ecommerce_Backed.Response.AuthResponse;
import com.ecommerce.ecommerce_Backed.Service.AuthUserService;
import com.ecommerce.ecommerce_Backed.Service.CustomUserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
     private   CustomUserServiceImplementation customUserServiceImplementation;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/signUp")
    public ResponseEntity<AuthResponse> createUser(@RequestBody User user) throws Exception{
        try {
            UserDetails userObj=userRepository.findByMobileNumber(user.getMobileNumber());
            if(userObj==null){
                return authUserService.createUser(user);
            }else{
                AuthResponse authResponse=new AuthResponse();
                authResponse.setMessage("User already register with us");
                authResponse.setJwt("null");
               return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;

        }
    }
    @GetMapping("/signIn")
    public ResponseEntity<AuthResponse> loinUser(@RequestBody LoginRequest user) throws Exception{
        try {
            Authentication authentication=  authenticate(user.getMobileNumber(),user.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token=jwtUtil.generateToken(authentication);
            AuthResponse authResponse=new AuthResponse();
            authResponse.setJwt(token);
            authResponse.setMessage("SignIn Success");
            return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }

    private Authentication authenticate(String mobileNumber, String password) throws Exception {
        try {
            UserDetails userDetails= customUserServiceImplementation.loadUserByUsername(mobileNumber);
            if(mobileNumber==null){
                throw  new BadCredentialsException("Account not Found");
            }
            if(!passwordEncoder.matches(userDetails.getPassword(),password)){
                throw  new BadCredentialsException("Wrong password");
            }
            return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
