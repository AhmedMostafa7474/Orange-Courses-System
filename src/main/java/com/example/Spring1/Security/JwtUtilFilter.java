package com.example.Spring1.Security;

import com.example.Spring1.Model.Adminn;
import com.example.Spring1.Service.AdminnServices;
import com.example.Spring1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtUtilFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;
    @Autowired
    AdminnServices adminnServices;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authHeader=httpServletRequest.getHeader("Authorization");
        String username=null;
        String jwt=null;

        if(authHeader!=null&& authHeader.startsWith("Bearer "))
        {
            jwt =authHeader.substring(7);
            username=jwtUtil.extractUsername(jwt);
        }
        if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails= userService.loadUserByUsername(username);

           if(jwtUtil.validateToken(jwt,userDetails))
           {
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                       userDetails,null,userDetails.getAuthorities()
               );
               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
