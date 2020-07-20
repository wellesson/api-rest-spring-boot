
package com.bdc.desafio.dtos;

public class CidadeDTO {

	private String nome;

	private UnidadeFederativaDTO estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UnidadeFederativaDTO getEstado() {
		return estado;
	}

	public void setEstado(UnidadeFederativaDTO estado) {
		this.estado = estado;
	}

}
