package com.stemlink.skillmentor.security;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class ClerkAuthenticationFilter extends OncePerRequestFilter {

    private final ClerkValidator clerkValidator;
    //  first hit BASE VALIDATOR. then decide if it is --> clerk, aws or service token
    //then go to filter and validate the token

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("JWT FILTER HIT");

        String token = extractToken(request);

        if (token != null && clerkValidator.validateToken(token)) {
           // String username = clerkValidator.extractUsername(token);
           // List<String> roles = clerkValidator.extractRoles(token);

            // Get data from ClerkValidator
            String username = clerkValidator.extractUserId(token);
            List<String> roles = clerkValidator.extractRoles(token);

            //String username = "user";
           // List<String> roles = new ArrayList<>();

            List<GrantedAuthority> authorities = roles != null ?
                    roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .collect(Collectors.toList()) :
                    new ArrayList<>();

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }


    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
