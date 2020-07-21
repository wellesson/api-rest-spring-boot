
package com.bdc.desafio.dtos.response;

import lombok.Data;

@Data
public class ResponseUsuarioDTO {

	private String nome;

	private String cpf;

	private ResponsePerfilDTO perfil;

	private ResponseEnderecoDTO endereco;

	private String senha;
	
}
