package com.bdc.desafio.dtos.response;

import java.util.List;

import lombok.Data;

@Data
public class ResponseUsuarioDTO {

	private String nome;
	private String cpf;
	private Integer codigoPerfil;
	private List<String> telefones;
	private ResponseEnderecoDTO endereco;

}
