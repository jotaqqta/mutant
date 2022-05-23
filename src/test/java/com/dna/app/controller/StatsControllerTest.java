package com.dna.app.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dna.app.repository.entity.MutantStats;
import com.dna.app.service.IMutantService;
import com.dna.app.service.ObjectProvider;

@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
@SpringBootTest
public class StatsControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IMutantService mutantService;
	
	
    @BeforeEach
    public void init() {
    }
    
    @Test
    void getStats() throws Exception {
    	
    	MutantStats mutantStats = ObjectProvider.getMutantStats();
    	Mockito.when(mutantService.getStats()).thenReturn(mutantStats);
    	
    	mockMvc.perform(MockMvcRequestBuilders.get("/stats"))
        .andExpect(status().isOk());
    	
    }
	
}
