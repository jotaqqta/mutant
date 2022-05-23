package com.dna.app.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MutantDna")
public class MutantDna {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMutantDna;

    @Column(nullable = false, updatable = false, unique = true)
    @Size(max = 4000)
    private String dna;

    @Column(nullable = false)
    private Boolean isMutant;

}
