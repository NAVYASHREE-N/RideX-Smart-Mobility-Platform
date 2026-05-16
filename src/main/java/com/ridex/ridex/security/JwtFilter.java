package com.ridex.ridex.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain

    ) throws ServletException, IOException {

        // AUTH HEADER

        final String authHeader =
                request.getHeader(
                        "Authorization"
                );

        String token = null;

        String email = null;

        // CHECK HEADER

        if(
                authHeader != null
                &&
                authHeader.startsWith(
                        "Bearer "
                )
        ){

            token =
                    authHeader.substring(7);

            // VALIDATE TOKEN

            if(
                    JwtUtil.validateToken(
                            token
                    )
            ){

                email =
                        JwtUtil.extractEmail(
                                token
                        );

            }

        }

        // AUTHENTICATE USER

        if(
                email != null
                &&
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                == null
        ){

            User user =
                    new User(

                            email,

                            "",

                            Collections.emptyList()

                    );

            UsernamePasswordAuthenticationToken authToken =

                    new UsernamePasswordAuthenticationToken(

                            user,

                            null,

                            user.getAuthorities()

                    );

            authToken.setDetails(

                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)

            );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authToken);

        }

        filterChain.doFilter(
                request,
                response
        );

    }

}