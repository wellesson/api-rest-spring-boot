package com.bdc.desafio.dtos.request;

import lombok.Data;

@Data
public class RequestUsuarioDTO {

	private String nome;

	private String cpf;
	
	private Long codigoPerfil;
	
	private String senha;
	
	private RequestEnderecoDTO endereco;

}
