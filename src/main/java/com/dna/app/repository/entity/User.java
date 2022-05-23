package com.dna.app.repository.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Sets;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="users")
@AllArgsConstructor @NoArgsConstructor @Builder
@Getter @Setter @ToString
@Data
public class User implements Serializable{

	private static final long serialVersionUID = 2L;

	@Id
    @Column(name="id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(position=1, dataType="Long", value="Identificador unico del usuario. <br>", example="10", required=true)
    private Long idUser;

    @Column(name="username", nullable = false, unique = true)
    @NotNull(message = "Nombre de usuario no puede ser vacio.")
    @Size(min=4, max=50, message="Cantidad de carateres para el nombre de uaurio minimo es de 4 y de maxima de 50.")
    @ApiModelProperty(position=2, dataType="String", value="Nombre de usuario el cual debe ser unico. <br>", example="JAIDER_2103", required=true)
    private String nameUser;

    @JsonIgnore
    @Column(name="password", nullable = false)
    @NotNull(message = "Contraseña de usuario no puede ser vacia.")
    @Size(min=10, max=300, message="Cantidad de carateres de la contraseña mínimo debe ser de 10 y de maxima de 50.")
    @ApiModelProperty(position=3, dataType="String", value="Contraseña del usuario. <br>", example="**********", required=true)
    private String password;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull(message = "Rol no puede ser vacio.")
    @JoinTable(name="user_rol", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ApiModelProperty(position=4, dataType="Rol", required=true)
    private Set<Role> listRole = Sets.newHashSet();

    @Column(name="fullname", nullable = false)
    @NotNull(message = "Nombre completo de usuario no puede ser vacio.")
    @Size(min=10, max=100, message = "Cantidad de carateres del nombre minimo es de 10 y de maximo de 100")
    @ApiModelProperty(position=5, dataType="String", value="<br>Nombres y apellidos del usuario. <br>", example="Magento XMen", required=true)
    private String fullName;

    @Column(name="dt_creation")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(position=6, dataType = "Date", value = "Fecha creacion del usuario. (yyyy-MM-dd HH:mm:ss)")
    private Date dtCreation;

    @PrePersist
    public void prePersist() {
    	this.fullName = this.fullName.toUpperCase().trim();
    	this.dtCreation = new Date();
    }

	public User(
			@NotNull(message = "Nick de usuario no puede ser vacio.") 
			@Size(min = 4, max = 50, message = "Cantidad de carateres del NickName debe ser entre 4 y 50.") 
			String nomUsuario,
			@NotNull(message = "Contraseña de usuario no puede ser vacia.") 
			@Size(min = 10, max = 50, message = "Cantidad de carateres de la contraseña debe ser de 10 y 50.") 
			String password,
			@NotNull(message = "Nombre completo de usuario no puede ser vacio.") 
			@Size(min = 10, max = 100, message = "Cantidad de carateres del nombre debe ser entre 10 y 100.") 
			String fullName) {
		
		super();
		this.nameUser = nomUsuario;
		this.password = password;
		this.fullName = fullName;
	}

}
