package com.dna.app.controller.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter 
@Setter 
@ToString
public class DnaDto {

	@JsonProperty("dna")
    @ApiModelProperty(position=1, dataType="String", value="Cadena de secuencia de ADN.", example="Cadena de DNA entre comillas doble separada por comas {ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG}", required=true)
	private List<String> dna;
	
	private boolean isMutant;
	
}
