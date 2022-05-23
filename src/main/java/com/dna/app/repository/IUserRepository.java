package com.dna.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dna.app.repository.entity.User;

@Repository
public interface IUserRepository  extends JpaRepository<User, Long> {

	/**
	 * 
	 * @param idUser Identificador unico del usuario a buscar.
	 * @return Usuario asociado al <b>idUsuario</b> o <b>null</b> en caso que no exista.
	 */
	public User findByIdUser(Long idUser);

	/**
	 * 
	 * @param NameUser Nombre del usuario a buscar.
	 * @return User asociado al <b>nomUsuario</b>.
	 */
	public Optional<User> findByNameUser(String NameUser);

	/**
	 * 
	 * @param NameUser Nombre de usuario a validar si existe.
	 * @return True si el usuario con nombre <b>nomUsuario</b> ya existe de lo contrario false.
	 */
	public boolean existsByNameUser(String NameUser);


}
