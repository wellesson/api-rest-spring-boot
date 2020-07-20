
package com.bdc.desafio.dtos.request;

import lombok.Data;

@Data
public class RequestEnderecoDTO {

	private String logradouro;
	private String numero;
	private String bairro;
	private Long codigoCidade;

}
