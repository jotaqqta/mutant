package com.dna.app.security.jwtoken;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.dna.app.util.Logs;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class ProviderToken {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generarToken(Authentication authentication){
        UserAuthorized usuarioAutorizado = (UserAuthorized) authentication.getPrincipal();
        
        return Jwts.builder().setSubject(usuarioAutorizado.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
    	boolean esValido = false;
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            esValido = true;
        }catch (MalformedJwtException e){
        	Logs.error("Jwt malformed. "+e.getMessage());
        }catch (UnsupportedJwtException e){
        	Logs.error("Jwt don't supported. "+e.getMessage());
        }catch (ExpiredJwtException e){
        	Logs.error("Jwt expired. "+e.getMessage());
        }catch (IllegalArgumentException e){
        	Logs.error("Jwt invalid. "+e.getMessage());
        }catch (SignatureException e){
        	Logs.error("Signature failed. "+e.getMessage());
        }
        return esValido;
    }
}
