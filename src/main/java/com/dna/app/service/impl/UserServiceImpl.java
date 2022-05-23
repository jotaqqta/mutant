package com.dna.app.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dna.app.repository.IUserRepository;
import com.dna.app.repository.entity.User;
import com.dna.app.service.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	public UserServiceImpl(IUserRepository usuarioRep) {
		super();
		this.usuarioRep = usuarioRep;
	}
	
	@Autowired
	private IUserRepository usuarioRep;

	@Override
	public Optional<User> findUserByNameUser(String nameUser) {
		return usuarioRep.findByNameUser(nameUser);
	}

}
