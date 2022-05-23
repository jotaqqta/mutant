package com.dna.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dna.app.repository.entity.User;
import com.dna.app.security.jwtoken.UserAuthorized;
import com.dna.app.service.IUserService;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private IUserService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nameUser) throws UsernameNotFoundException {
    	UserAuthorized usuarioAutorizado = null;

    	Optional<User> user = usuarioService.findUserByNameUser(nameUser);
    	if(user.isPresent()) {
    		usuarioAutorizado = UserAuthorized.build(user.get());
    	}    	
        return usuarioAutorizado;
    }

}
