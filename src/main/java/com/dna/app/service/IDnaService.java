package com.dna.app.service;

import com.dna.app.controller.model.DnaDto;

/**
 * Interfaz que define el método que analiza un ADN para determinar si es Mutante
 */
public interface IDnaService {

	/**
	 * Analiza si la secuencia de ADN corresponde a un Mutante o no.
	 * 
	 * @param dnaDto Lista con la secuencia de ADN a analizar.
	 * @return Si el ADN analizado es Mutante o no.
	 */
	boolean isMutant(DnaDto dnaDto);
	
}
