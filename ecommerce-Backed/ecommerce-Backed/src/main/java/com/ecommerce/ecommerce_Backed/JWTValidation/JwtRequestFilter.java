package com.ecommerce.ecommerce_Backed.JWTValidation;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtRequestFilter  extends OncePerRequestFilter {
    public JwtRequestFilter() {
    }

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final  String authorizationHeader=request.getHeader(JwtConstant.JWT_HEADER);
      String SECRET_KEY = "blhblhbzoyzgdygzygrkhfkhkfzgfgjhbbgyubfuyssvjvgsvdvskgdvskgvdksgvkdgsv";

        String username="";
        String jwt="";
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) { //if facing issue look at Bearer (rev)
            jwt = authorizationHeader.substring(7);
            try {
               // SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
                SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
                Claims claims=  Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();

               String mobileNumber=String.valueOf(claims.get("mobileNumber"));

               String authorities=String.valueOf(claims.get("authorities"));
                List<GrantedAuthority> auth= AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                Authentication authentication=new UsernamePasswordAuthenticationToken(mobileNumber,null,auth);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (IllegalArgumentException | ExpiredJwtException | MalformedJwtException | SignatureException e) {
                // Handle exceptions related to JWT processing
                logger.warn("JWT Token is invalid: " + e.getMessage());
            }
        }

//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//            if (jwtUtil.validateToken(jwt, userDetails)) {
//                JWTAuthenticationToken authenticationToken = new JWTAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
        filterChain.doFilter(request, response);
    }



    }

