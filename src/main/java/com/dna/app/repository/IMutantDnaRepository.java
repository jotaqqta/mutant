package com.dna.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dna.app.repository.entity.MutantDna;

public interface IMutantDnaRepository extends JpaRepository<MutantDna, Long>{
	
	MutantDna findByDna(String dna);
	
	long countAllByIsMutant(Boolean isMutant);

}
