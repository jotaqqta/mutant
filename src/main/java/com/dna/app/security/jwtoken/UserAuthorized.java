package com.dna.app.security.jwtoken;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dna.app.repository.entity.User;

public class UserAuthorized  implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String nomCompleto;
    private String nombreUsuario;
    private String clave;
    private Collection<? extends GrantedAuthority> authorities;

    public UserAuthorized(String nomUsuario, String nombre, String clave, 
    		                 Collection<? extends GrantedAuthority> authorities) {
    	this.nombreUsuario = nomUsuario;
        this.nomCompleto = nombre;
        this.clave = clave;
        this.authorities = authorities;
    }

    public static UserAuthorized build(User usuario){
        List<GrantedAuthority> authorities =
                usuario.getListRole().stream().map(rol -> new SimpleGrantedAuthority(rol.getRoleName()
                 .name())).collect(Collectors.toList());

        return new UserAuthorized(usuario.getNameUser(), usuario.getFullName(), usuario.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return nomCompleto;
    }

}
