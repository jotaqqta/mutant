package com.dna.app.service;

import com.dna.app.controller.model.DnaDto;
import com.dna.app.repository.entity.MutantStats;

public interface IMutantService {
	
	/**
	 * Busca la cadena de ADN e indica si es Mutante o no.
	 * 
	 * @param Cadena de secuencia de ADN a consultar.
	 * @return Lista de la secuencia de la cadena de ADN y un boolean indicando si es Mutante o No.
	 */
	DnaDto getMutant(String dna);

	/**
	 * Crea una cadena de secuencia de ADN y su respectivo análisis de si es Mutante o no.
	 * 
	 * @param Lista de secuencia de ADN
	 */
	void createMutant(DnaDto dnaDto);
	
	/**
	 * Busca la cantidad de ADN Mutante y Humano analizado y calcula el valor del Ratio.
	 * 
	 * @return Cantidad de ADN Mutante y Humano y valor del Ratio.
	 */
	MutantStats getStats();
	
}
