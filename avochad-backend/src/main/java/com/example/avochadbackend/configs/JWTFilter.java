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
import com.example.avochadbackend.services.MyUserDetailService;

@Component
public class JWTFilter extends OncePerRequestFilter{

    private final JWTUtil jwtUtil;
    private final MyUserDetailService myUserDetailService;

    @Autowired
    public JWTFilter(JWTUtil jwtUtil, MyUserDetailService myUserDetailService) {
        this.jwtUtil = jwtUtil;
        this.myUserDetailService = myUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String authHeader = request.getHeader("Authorization");

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    StringBuilder token = new StringBuilder(authHeader.substring(7));
                    String jwtToken = token.toString();
                    System.out.println(jwtToken);

                    if(jwtToken.isBlank())
                        response.sendError(401, "Token is empty");
                    else {

                        try{
                            String username = jwtUtil.validateToken(jwtToken);
                            UserDetails userDetails = myUserDetailService.loadUserByUsername(username);

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