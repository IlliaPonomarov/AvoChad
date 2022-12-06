package com.example.avochadbackend.configs;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.avochadbackend.security.JWTUtil;
import com.example.avochadbackend.services.UserDetailService;

@Component
public class JWTFilter extends OncePerRequestFilter{

    private final com.example.avochadbackend.security.JWTUtil jwtUtil;
    private final com.example.avochadbackend.services.UserDetailService userDetailService;

    @Autowired
    public JWTFilter(com.example.avochadbackend.security.JWTUtil jwtUtil, com.example.avochadbackend.services.UserDetailService userDetailService) {
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String authHeader = request.getHeader("Authorization");

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    StringBuilder token = new StringBuilder(authHeader.substring(7));
                    token.deleteCharAt(token.length() - 1);
                    String jwtToken = token.toString();

                    if(jwtToken.isBlank())
                        response.sendError(401, "Token is empty");
                    else {

                        try{
                            String username = jwtUtil.validateToken(jwtToken);
                            UserDetails userDetails = userDetailService.loadUserByUsername(username);

                            UsernamePasswordAuthenticationToken authToken = 
                            new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                            if( SecurityContextHolder.getContext().getAuthentication() == null)
                                SecurityContextHolder.getContext().setAuthentication(authToken);

                        }
                        catch(Exception e) {
                            response.sendError(401, e.getMessage());
                        }

                    }
                } else
                     System.out.println("No token found");
                filterChain.doFilter(request, response);
    }



} 