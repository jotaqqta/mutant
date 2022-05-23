package com.dna.app.repository.entity;

import lombok.Getter;

@Getter
public enum ERole {

	ADMIN("ADMIN");

	private String nombreRol;

	private ERole(String nombreRol) {
		this.nombreRol = nombreRol;
	}

}
