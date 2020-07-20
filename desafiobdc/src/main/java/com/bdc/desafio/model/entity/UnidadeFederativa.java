package com.bdc.desafio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bdc.desafio.core.model.GenericEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "UNIDADEFEDERATIVA")
@EqualsAndHashCode(callSuper = true)
public class UnidadeFederativa extends GenericEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3446125439848412700L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduf")
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String sigla;

	public UnidadeFederativa() {
		super();
	}

	public UnidadeFederativa(Long id, String nome, String sigla) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}

}
