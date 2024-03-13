package com.iminbus.pushnotification.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {


    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {  // This method converts a Jwt token into an AbstractAuthenticationToken
        // Extract authorities from the JWT
        Collection<GrantedAuthority> roles = extractAuthorities(jwt);
        // Create a JwtAuthenticationToken with the extracted authorities
        return new JwtAuthenticationToken(jwt, roles);
    }

    // This method extracts authorities from the JWT claims
//    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
//        // Check if the 'ver' claim exists in the JWT
//        if (jwt.getClaim("ver") != null) {
//            // Extract the 'ver' claim
//            String roles1 = jwt.getClaim("ver");
//
//            List<GrantedAuthority> roles = new ArrayList<>();
//
//            // Create a SimpleGrantedAuthority for the extracted role and add it to the list of roles
//            roles.add(new SimpleGrantedAuthority(roles1));
//
//            return roles;
//        }
//        // If 'ver' claim doesn't exist or is null, return an empty list of authorities
//        return new ArrayList<>();
//    }


    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        if (jwt.getClaim("roles") != null) {
            List<String> roles1 = jwt.getClaim("roles");

            List<GrantedAuthority> roles = new ArrayList<>();

            for (String ADRole : roles1) {
                roles.add(new SimpleGrantedAuthority(ADRole));
            }


            return roles;
        }
        return new ArrayList<>();
    }
}