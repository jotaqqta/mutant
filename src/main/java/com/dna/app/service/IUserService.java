package com.dna.app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dna.app.repository.entity.User;

@Service
public interface IUserService {

	/***
	 * Busca un usuario dado su nombre unico <b>NickName</b>.
	 * 
	 * @param nameUser Identificador unico del usuario. 
	 * @return Usuario asociado al <b>nameUser</b> o null si no se encuentra.
	 */
	public Optional<User> findUserByNameUser(String nameUser);

}
