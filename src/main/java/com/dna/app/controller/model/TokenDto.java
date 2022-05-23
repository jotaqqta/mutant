package com.dna.app.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class TokenDto {

	@ApiModelProperty(position=1, dataType="String", value="Token de autenticación.<br>", example="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKQUlERVJTT04iLCJp.", required=true)
    private String token;

	@ApiModelProperty(position=2, dataType="String", value="Nombre de usuario.<br>", example="Super Magneto", required=true)
	private String userName;

    public TokenDto(String token, String nombreUsuario) {
        this.token = token;
        this.userName = nombreUsuario;
    }

}
