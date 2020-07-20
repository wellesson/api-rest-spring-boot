
package com.bdc.desafio.dtos.response;

import lombok.Data;

@Data
public class ResponseEnderecoDTO {

	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private Long codigoCidade;

}
