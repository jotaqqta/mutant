package com.dna.app.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="rols")
@NoArgsConstructor 
@AllArgsConstructor 
@Getter 
@Setter 
@ToString
@Data
@ApiModel(description = "Roles o perfiles de usuarios del sistema wishlist.")
public class Role {

    @Id
    @Column(name="id_role")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position = 1, dataType = "Long", value = "Identificador único del rol.<br>", example = "1", required = true)
    private Long idRole;
    
    @Column(name="name_role", unique = true)
    @NotNull(message = "El rol de usuario no puede ser vacio.")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(position = 2, dataType = "String", value = "Nombre del rol. <br>", example = "ADMIN", required = true)
    private ERole roleName;

	public Role(ERole nombre) {
		super();
		this.roleName = nombre;
	}

}
