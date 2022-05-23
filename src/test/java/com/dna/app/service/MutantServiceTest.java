package com.dna.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dna.app.controller.model.DnaDto;
import com.dna.app.repository.IMutantDnaRepository;
import com.dna.app.service.impl.MutantServiceImpl;

@SpringBootTest
public class MutantServiceTest {
	
	@Autowired
	private IMutantService mutantService;
	
	@Autowired
	private IMutantDnaRepository mutantDnaRepository;
	
	private DnaDto dnaDto;
	
	@BeforeEach
	public void init() {
		this.mutantService = new MutantServiceImpl(mutantDnaRepository);
		dnaDto = ObjectProvider.getDnaDto();
	}
	
	@Test
	public void createMutant() {
		mutantService.createMutant(dnaDto);
		assertThat(this.dnaDto).isNotNull();
	}

}
