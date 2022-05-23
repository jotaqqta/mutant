package com.dna.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dna.app.repository.entity.MutantStats;
import com.dna.app.service.IMutantService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/stats")
public class StatsController {
	
	@Autowired
	private IMutantService mutantService;
	
	@GetMapping
	@ApiOperation(value = "Genera estadísticas de los análisis de ADN")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Estadísticas de ADN generadas"),
        @ApiResponse(code = 401, message = "Acceso al recurso no autorizado."),
        @ApiResponse(code = 204, message = "No existen datos para generar las estadísticas")
    })
	public ResponseEntity<MutantStats> getStats(){
		
		MutantStats mutantStats = mutantService.getStats();
		if(mutantStats != null)
			return new ResponseEntity<MutantStats>(mutantStats, HttpStatus.OK);
		return new ResponseEntity<MutantStats>(HttpStatus.NO_CONTENT);
		
	}

}
