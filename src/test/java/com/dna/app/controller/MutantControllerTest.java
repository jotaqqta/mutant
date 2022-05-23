package com.dna.app.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dna.app.controller.model.DnaDto;
import com.dna.app.service.IDnaService;
import com.dna.app.service.ObjectProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
@SpringBootTest
public class MutantControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private IDnaService adnService;
	
	private DnaDto dnaDto;
	
	private ObjectMapper objectMapper;  

    @BeforeEach
    public void init() {
    	this.dnaDto = ObjectProvider.getDnaDto();
        this.objectMapper = new ObjectMapper();
    }
	
	@Test
	void isMutant() throws Exception {
		
		Mockito.when(adnService.isMutant(any(DnaDto.class))).thenReturn(true);
		
		String json = objectMapper.writeValueAsString(dnaDto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/mutant/")
		.content(json)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
	}
	
}
