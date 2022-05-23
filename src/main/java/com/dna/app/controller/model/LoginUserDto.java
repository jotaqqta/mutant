package com.dna.app.controller.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@Getter 
@Setter
public class LoginUserDto {

	@JsonProperty("userName")
    @NotBlank(message = "Nombre de usuario no debe ser vacio.")
    @Size(min=4, max=50, message="Nombre de usuario debe tener ent re 4 y 50 caracteres.")
    @ApiModelProperty(position=1, dataType="String", value="Nombre de usuario del sistema.", example="magnetoMeli", required=true)
    private String userName;

	@JsonProperty("password")
    @NotBlank(message = "Contraseña no puede ser vacia.")
    @Size(min=10, max=30, message="Contraseña debe tener entre 10 y 30 caracteres.")
    @ApiModelProperty(position=2, dataType="String", value="Contraseña/Password del usuario. <br>", example="**********", required=true)
    private String password;

}
