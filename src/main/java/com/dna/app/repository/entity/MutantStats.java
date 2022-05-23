package com.dna.app.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MutantStats {

	@JsonProperty("count_mutant_dna")
	@ApiModelProperty(position=1, dataType="long", value="Cantidad de ADN Mutante analizado",  example="40")
	private long countMutantDna;
	
	@JsonProperty("count_human_dna")
	@ApiModelProperty(position=2, dataType="lonh", value="Cantidad de ADN Humano analizado", example="10")
	private long countHumanDna;
	
	@JsonProperty("ratio")
	@ApiModelProperty(position=3, dataType="double", value="Valor de Ratio", example="0.4")
	private double ratio;
	
	public double calculateRatio() {
		if(countMutantDna == 0 || countHumanDna == 0)
			return 0;
		return (double) countMutantDna / countHumanDna;
	}
	
}
