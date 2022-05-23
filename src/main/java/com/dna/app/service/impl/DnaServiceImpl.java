package com.dna.app.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.dna.app.controller.model.DnaDto;
import com.dna.app.repository.entity.CountDna;
import com.dna.app.service.IDnaService;
import com.dna.app.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DnaServiceImpl implements IDnaService{
	
	public DnaServiceImpl() {
		super();
	}
	
	@Override
	public boolean isMutant(DnaDto dnaDto) {
		
		int sizeMxDna = dnaDto.getDna().size();
		int countSeq = 0;
		if(isNxN(dnaDto.getDna(), sizeMxDna) && validateDna(dnaDto.getDna())) {
			countSeq = findHorizontalVertical(dnaDto.getDna(), sizeMxDna);
			log.info("Cantidad Total de secuencias de ADN encontradas: " + countSeq);
			if(countSeq < 2)
				countSeq += findDnaOblicua2(dnaDto.getDna(), sizeMxDna);
			if(countSeq > 1)
				return true;
			return false;
		}else
			return false;
		
	}
	
	/**
	 * Método que busca en direcciones horizontales y verticales secuencias del ADN.
	 * 
	 * @param listDna Lista de secuencia de ADN
	 * @param sizeMxDna tamaño de la Matriz
	 * @return cantidad de secuencias de ADN encontradas.
	 */
	private int findHorizontalVertical(List<String> listDna, int sizeMxDna) {
		
		CountDna horizontal = new CountDna();
		CountDna vertical = new CountDna();
		
		for(int x = 0; x <= sizeMxDna-1; x++) {
			horizontal.setCountMatch(0);
			vertical.setCountMatch(0);
			for(int y = 0; y <= sizeMxDna-1; y++) {
				if(horizontal.getCountSequence() + vertical.getCountSequence() > 1)
					return horizontal.getCountSequence() + vertical.getCountSequence();
				if(y < sizeMxDna-1) {
					validateSequence(listDna.get(x).charAt(y), listDna.get(x).charAt(y+1), horizontal);
					validateSequence(listDna.get(y).charAt(x), listDna.get(y+1).charAt(x), vertical);
				}	
			}	
		}
		
		log.info("Cantidad de secuencias horizontal de ADN encontradas: " + horizontal.getCountSequence());
		log.info("Cantidad de secuencias vertical de ADN encontradas: " + vertical.getCountSequence());
		return horizontal.getCountSequence() + vertical.getCountSequence();
		
	}
	
	/**
	 * Método que busca en dirección oblicua secuencias del ADN.
	 * 
	 * @param listDna Lista de secuencia de ADN
	 * @param sizeMxDna tamaño de la Matriz
	 * @return cantidad de secuencias de ADN encontradas.
	 */
	private int findDnaOblicua2(List<String> listDna, int sizeMxDna) {
		
		int cons = Constants.I_SIZE_DNA_MATCH;
		CountDna oblicuaIzqDer1 = new CountDna();
		CountDna oblicuaIzqDer2 = new CountDna();
		CountDna oblicuaDerIzq1 = new CountDna();
		CountDna oblicuaDerIzq2 = new CountDna();

		for(int x = cons, restarOblIzq = (sizeMxDna-cons); x<sizeMxDna; x++, restarOblIzq--) {
			oblicuaIzqDer1.setCountMatch(0);
			oblicuaIzqDer2.setCountMatch(0);
			oblicuaDerIzq1.setCountMatch(0);
			oblicuaDerIzq2.setCountMatch(0);
			for(int y=sizeMxDna-1, xx=x, izq=restarOblIzq-1, yy=0; y >-1 ; y--, xx--, izq++, yy++) {
				if(xx > 0 && y > 0) {	
					validateSequence(listDna.get(y).charAt(xx), listDna.get(y-1).charAt(xx-1), oblicuaIzqDer1);
					validateSequence(listDna.get(yy).charAt(xx), listDna.get(yy+1).charAt(xx-1), oblicuaDerIzq1);
					if(xx != y) {
						validateSequence(listDna.get(xx).charAt(y), listDna.get(xx-1).charAt(y-1), oblicuaIzqDer2);
						validateSequence(listDna.get(izq).charAt(y), listDna.get(izq+1).charAt(y-1), oblicuaDerIzq2);
					}
				}
				
				if(xx == 0)
					break;
			}
		}
		
		log.info("Cantidad de secuencias oblicuas de ADN encontradas: " + (oblicuaIzqDer1.getCountSequence() + oblicuaIzqDer2.getCountSequence() + oblicuaDerIzq1.getCountSequence() + oblicuaDerIzq1.getCountSequence()));
		return oblicuaIzqDer1.getCountSequence() + oblicuaIzqDer2.getCountSequence() + oblicuaDerIzq1.getCountSequence() + oblicuaDerIzq1.getCountSequence();

	}
	
	/**
	 * Método que verifica si 2 valores son idénticos.
	 * 
	 * @param current valor de char de la posición del recorrido de la secuencia de ADN
	 * @param next valor de char de la posición siguiente del recorrido de la secuencia de ADN
	 * @return countDna Objeto que almacena la cantidad de secuencias de ADN encontradas.
	 */
	private void validateSequence(char current, char next, CountDna countDna) {
		
		if(Character.compare(current, next) == 0) {
			countDna.setCountMatch(countDna.getCountMatch()+1);
			if(countDna.getCountMatch() == Constants.L_SIZE_DNA_MATCH) {
				countDna.setCountSequence(countDna.getCountSequence()+1);
				countDna.setCountMatch(0);
			}
		}
		else
			countDna.setCountMatch(0);
		
	}
	
	/**
	 * Validación de la Matriz para determinar si es NxN
	 * 
	 * @param listDna Lista con la secuencia de ADN
	 * @param sizeMxDna tamaño de la Matriz
	 * @return si la cadena de ADN corresponde a una Matriz NxN
	 */
	private boolean isNxN(List<String> listDna, long sizeMxDna) {
		
		for(String dna : listDna) {
			if(dna.length() != sizeMxDna)
				return false;
		}
		return true;
	}
	
	/**
	 * Validación de los caracteres de la secuencia de ADN
	 * 
	 * @param listDna Lista con la secuencia de ADN
	 * @param sizeMxDna tamaño de la Matriz
	 * @return si la cadena de ADN contiene solo caracteres válidos
	 */
	private boolean validateDna(List<String> listDna) {
        
		int x = listDna.size();
		for(String s : listDna) {
			for(int i = 0; i<x; i++) {
				if(!s.substring(i, i+1).equals("A") && !s.substring(i, i+1).equals("C") 
						&& !s.substring(i, i+1).equals("T") && !s.substring(i, i+1).equals("G") )
					return false;
			}	
		}
		
		return true;
		
	}

}
