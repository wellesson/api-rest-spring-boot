
package com.bdc.desafio.dtos;

import lombok.Data;

@Data
public class EnderecoDTO {

	private String logradouro;

	private String numero;

	private String bairro;

	private CidadeDTO cidade;

}
