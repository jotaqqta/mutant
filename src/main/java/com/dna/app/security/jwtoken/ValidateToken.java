package com.dna.app.security.jwtoken;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dna.app.service.impl.UserDetailsServiceImpl;
import com.dna.app.util.Logs;

public class ValidateToken extends OncePerRequestFilter{

    @Autowired
    private ProviderToken proveedorToken;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest requet, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(requet);
            if(token != null && proveedorToken.validateToken(token)){
                String nomUsuario = proveedorToken.getNombreUsuarioFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(nomUsuario);

                UsernamePasswordAuthenticationToken autenticacionUsuario =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autenticacionUsuario);
            }
        } catch (Exception e){
            Logs.error("ValidarToken.doFilterInternal: " + e.getMessage());
        }
        filterChain.doFilter(requet, response);
    }

    private String getToken(HttpServletRequest request){
        String token = null;
    	String header = request.getHeader("Authorization");        

    	if(header != null && header.startsWith("Bearer")) {
    		token = header.replace("Bearer ", "");
        }
        return token;
    }

}
