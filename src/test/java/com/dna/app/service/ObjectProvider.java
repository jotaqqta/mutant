package com.dna.app.service;

import java.util.Arrays;
import java.util.List;

import com.dna.app.controller.model.DnaDto;
import com.dna.app.repository.entity.MutantStats;

public class ObjectProvider {

    public static DnaDto getDnaDto() {
    	List<String> dna = Arrays.asList("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG".split(","));
    	return DnaDto.builder().dna(dna)
	    	.isMutant(true)
	    	.build();
    }
    
    public static MutantStats getMutantStats() {
    	return MutantStats.builder().countMutantDna(40)
    			.countHumanDna(100)
    			.build();
    }

}
