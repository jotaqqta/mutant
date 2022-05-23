package com.dna.app.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dna.app.controller.model.DnaDto;
import com.dna.app.repository.IMutantDnaRepository;
import com.dna.app.repository.entity.MutantDna;
import com.dna.app.repository.entity.MutantStats;
import com.dna.app.service.IMutantService;

@Service
public class MutantServiceImpl implements IMutantService{

	public MutantServiceImpl(IMutantDnaRepository mutantRepository) {
		super();
		this.mutantRepository = mutantRepository;
	}
	
	@Autowired
	private IMutantDnaRepository mutantRepository;
	
	@Override
	public DnaDto getMutant(String dna) {
		
		return toDnaDto(mutantRepository.findByDna(dna));
	}
	
	@Override
	public void createMutant(DnaDto dnaDto) {
		
		MutantDna mutant = toMutantDna(dnaDto);
		
		if(getMutant(mutant.getDna()) == null)
			mutantRepository.save(mutant);
	}
	
	@Override
	public MutantStats getStats() {
		
		MutantStats mutantStats = new MutantStats();
		mutantStats.setCountMutantDna(mutantRepository.countAllByIsMutant(true));
		mutantStats.setCountHumanDna(mutantRepository.countAllByIsMutant(false));
		mutantStats.setRatio(mutantStats.calculateRatio());
		
		return mutantStats;
	}
	
	private MutantDna toMutantDna(DnaDto dnaDto) {
		
		return MutantDna.builder().dna(arrayToString(dnaDto.getDna()))
				.isMutant(dnaDto.isMutant())
				.build();
	}
	
	private DnaDto toDnaDto(MutantDna mutantDna) {
		
		if(mutantDna != null)
			return DnaDto.builder().dna(Arrays.asList(mutantDna.getDna().split(",")))
					.isMutant(mutantDna.getIsMutant())
					.build();
		return null;		
	}
	
	private String arrayToString(List<String> seqDna) {
		return String.join(",", seqDna);
	}

}
