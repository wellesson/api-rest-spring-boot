package com.bdc.desafio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bdc.desafio.core.model.GenericEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "endereco")
@EqualsAndHashCode(callSuper = true)
public class Endereco extends GenericEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2296747139078255159L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idendereco")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idcidade")
	private Cidade cidade;

	private String logradouro;

	private String numero;

	private String bairro;

	public Endereco(Cidade cidade, String logradouro, String numero, String bairro) {
		super();
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
	}

}
