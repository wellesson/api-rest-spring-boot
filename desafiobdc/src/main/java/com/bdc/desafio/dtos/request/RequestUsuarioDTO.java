package com.bdc.desafio.dtos.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RequestUsuarioDTO {

	@NotBlank(message="campo obrigatorio")
	private String nome;
	private String cpf;
	private Long codigoPerfil;
	private String senha;
	private RequestEnderecoDTO endereco;

}
