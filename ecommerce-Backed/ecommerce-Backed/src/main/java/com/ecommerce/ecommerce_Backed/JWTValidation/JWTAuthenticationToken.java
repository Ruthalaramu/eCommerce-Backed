package com.ecommerce.ecommerce_Backed.JWTValidation;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

    private final UserDetails principal;
    private final String token;

    public JWTAuthenticationToken(UserDetails principal, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.token = token;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public UserDetails getPrincipal() {
        return this.principal;
    }
}
