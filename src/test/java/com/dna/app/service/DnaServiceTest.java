package com.dna.app.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dna.app.controller.model.DnaDto;
import com.dna.app.service.impl.DnaServiceImpl;

@SpringBootTest
public class DnaServiceTest {
	
	@Autowired
	private IDnaService adnService;
	
	@BeforeEach
	public void init() {
		
		adnService = new DnaServiceImpl();
	}
	
	@ParameterizedTest
    @CsvFileSource(resources = "/dnaTest.csv", numLinesToSkip = 1)
    void checkIsMutant(String input, boolean expected){
        DnaDto dnaDto = new DnaDto();
        dnaDto.setDna(Arrays.asList(input.split(",")));
        dnaDto.setMutant(expected);
        assertEquals(adnService.isMutant(dnaDto), expected);
    }

}
