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
@Table(name = "PERFIL")
@EqualsAndHashCode(callSuper = true)
public class Perfil extends GenericEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1559700198209803993L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String descricao;

	public Perfil() {
		super();
	}
	
	public Perfil(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Perfil(Long id) {
		super();
		this.id = id;
	}

}
