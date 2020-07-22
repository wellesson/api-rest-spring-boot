package com.bdc.desafio.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "USUARIO")
@EqualsAndHashCode(callSuper = true)
public class Usuario extends GenericEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6444938791194464563L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusuario")
	private Long id;

	@Column(nullable = false)
	private String cpf;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String senha;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idendereco")
	private Endereco endereco;

	@OneToOne
	@JoinColumn(name = "idperfil")
	private Perfil perfil;

	public Usuario(String cpf, String nome, String senha, Endereco endereco, Perfil perfil) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
		this.endereco = endereco;
		this.perfil = perfil;
	}

}
