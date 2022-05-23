package com.dna.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.dna.app.repository.entity.MutantStats;
import com.dna.app.service.ObjectProvider;

@SpringBootTest
public class MutantStatsTest {
	
	private MutantStats mutantStats;
	
	@BeforeEach
	public void init() {
		mutantStats = ObjectProvider.getMutantStats();
	}
	
	@Test
	public void getStatst() {
		assertEquals(0.4, (double)mutantStats.calculateRatio());
	}
	
	public void getStatsWhitZero() {
		mutantStats.setCountHumanDna(0);
		assertEquals(0, (double)mutantStats.calculateRatio());
	}

}
