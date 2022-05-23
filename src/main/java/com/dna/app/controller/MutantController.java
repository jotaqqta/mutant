package com.dna.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dna.app.controller.model.DnaDto;
import com.dna.app.service.IDnaService;
import com.dna.app.service.IMutantService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/mutant/")
public class MutantController {
	
	@Autowired
	private IDnaService adnService;
	
	@Autowired
	private IMutantService mutantService;
	
	@PostMapping
    @ApiOperation(value = "Analiza una cadena de ADN")
    @ApiResponses({
        @ApiResponse(code = 200, message = "ADN Mutante"),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 403, message = "ADN no Mutante.")
    })
	public ResponseEntity<Void> isMutant(
			@ApiParam(name="analizarADN", value="ADN para analizar", required = true)
			@Valid @RequestBody DnaDto dnaDto){

		boolean isMutant = adnService.isMutant(dnaDto);
		dnaDto.setMutant(isMutant);
		mutantService.createMutant(dnaDto);
		if(isMutant)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

}
